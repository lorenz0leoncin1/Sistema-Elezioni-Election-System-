import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class VotoDAOImpl implements VotoDAO  {
	
	
	@Override
	public boolean SelectVoto(Elettore Elettore, Votazione ID_Votazione) {
		boolean chkPerform = true;
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_elettorale_cl", "root", "root");//cambiare con info database locale
			System.out.println("VotazioneDAOImpl - Database connesso con successo!");
			
			PreparedStatement preparedStatement = connection.prepareStatement("select elettorechk from voto where elettorechk = ? AND votazionechk = ?");
			//Impostazione dei parametri della query
			preparedStatement.setString(1,Elettore.getUsernameElettore());
			preparedStatement.setLong(2,ID_Votazione.getIDvotazione());
			
			//System.out.println("----------------" + Elettore.getUsernameElettore() + ID_Votazione.getIDvotazione());
			String Username = "";
			
			//Esecuzione della query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("Select ELETTORE - Elettore ottenuto dal database con successo!");
			while (rs.next()) {
				Username = rs.getString("elettorechk");
			}	
			
			if (Username.isEmpty()) {
				chkPerform = false;
			}
			//System.out.println("Username = "+ Username + chkPerform);
			
		} catch (SQLException e) {
			System.out.println("Errore nella comunicazione con il database!" + e.toString());
		}
		return chkPerform;
	}

	@Override
	public boolean InsertVoto(Elettore Elettore, Votazione Votazione) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_elettorale_cl", "root", "root");//cambiare con info database locale
			System.out.println("Database connesso con successo!");
			
			PreparedStatement preparedStatement = connection.prepareStatement("insert into voto (elettorechk, votazionechk) values (?,?)");
			//Impostazione dei parametri della query
			preparedStatement.setString(1,Elettore.getUsernameElettore());
			preparedStatement.setLong(2,Votazione.getIDvotazione());
			        
			//Esecuzione della query
			preparedStatement.executeUpdate();
			
			System.out.println("Dati inseriti nel database con successo!");
			
			connection.close();
			System.out.println("Connessione con il database chiusa!");
			
			return true;
			
		} catch (SQLException e) {
			System.out.println("Errore nella comunicazione con il database!" + e.toString());
			return false;
		}
	}
	

}