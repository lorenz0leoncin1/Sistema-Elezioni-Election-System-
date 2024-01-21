import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CandidatiHashDAOImpl implements CandidatiHashDAO{

	@Override
	public List<Candidato> getCandidatiVotazione(Votazione v) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_elettorale_cl", "root", "root");//cambiare con info database locale
			System.out.println("CandidatiHashDAOImpl - Database connesso con successo!");
			
			PreparedStatement preparedStatement = connection.prepareStatement("select candidato from candidati_hash where votazione = ?");
			//Impostazione dei parametri della query
			preparedStatement.setInt(1,v.getIDvotazione());
			        
			//Esecuzione della query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("Candidati ottenuti per votazione" + v.getTitolo_votazione()  +"!");
			
			CandidatiDAOImpl dao = new CandidatiDAOImpl();
			
			
			Candidato c;
			List<Candidato> lst= new ArrayList<Candidato>();
			String nome = "";
			String partito = "";
			
			while (rs.next()) {
				
				nome = rs.getString("candidato");
				partito = dao.selectCandidato(nome).getPartito();
				c = new Candidato(rs.getString("candidato"), partito);
				lst.add(c);
				
			}
			
			System.out.println("CandidatiHash recuperati con successo");
			
			connection.close();
			System.out.println("Connessione con il database chiusa!");
			
			
			//creazione e restituzione dell'oggetto
			return lst;
			
			
		} catch (SQLException e) {
			System.out.println("Errore nella comunicazione con il database!");
		}
		
		return null;
	}

	@Override
	public boolean UpdateVotiCandidato(CandidatiHash ch) {
		try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_elettorale_cl", "root", "root");//cambiare con info database locale
            System.out.println("UPDATE Numero Voti - Database connesso con successo!");

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE candidati_hash SET numeroVoti = ? WHERE candidato = ? AND votazione = ?;");
            //Impostazione dei parametri della query
            preparedStatement.setInt(1,ch.getNumeroVoti());
            preparedStatement.setString(2,ch.getC().getNomeCandidato());
            preparedStatement.setInt(3,ch.getV().getIDvotazione());

            //Esecuzione della query
            preparedStatement.executeUpdate();
            System.out.println("UPDATE NumeroVoti - Dati Aggiornati nel database con successo!");

            connection.close();
            System.out.println("UPDATE NumeroVoti - Connessione con il database chiusa!");
            
            return true;

        } catch (SQLException e) {
            System.out.println("UPDATE NumeroVoti - Errore nella comunicazione con il database!" + e.toString());
    		return false;
        }
	}

	@Override
	public int SelectNumeroVoti(Votazione v, Candidato c) {
		
		int numeroVoti = -1;
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_elettorale_cl", "root", "root");//cambiare con info database locale
			System.out.println("CandidatiHashDAOImpl - Database connesso con successo!");
			
			PreparedStatement preparedStatement = connection.prepareStatement("select numeroVoti from candidati_hash where votazione = ? AND candidato = ?");
			//Impostazione dei parametri della query
			preparedStatement.setInt(1, v.getIDvotazione());
			preparedStatement.setString(2, c.getNomeCandidato());
			        
			//Esecuzione della query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("Numero di Voti ottenuto dal database con successo!");
					
			while (rs.next()) {
				
				numeroVoti = rs.getInt("numeroVoti");
				
			}
			
			connection.close();
			System.out.println("Connessione con il database chiusa!");	
			
		} catch (SQLException e) {
			System.out.println("Errore nella comunicazione con il database!");
			
		}
		
		return numeroVoti;
		
	}

}
