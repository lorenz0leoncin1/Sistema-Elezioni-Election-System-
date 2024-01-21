
public class Candidato {

	private String nomeCandidato;
	private String Partito;
	
	public Candidato(String nome, String Partito) {
		setNomeCandidato(nome);
		setPartito(Partito);
	}

	public String getNomeCandidato() {
		return nomeCandidato;
	}

	public void setNomeCandidato(String nomeCandidato) {
		this.nomeCandidato = nomeCandidato;
	}

	public String getPartito() {
		return Partito;
	}

	public void setPartito(String partito) {
		Partito = partito;
	}
	
}
