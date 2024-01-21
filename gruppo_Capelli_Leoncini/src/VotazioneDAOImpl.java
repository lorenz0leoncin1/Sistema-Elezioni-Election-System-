import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VotazioneDAOImpl implements VotazioneDAO {
	
	//METODO SELECT DA DATABASE
		@Override
		public Votazione selectVotazione(String TitoloVotazione, boolean statoin) {
			try {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_elettorale_cl", "root", "root");//cambiare con info database locale
				System.out.println("VotazioneDAOImpl - Database connesso con successo!");
				
				PreparedStatement preparedStatement = connection.prepareStatement("select * from votazione where titolo_votazione = ? AND stato = ?");
				//Impostazione dei parametri della query
				preparedStatement.setString(1,TitoloVotazione);
				if (statoin == false) { preparedStatement.setString(2,"0"); } else { preparedStatement.setString(2,"1"); }
				        
				//Esecuzione della query
				ResultSet rs = preparedStatement.executeQuery();
				System.out.println("Votazione ottenuta dal database con successo!");
				
				int idVotazione = -1;
				String tipo = "";
				String mod_v = "";
				String vincitore = "";
				boolean stato = false;
				String admin = "";
				
				
				while (rs.next()) {
					
					idVotazione = rs.getInt("id_votazione");
					tipo = rs.getString("tipo_voto");
					mod_v = rs.getString("mod_vittoria");
					vincitore = rs.getString("vincitore");
					stato = rs.getBoolean("stato");
					admin = rs.getString("admin");
					
				}
				
				preparedStatement = connection.prepareStatement("select * from candidati_hash where votazione = ?");
				preparedStatement.setInt(1, idVotazione);
				rs = preparedStatement.executeQuery();
				
				System.out.println("CandidatiHash recuperati con successo");
				
				HashMap<Candidato, Integer> ListaCandidati = new HashMap<Candidato, Integer>();
				CandidatiDAOImpl dao = new CandidatiDAOImpl();
				
				while (rs.next()) {
					
					ListaCandidati.put(new Candidato(rs.getString("candidato"), dao.selectCandidato(rs.getString("candidato")).getPartito()), rs.getInt("numeroVoti"));
					
				}
				
				
				connection.close();
				System.out.println("Connessione con il database chiusa!");
				
				
				//creazione e restituzione dell'oggetto
				Votazione v = new Votazione(TitoloVotazione, tipo,mod_v,stato,vincitore,admin,ListaCandidati );
				v.setIDvotazione(idVotazione);
				return v;
				
				
			} catch (SQLException e) {
				System.out.println("Errore nella comunicazione con il database!");
			}
			
			return null;
		}
		
		
		@Override
		public void InsertVotazione(Votazione v) {
			
			try {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_elettorale_cl", "root", "root");//cambiare con info database locale
				System.out.println("Database connesso con successo!");
				
				PreparedStatement preparedStatement = connection.prepareStatement("insert into votazione (tipo_voto,mod_vittoria,stato,vincitore,admin,titolo_votazione) values (?,?,?,?,?,?)");
				//Impostazione dei parametri della query
				preparedStatement.setString(1,v.getTipo_voto());
				preparedStatement.setString(2,v.getMod_vittoria());
				if (v.getStato() == false) { preparedStatement.setString(3,"0"); } else { preparedStatement.setString(3,"1"); }
				preparedStatement.setString(4,v.getVincitore());
				preparedStatement.setString(5,v.getAdmin());
				preparedStatement.setString(6,v.getTitolo_votazione());
				        
				//Esecuzione della query
				preparedStatement.executeUpdate();
				
				int vID = this.selectVotazione(v.getTitolo_votazione(), true).getIDvotazione();
				
				v.setIDvotazione(vID);
				System.out.println("Votazione di cui inserire i candidati: " + v.getIDvotazione());
				
				
				for (Candidato i : v.getListaCandidati().keySet()) {
					
					System.out.println("Inserimento candidato: " + i.getNomeCandidato());
					preparedStatement = connection.prepareStatement("insert into candidati_hash (votazione,candidato,numeroVoti) values (?,?,?)");
					preparedStatement.setInt(1, v.getIDvotazione());
					preparedStatement.setString(2, i.getNomeCandidato());
					preparedStatement.setInt(3, v.getListaCandidati().get(i));
					preparedStatement.executeUpdate();
					
				}
				
				System.out.println("Dati inseriti nel database con successo!");
				
				connection.close();
				System.out.println("Connessione con il database chiusa!");
				
			} catch (SQLException e) {
				System.out.println("Errore nella comunicazione con il database!" + e.toString());
			}
			
		}


		@Override
        public void UpdateVotazione(Votazione v,String vincitore, boolean stato) {
            try {
            	
            	System.out.println(stato);
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_elettorale_cl", "root", "root");//cambiare con info database locale
                System.out.println("UPDATE VOTAZIONE - Database connesso con successo!");

                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE votazione SET stato = ? , vincitore = ? WHERE titolo_votazione = ?;");
                //Impostazione dei parametri della query
                if (stato == false) { preparedStatement.setString(1,"0"); } else { preparedStatement.setString(1,"1"); }
                preparedStatement.setString(2,vincitore);
                preparedStatement.setString(3,v.getTitolo_votazione());

                //Esecuzione della query
                preparedStatement.executeUpdate();
                System.out.println("UPDATE VOTAZIONE - Dati Aggiornati nel database con successo!");

                connection.close();
                System.out.println("UPDATE VOTAZIONE - Connessione con il database chiusa!");

            } catch (SQLException e) {
                System.out.println("UPDATE VOTAZIONE - Errore nella comunicazione con il database!");
            }
        }


		@Override
		public List<String> GetVotazioni(boolean stato) {
			try {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_elettorale_cl", "root", "Ste5cape99!");//cambiare con info database locale
				System.out.println("VotazioneDAOImpl - Database connesso con successo!");
				
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT titolo_votazione FROM votazione WHERE stato=?;");
				//Impostazione dei parametri della query
				if (stato == false) { preparedStatement.setString(1,"0"); } else { preparedStatement.setString(1,"1"); }        
				
				//Esecuzione della query
				ResultSet rs = preparedStatement.executeQuery();
				System.out.println("Votazione ottenuta dal database con successo!");
				
				List<String> Votazioni = new ArrayList<String>();
				
				while (rs.next()) {
					
					Votazioni.add(rs.getString("titolo_votazione"));
					
					
				}
				
				return Votazioni;
				
			} catch (SQLException e) {
				System.out.println("Errore nella comunicazione con il database!");
				return null;
			}
			
		}

		
}
