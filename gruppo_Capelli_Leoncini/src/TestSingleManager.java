import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

public class TestSingleManager{
		
	   //Test per verificare che SingleManager restituisca una lista di Votazioni se sono presenti con stato = 1 e quindi attive
	   @Test
	   public void testVotazioniPresenti() {	  
		   Assert.assertNotNull(SingleManager.getInstance().getVotazioni(true));
	   }
	   
	   //Test per verificare che SingleManager restituisca una lista di Votazioni se sono presenti con stato = 0 e quindi NON attive (terminate)
	   @Test
	   public void testVotazioniTerminate() {	  
		   Assert.assertNotNull(SingleManager.getInstance().getVotazioni(false));
	   }
	   
	   
	   //Test per referendum Senza Quorums
	   @Test
	   public void testVincitoreSQ() {
		   HashMap<Candidato, Integer> ListaCandidati = new HashMap<Candidato, Integer>();
		   ListaCandidati.put(new Candidato("Test1", ""), 10);
		   ListaCandidati.put(new Candidato("Test2", ""), 8);
		   Votazione v = new Votazione("votazioneProva", "Referendum", "Referendum Senza Quorum", true, "", "1", ListaCandidati);
		   String vincitore = SingleManager.getInstance().setVincitorePerTest(v);
		   System.out.println("vincitore = " + vincitore);
		   Assert.assertTrue(vincitore.equals("Test1"));
	   }
	   
	   //Test per referendum con Quorums in cui NON si raggiunge il numero minimo di voti (numero minimo = 25.000.001)
	   @Test
	   public void testVincitoreCQ1() {
		   HashMap<Candidato, Integer> ListaCandidati = new HashMap<Candidato, Integer>();
		   ListaCandidati.put(new Candidato("Test1", ""), 156600);
		   ListaCandidati.put(new Candidato("Test2", ""), 350000);
		   Votazione v = new Votazione("votazioneProva", "Referendum", "Referendum con Quorum", true, "", "1", ListaCandidati);
		   String vincitore = SingleManager.getInstance().setVincitorePerTest(v);
		   System.out.println("vincitore = " + vincitore);
		   Assert.assertTrue(vincitore.equals("numero minimo di votanti non raggiunto"));
	   }
	   
	   //Test per referendum con Quorums in cui si raggiunge il numero minimo di voti (numero minimo = 25.000.001)
	   @Test
	   public void testVincitoreCQ2() {
		   HashMap<Candidato, Integer> ListaCandidati = new HashMap<Candidato, Integer>();
		   ListaCandidati.put(new Candidato("Test1", ""), 18000000);
		   ListaCandidati.put(new Candidato("Test2", ""), 10000000);
		   Votazione v = new Votazione("votazioneProva", "Referendum", "Referendum con Quorum", true, "", "1", ListaCandidati);
		   String vincitore = SingleManager.getInstance().setVincitorePerTest(v);
		   System.out.println("vincitore = " + vincitore);
		   Assert.assertTrue(vincitore.equals("Test1"));
	   }
	   
	   //Test per una qualsiasi votazione Ordinale o Categorica con vittoria per Maggioranza
	   @Test
	   public void testVincitoreM() {
		   HashMap<Candidato, Integer> ListaCandidati = new HashMap<Candidato, Integer>();
		   ListaCandidati.put(new Candidato("Test1", ""), 180);
		   ListaCandidati.put(new Candidato("Test2", ""), 181);
		   Votazione v = new Votazione("votazioneProva", "Voto Categorico", "Maggioranza", true, "", "1", ListaCandidati);
		   String vincitore = SingleManager.getInstance().setVincitorePerTest(v);
		   System.out.println("vincitore = " + vincitore);
		   Assert.assertTrue(vincitore.equals("Test2"));
	   }
	   
	   //Test per una qualsiasi votazione Ordinale o Categorica con vittoria per Maggioranza Assoluta in cui NON si ottiene la maggioranza dei voti
	   @Test
	   public void testVincitoreMA1() {
		   HashMap<Candidato, Integer> ListaCandidati = new HashMap<Candidato, Integer>();
		   ListaCandidati.put(new Candidato("Test1", ""), 5);
		   ListaCandidati.put(new Candidato("Test2", ""), 5);
		   Votazione v = new Votazione("votazioneProva", "Voto Categorico", "Maggioranza Assoluta", true, "", "1", ListaCandidati);
		   String vincitore = SingleManager.getInstance().setVincitorePerTest(v);
		   System.out.println("vincitore = " + vincitore);
		   Assert.assertTrue(vincitore.equals("maggioranza non raggiunta"));
	   }
	   
	   //Test per una qualsiasi votazione Ordinale o Categorica con vittoria per Maggioranza Assoluta in cui si ottiene la maggioranza dei voti
	   @Test
	   public void testVincitoreMA2() {
		   HashMap<Candidato, Integer> ListaCandidati = new HashMap<Candidato, Integer>();
		   ListaCandidati.put(new Candidato("Test1", ""), 6);
		   ListaCandidati.put(new Candidato("Test2", ""), 4);
		   Votazione v = new Votazione("votazioneProva", "Voto Categorico", "Maggioranza Assoluta", true, "", "1", ListaCandidati);
		   String vincitore = SingleManager.getInstance().setVincitorePerTest(v);
		   System.out.println("vincitore = " + vincitore);
		   Assert.assertTrue(vincitore.equals("Test1"));
	   }
	   
}
