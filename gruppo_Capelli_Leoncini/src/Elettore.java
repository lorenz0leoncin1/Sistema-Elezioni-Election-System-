
public class Elettore {

	private String UsernameElettore;
	private char[] PswElettore;
	private String nome;
	
	public Elettore(String Usr, char[] PswElettore) {
		setUsernameElettore(Usr);
		setPswElettore(PswElettore);
	}

	public String getUsernameElettore() {
		return UsernameElettore;
	}

	public void setUsernameElettore(String usernameElettore) {
		UsernameElettore = usernameElettore;
	}

	public char[] getPswElettore() {
		return PswElettore;
	}

	public void setPswElettore(char[] PswElettore) {
		this.PswElettore = PswElettore;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean Login() {
		SingleManager Autenticazione = SingleManager.getInstance();
		boolean resultCheckAuth = false ;
		String nomeElettore = Autenticazione.CheckElettore(UsernameElettore, PswElettore);
		if (nomeElettore!= null) {
			resultCheckAuth = true;
			this.setNome(nomeElettore);
		}
		
		return resultCheckAuth;
	}
}
