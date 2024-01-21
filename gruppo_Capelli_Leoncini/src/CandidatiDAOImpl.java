import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidatiDAOImpl implements CandidatiDAO{

	@Override
	public void selectCandidati(List<Candidato> listaC) {
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_elettorale_cl", "root", "root");//cambiare con info database locale
			System.out.println("Database connesso con successo!");
			
			PreparedStatement preparedStatement = connection.prepareStatement("select * from candidati");
			        
			//Esecuzione della query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("Dati ottenuti dal database con successo!");
			
			String candidato = "";
			String partito = "";
			
			while (rs.next()) {
			
				candidato = rs.getString("nome_candidato");
				partito = rs.getString("partito");
				Candidato c = new Candidato(candidato, partito);
				listaC.add(c);
				
			}
			connection.close();
			System.out.println("Connessione con il database chiusa!");

		
		} catch (SQLException e) {
			System.out.println("Errore nella comunicazione con il database!");
		}

	}

	@Override
	public Candidato selectCandidato(String nome) {
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_elettorale_cl", "root", "root");//cambiare con info database locale
			System.out.println("Database connesso con successo!");
			
			PreparedStatement preparedStatement = connection.prepareStatement("select * from candidati where nome_candidato = ?");
			preparedStatement.setString(1, nome);
			
			//Esecuzione della query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("Dati ottenuti dal database con successo!");
			
			String candidato = "";
			String partito = "";
			Candidato c = null;
			while (rs.next()) {
			
				candidato = rs.getString("nome_candidato");
				partito = rs.getString("partito");
				c = new Candidato(candidato, partito);				
			}
			connection.close();
			System.out.println("Connessione con il database chiusa!");
			return c;
		
		} catch (SQLException e) {
			System.out.println("Errore nella comunicazione con il database!");
			return null;
		}

	}

	@Override
	public List<Candidato> selectCandidatoPartito(String partitoInput) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_elettorale_cl", "root", "root");//cambiare con info database locale
			System.out.println("Database connesso con successo!");
			
			PreparedStatement preparedStatement = connection.prepareStatement("select * from candidati where partito = ?");
			preparedStatement.setString(1, partitoInput);
			
			//Esecuzione della query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("Dati ottenuti dal database con successo!");
			
			String candidato = "";
			String partito = "";
			Candidato c = null;
			List<Candidato> lst = new ArrayList<Candidato>();
			while (rs.next()) {
			
				candidato = rs.getString("nome_candidato");
				partito = rs.getString("partito");
				c = new Candidato(candidato, partito);
				lst.add(c);
			}
			connection.close();
			System.out.println("Connessione con il database chiusa!");
			return lst;
		
		} catch (SQLException e) {
			System.out.println("Errore nella comunicazione con il database!");
			return null;
		}
	}
	
	
	
}
