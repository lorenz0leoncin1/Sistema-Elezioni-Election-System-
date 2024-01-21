
public class Amministratore {

	private String UsernameAmministratore;
	private char[] PswAmministratore;
	private String nome;
	
	public Amministratore(String Usr, char[] PswAmministratore) {
		setUsernameAmministratore(Usr);
		setPswAmministratore(PswAmministratore);
	}

	public String getUsernameAmministratore() {
		return UsernameAmministratore;
	}

	public void setUsernameAmministratore(String usernameAmministratore) {
		UsernameAmministratore = usernameAmministratore;
	}

	public char[] getPswAmministratore() {
		return PswAmministratore;
	}

	public void setPswAmministratore(char[] PswAmministratore) {
		this.PswAmministratore = PswAmministratore;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	public boolean Login() {
		
		boolean resultCheckAuth = false ;
		SingleManager Autenticazione = SingleManager.getInstance();
		String nomeAdmin = Autenticazione.CheckAmministratore(UsernameAmministratore, PswAmministratore);
		if (nomeAdmin!= null) {
			resultCheckAuth = true;
			this.setNome(nomeAdmin);
		}
		
		return resultCheckAuth;
	}
	
}
