import java.util.List;

public interface VotazioneDAO {

	public Votazione selectVotazione(String titoloVotazione, boolean stato);
	public void InsertVotazione(Votazione v);
	public void UpdateVotazione(Votazione v,String vincitore, boolean stato);
	public List<String> GetVotazioni(boolean stato);
}
