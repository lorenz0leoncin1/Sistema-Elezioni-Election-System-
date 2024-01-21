import java.util.HashMap;
import java.util.List;

public class Votazione {

	HashMap<Candidato, Integer> ListaCandidati = new HashMap<Candidato, Integer>();
	private int IDvotazione;
	private String tipo_voto;
	private String mod_vittoria;
	private boolean stato;
	private String Vincitore;
	private String Admin;
	private String titolo_votazione;

	
	public Votazione(String titolo_votazione,String tipo_voto, String mod_vittoria, boolean Stato, String Vincitore, String Admin,HashMap<Candidato, Integer> Lc ) {
		
		setTipo_voto(tipo_voto);
		setTitolo_votazione(titolo_votazione);
		setMod_vittoria(mod_vittoria);
		setStato(Stato);
		setVincitore(Vincitore);
		setAdmin(Admin);
		setListaCandidati(Lc);
	}


	public String getTipo_voto() {
		return tipo_voto;
	}


	public void setTipo_voto(String tipo_voto) {
		
			this.tipo_voto = tipo_voto;
	}


	public String getMod_vittoria() {
		return mod_vittoria;
	}


	public void setMod_vittoria(String mod_vittoria) {
		this.mod_vittoria = mod_vittoria;
	}


	public boolean getStato() {
		return stato;
	}


	public void setStato(boolean stato) {
		this.stato = stato;
	}


	public String getVincitore() {
		return Vincitore;
	}


	public void setVincitore(String vincitore) {
		Vincitore = vincitore;
	}


	public String getAdmin() {
		return Admin;
	}


	public void setAdmin(String nomeAdmin) {
		Admin = nomeAdmin;
	}


	public String getTitolo_votazione() {
		return titolo_votazione;
	}


	public void setTitolo_votazione(String titolo_votazione) {
		this.titolo_votazione = titolo_votazione;
	}


	public int getIDvotazione() {
		return IDvotazione;
	}
	
	public void setIDvotazione(int ID) {
		this.IDvotazione = ID;
	}
	
	public boolean Save() {
		
		boolean resultInsert = false;
		SingleManager Autenticazione = SingleManager.getInstance();
		resultInsert = Autenticazione.SaveVotazioneDB(this);
		
		return resultInsert;
	}


	public HashMap<Candidato, Integer> getListaCandidati() {
		return ListaCandidati;
	}


	public void setListaCandidati(HashMap<Candidato, Integer> listaCandidati) {
		ListaCandidati = listaCandidati;
	}
	
}
