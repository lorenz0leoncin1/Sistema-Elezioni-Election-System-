import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class SingleManager {
	   //create an object of SingleObject
	   private static SingleManager instance = new SingleManager();

	   //make the constructor private so that this class cannot be
	   //instantiated
	   private SingleManager(){}

	   //Get the only object available
	   public static SingleManager getInstance(){
	      return instance;
	   }
	//private Autenticazione() {}
	
	public  String CheckAmministratore(String Username, char[] PasswordChar) {
		
		AmministratoreDAOImpl dao = new AmministratoreDAOImpl();
		Amministratore a = dao.selectAmminstratore(Username);
		
		String pswdb = new String(a.getPswAmministratore());
		System.out.println("urs db aut:" + a.getUsernameAmministratore() + "pswdb aut: "+ pswdb + "nomedb aut: "+ a.getNome() );
		
    	//String s = "";
    	try {
    	
    	String Password = new String(PasswordChar);
    	MessageDigest md = MessageDigest.getInstance("MD5");
    	md.update(Password.getBytes());
    	byte[] digest = md.digest();
    	
    	String pswtoCheck = Base64.getEncoder().encodeToString(digest);
    	System.out.println("ursInserito:" + Username + "pswdbInseritaMD5: "+ pswtoCheck);
    	
    	for(int x=0;x<pswtoCheck.length();x++) {
    		if (pswtoCheck.toCharArray()[x]!=a.getPswAmministratore()[x]) {
    			a.setNome(null);
    		}
    	}
    	
    	
    	
    	}catch (Exception e) {
    		System.out.println(e.toString());
    	}
    	
    	//System.out.println(usernameEl + " : " +pswEl + " MD5: " + s);
		
		return a.getNome();
	}
	
	public  String CheckElettore(String Username, char[] PasswordChar) {
		
		ElettoreDAOImpl dao = new ElettoreDAOImpl();
		Elettore el = dao.selectElettore(Username);
		
		String pswdb = new String(el.getPswElettore());
		System.out.println("urs db aut:" + el.getUsernameElettore() + "pswdb aut: "+ pswdb + "nomedb aut: "+ el.getNome() );
		
    	//String s = "";
    	try {
    	
    	String Password = new String(PasswordChar);
    	MessageDigest md = MessageDigest.getInstance("MD5");
    	md.update(Password.getBytes());
    	byte[] digest = md.digest();
    	
    	String pswtoCheck = Base64.getEncoder().encodeToString(digest);
    	System.out.println("ursInserito:" + Username + "pswdbInseritaMD5: "+ pswtoCheck);
    	
    	for(int x=0;x<pswtoCheck.length();x++) {
    		if (pswtoCheck.toCharArray()[x]!=el.getPswElettore()[x]) {
    			el.setNome(null);;
    		}
    	}
    	
    	
    	}catch (Exception ex) {
    		System.out.println(ex.toString());
    	}
    	
    	//System.out.println(usernameEl + " : " +pswEl + " MD5: " + s);
		
		return el.getNome();
	}
	
	public  boolean SaveVotazioneDB(Votazione v) {
		
		System.out.println("Arrivo in Autenticazione....");
		VotazioneDAOImpl dao = new VotazioneDAOImpl();
		try {
		dao.InsertVotazione(v);
		}catch (Exception e)
		{
			System.out.println("errore salvataggio votazione su DB" + e.toString());
			return false;
		}
		return true;
	}
	
	public  void getCandidati(List<Candidato> listaC) {
		
		CandidatiDAOImpl dao = new CandidatiDAOImpl();
		dao.selectCandidati(listaC);
		
	}
	
	public  List<String> getVotazioni(boolean stato){
		
		VotazioneDAOImpl dao = new VotazioneDAOImpl();		
		return dao.GetVotazioni(stato);
	}
	
	public  boolean setVincitore(String titolo_v) {
		
		VotazioneDAOImpl dao = new VotazioneDAOImpl();
		CandidatiDAOImpl daoCandidati = new CandidatiDAOImpl();
		Votazione v = dao.selectVotazione(titolo_v, true);
		String vincitore = "";
		
		System.out.println("calcolo del vincitore...");
		
		System.out.println(v.getTipo_voto());
		
		if (v.getTipo_voto().equals("Referendum")) {
			//50milioni maggioranza = 25.000.001
			

			int max = -1;
			int TotaleVoti = 0;
			System.out.println(v.getMod_vittoria());
			
			switch(v.getMod_vittoria()) {
				case "Referendum Senza Quorum":
					System.out.println("nel primo");
					for (Candidato c : v.getListaCandidati().keySet()) {
					if (v.getListaCandidati().get(c) > max) {
					max = v.getListaCandidati().get(c);
					vincitore = c.getNomeCandidato();
					}
					}
					vincitore = vincitore.substring(14,16);
					
				break;
				
				case "Referendum con Quorum":
					System.out.println("nel secondo");
					for (Candidato c : v.getListaCandidati().keySet()) {
						TotaleVoti += v.getListaCandidati().get(c);
						if (v.getListaCandidati().get(c) > max) {
						max = v.getListaCandidati().get(c);
						vincitore = c.getNomeCandidato();
						}
					}
					vincitore = vincitore.substring(14,16);
					if(TotaleVoti<25000001) {
						vincitore = "numero minimo di votanti non raggiunto";
						System.out.println(TotaleVoti);
					}
					
					
					
				break;
				}
			
			} else {
			
			int max = -1;
			int TotaleVoti = -1;
			
				switch(v.getMod_vittoria()) {
				case "Maggioranza":
				
				
					for (Candidato c : v.getListaCandidati().keySet()) {
					if (v.getListaCandidati().get(c) > max) {
						max = v.getListaCandidati().get(c);
						vincitore = c.getNomeCandidato();
						}
					}
					
					if (vincitore.substring(0).equals("1")) {
					vincitore = vincitore.substring(9);
					}
					
					
					
				break;
			
				case "Maggioranza Assoluta":
						TotaleVoti = 0;
					for (Candidato c : v.getListaCandidati().keySet()) {
						
						if (v.getListaCandidati().get(c) > max) {
						max = v.getListaCandidati().get(c);
						vincitore = c.getNomeCandidato();
						}
						TotaleVoti = TotaleVoti + v.getListaCandidati().get(c);
					}
					if(max<(TotaleVoti*0.5)+1) {
						vincitore = "maggioranza non raggiunta";
					}
					
					if (vincitore.substring(0).equals("1")) {
						vincitore = vincitore.substring(9);
					}
				break;
			
			
			}
				
			if(v.getTipo_voto().equals("Voto Categorico Con Preferenze")) {
					String CandidatoMax = ""; int maxC = -1;
					for (Candidato c : daoCandidati.selectCandidatoPartito(vincitore)) {
						for (Candidato cv : v.getListaCandidati().keySet()) {
							if(c.getNomeCandidato().equals(cv.getNomeCandidato())) {
								if(v.getListaCandidati().get(cv) > maxC) {
									maxC = v.getListaCandidati().get(cv);
									CandidatoMax = cv.getNomeCandidato();
								}else if(v.getListaCandidati().get(cv) == maxC) {
									CandidatoMax = CandidatoMax + " " + cv.getNomeCandidato();
								}
							}
						}
					}
					vincitore = vincitore + " " + CandidatoMax;
			}
			
			/*if (v.getTipo_voto().equals("Voto Ordinale") && vincitore.equals("maggioranza non raggiunta")) {
				
				while(vincitore.equals("maggioranza non raggiunta")) {
					
					List<Candidato> ListaSuDB = this.getCandidatiVotazione(v);
					int min = v.getListaCandidati().get(0);
					Candidato minc = null;
					CandidatiHashDAOImpl CHDAO = new CandidatiHashDAOImpl();
					
					for (Candidato c : v.getListaCandidati().keySet()) {
						TotaleVoti = v.getListaCandidati().get(c);
						if (v.getListaCandidati().get(c) < min) {
						min = v.getListaCandidati().get(c);
						minc = c;
					}
						int posmin = -1;
						for (int x = 0; x < ListaSuDB.size(); x++) {
							if(minc.getNomeCandidato().equals(ListaSuDB.get(x).getNomeCandidato())) {
								posmin = x;
							}
						}
						
						for (Integer s: daoScheda.SelectOrdini(v)) {
							int primonumero = s / (10^(s.toString().length()-1));
							if(primonumero == posmin) {
								s = Integer.parseInt(s.toString().substring(0));
								int primonumeronuovo =  s / (10^(s.toString().length()-1));
								
								int votiCandidato = CHDAO.SelectNumeroVoti(v, ListaSuDB.get(primonumeronuovo));
								votiCandidato = votiCandidato + min;
								CandidatiHash ch= new CandidatiHash(v,c,votiCandidato);
								CHDAO.UpdateVotiCandidato(ch);
								//DA COMPLETARE
								//daoScheda.UpdateScheda(v, );
								
							}
						}
					
						if(max<(TotaleVoti*0.5)+1) {
							vincitore = "maggioranza non raggiunta";
						}
					}
					if (vincitore.substring(0).equals("1")) {
						vincitore = vincitore.substring(9);
					}
					

				}
			}*/
		}
		
		dao.UpdateVotazione(v, vincitore, false);
		
		return true;
		
	}
	
	public  String getVincitore(String titolo_v) {
		
		VotazioneDAOImpl dao = new VotazioneDAOImpl();
		Votazione v = dao.selectVotazione(titolo_v, false);
		
		return v.getVincitore();
	}
	
	public  List<Candidato> getCandidatiVotazione(Votazione v){
		
		CandidatiHashDAOImpl dao = new CandidatiHashDAOImpl();
		return dao.getCandidatiVotazione(v);
		
	}
	
	public  Votazione SelectVotazione(String titolo) {
		
		VotazioneDAOImpl dao = new VotazioneDAOImpl();
		return dao.selectVotazione(titolo, true);
		
	}
	
	public  boolean Vota(Voto v, Candidato c) {
        boolean esito = false;
		VotoDAOImpl daoVo = new VotoDAOImpl();
        
        
		if (c==null) {
			esito = daoVo.InsertVoto(v.getElettore(), v.getVotazione());
		}else {
		
        int votiCandidato = -1;
        CandidatiHash ch = null;
        CandidatiHashDAOImpl daoCH = new CandidatiHashDAOImpl();
        
		switch(v.getVotazione().getTipo_voto()) {
		case "Voto Categorico":	
			daoVo.InsertVoto(v.getElettore(), v.getVotazione());
			votiCandidato = daoCH.SelectNumeroVoti(v.getVotazione(), c);
			votiCandidato = votiCandidato + 1;
			ch= new CandidatiHash(v.getVotazione(),c,votiCandidato);
			esito = daoCH.UpdateVotiCandidato(ch);
			break;
		
		case "Referendum":
			daoVo.InsertVoto(v.getElettore(), v.getVotazione());
			votiCandidato = daoCH.SelectNumeroVoti(v.getVotazione(), c);
			votiCandidato = votiCandidato + 1;
			ch= new CandidatiHash(v.getVotazione(),c,votiCandidato);
			esito = daoCH.UpdateVotiCandidato(ch);
			break;
		
		case "Voto Categorico Con Preferenze":
	        if (!daoVo.SelectVoto(v.getElettore(), v.getVotazione()))
	        {daoVo.InsertVoto(v.getElettore(), v.getVotazione());}
			votiCandidato = daoCH.SelectNumeroVoti(v.getVotazione(), c);
			System.out.println("voti per " + c.getNomeCandidato()+ " = "+ votiCandidato);
			votiCandidato = votiCandidato + 1;
			ch= new CandidatiHash(v.getVotazione(),c,votiCandidato);
			esito = daoCH.UpdateVotiCandidato(ch);
			break;
			}
		}
        return esito;
        
	}
	
	public  boolean Vota(Voto v, Candidato c, int valorevoto) {
		VotoDAOImpl daoVo = new VotoDAOImpl();
        CandidatiHashDAOImpl daoCH = new CandidatiHashDAOImpl();

        boolean esito = false;
        
        if (!daoVo.SelectVoto(v.getElettore(), v.getVotazione()))
        {daoVo.InsertVoto(v.getElettore(), v.getVotazione());}
        int VotiCandidato = 0;
        VotiCandidato = daoCH.SelectNumeroVoti(v.getVotazione(), c);
        VotiCandidato = VotiCandidato+valorevoto;
        CandidatiHash ch = new CandidatiHash(v.getVotazione(), c, VotiCandidato);
        System.out.println(v.getVotazione().getTitolo_votazione() + c.getNomeCandidato());
        esito = daoCH.UpdateVotiCandidato(ch);
        return esito;
        
	}
	
	public  boolean CheckVotoElettore(Voto v) {
		
		VotoDAOImpl dao = new VotoDAOImpl();
		boolean check = dao.SelectVoto(v.getElettore(), v.getVotazione());
		return check;
		
	}
	
	
	//metodo non effettivamente implementato ma utilizzato per mostrare il testing del metodo che calcola il vincitore
	public String setVincitorePerTest(Votazione v) {
		
		String vincitore = "";
		
		System.out.println("calcolo del vincitore test...");
		
		if (v.getTipo_voto().equals("Referendum")) {
			//50milioni maggioranza = 25.000.001
			
			int max = -1;
			int TotaleVoti = 0;
			System.out.println(v.getMod_vittoria());
			
			switch(v.getMod_vittoria()) {
				case "Referendum Senza Quorum":
					//System.out.println("nel primo");
					
					for (Candidato c : v.getListaCandidati().keySet()) {
						//System.out.println("dentro al ciclo");
						//System.out.println(c.getNomeCandidato() + " " + v.getListaCandidati().get(c));
						
					if (v.getListaCandidati().get(c) > max) {
						max = v.getListaCandidati().get(c);
						vincitore = c.getNomeCandidato();
						}
					}
					
					//vincitore = vincitore.substring(14,16);
					
				break;
				
				case "Referendum con Quorum":
					System.out.println("nel secondo");
					for (Candidato c : v.getListaCandidati().keySet()) {
						//System.out.println("dentro al ciclo");
						//System.out.println(c.getNomeCandidato() + " " + v.getListaCandidati().get(c));
						TotaleVoti += v.getListaCandidati().get(c);
						if (v.getListaCandidati().get(c) > max) {
						max = v.getListaCandidati().get(c);
						vincitore = c.getNomeCandidato();
						}
						//System.out.println("numero di voti totale= " + TotaleVoti);
					}
					
					if(TotaleVoti<25000001) {
						vincitore = "numero minimo di votanti non raggiunto";
						//System.out.println("Voti totali= "+TotaleVoti);
					}

				break;
				}
			
			} else {
			
			int max = -1;
			int TotaleVoti = 0;
			
				switch(v.getMod_vittoria()) {
				case "Maggioranza":
				
					for (Candidato c : v.getListaCandidati().keySet()) {
					if (v.getListaCandidati().get(c) > max) {
						max = v.getListaCandidati().get(c);
						vincitore = c.getNomeCandidato();
						}
					}
					
					if (vincitore.substring(0).equals("1")) {
					vincitore = vincitore.substring(9);
					}

				break;
			
				case "Maggioranza Assoluta":
					for (Candidato c : v.getListaCandidati().keySet()) {
						
						if (v.getListaCandidati().get(c) > max) {
						max = v.getListaCandidati().get(c);
						vincitore = c.getNomeCandidato();
						}
						TotaleVoti = TotaleVoti + v.getListaCandidati().get(c);
					
					}
					
					//System.out.println(max + " - " + TotaleVoti + " - "+ ((TotaleVoti*0.5)+1));
					if(max<(TotaleVoti*0.5)+1) {
						vincitore = "maggioranza non raggiunta";
					}

				break;
			}
			}
		
		return vincitore;
		
	}
	
}
