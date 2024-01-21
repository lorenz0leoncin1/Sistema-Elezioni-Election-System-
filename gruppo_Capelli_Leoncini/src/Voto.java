import java.util.List;

public class Voto {
	
	private Elettore elettore;
	private Votazione votazione;
	
	public Voto(Elettore e, Votazione v) {
		
		setElettore(e);
		setVotazione(v);
		
	}

	public Elettore getElettore() {
		return elettore;
	}

	public void setElettore(Elettore elettore) {
		this.elettore = elettore;
	}

	public Votazione getVotazione() {
		return votazione;
	}

	public void setVotazione(Votazione votazione) {
		this.votazione = votazione;
	}
	
	
	public boolean Save(Candidato c) {
		SingleManager Autenticazione = SingleManager.getInstance();
		return Autenticazione.Vota(this, c);
	}
	
	public boolean Save (Candidato c,int ValoreVoto) {
		SingleManager Autenticazione = SingleManager.getInstance();
		return Autenticazione.Vota(this, c,ValoreVoto);
	}
}