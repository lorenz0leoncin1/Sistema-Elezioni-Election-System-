
public class CandidatiHash {
	
	private Votazione v;
	private Candidato c;
	private int NumeroVoti;

	public CandidatiHash(Votazione v, Candidato c, int numeroVoti) {
		setV(v);
		setC(c);
		setNumeroVoti(numeroVoti);
	}

	public Votazione getV() {
		return v;
	}
	public void setV(Votazione v) {
		this.v = v;
	}
	public Candidato getC() {
		return c;
	}
	public void setC(Candidato c) {
		this.c = c;
	}
	public int getNumeroVoti() {
		return NumeroVoti;
	}
	public void setNumeroVoti(int numeroVoti) {
		NumeroVoti = numeroVoti;
	}
	
	
}
