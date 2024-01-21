import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AmministratoreDAOImpl implements AmministratoreDAO {

	//METODO SELECT DA DATABASE
	@Override
	public Amministratore selectAmminstratore(String Username) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_elettorale_cl", "root", "root");//cambiare con info database locale
			System.out.println("Database connesso con successo!");
			
			PreparedStatement preparedStatement = connection.prepareStatement("select * from amministratore where UsernameAdmin = ?");
			//Impostazione dei parametri della query
			preparedStatement.setString(1,Username);
			        
			//Esecuzione della query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("Dati ottenuti dal database con successo!");
			
			String usr = "";
			String psw = "";
			String name = "";
			
			while (rs.next()) {
				
			usr = rs.getString("UsernameAdmin");
			psw = rs.getString("PasswordAdmin");
			name = rs.getString("NomeAdmin");
			
			//System.out.println("usrdbiml: " + usr + "psdwdbimpl: " + psw + "nomedbimpl: " + name);
			}
			connection.close();
			System.out.println("Connessione con il database chiusa!");
			
			//creazione e restituzione dell'oggetto
			char[]pswchar = psw.toCharArray();
			Amministratore a = new Amministratore(usr,pswchar);
			a.setNome(name);
			return a;
			
		} catch (SQLException e) {
			System.out.println("Errore nella comunicazione con il database!");
		}
		
		return null;
	}

	//-------------------------------------------------------------------------------------------------
	//metodi implementati per completezza ma non necessari e non utillizzati
	@Override
	public void updateAmministartore(String Username, String nuovapsw) {

		   try {
			   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_elettorale_cl", "root", "root");//cambiare con info database locale
			   System.out.println("Connected With the database successfully");

			           PreparedStatement preparedStatement=connection.prepareStatement("update amministratore set PasswordAdmin = ? where UsernameAdmin = ?");
			           preparedStatement.setString(1,nuovapsw);
			           preparedStatement.setString(2,Username);
			         
			           preparedStatement.executeUpdate();
			           System.out.println("data updated successfully");
			           connection.close();
			           
		   } catch (SQLException ex) {
			   System.out.println("Error while connecting to the database");
		   }
		
	}

	@Override
	public void deleteAmministratore(String Username) {
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_elettorale_cl", "root", "root");//cambiare con info database locale
			System.out.println("Connected With the database successfully");

			        PreparedStatement preparedStatement = connection.prepareStatement("delete from amministratore where UsernameAdmin=?");
			        preparedStatement.setString(1,Username);
			        preparedStatement.executeUpdate();
			        System.out.println("Data deleted Successfully");
			        connection.close();
		} catch (SQLException ex) {
			System.out.println("Error while connecting to the database");
		}

		
	}

}
