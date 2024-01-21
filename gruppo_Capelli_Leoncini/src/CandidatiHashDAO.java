import java.util.List;

public interface CandidatiHashDAO {
	public List<Candidato>	getCandidatiVotazione(Votazione v);
	public int SelectNumeroVoti(Votazione v, Candidato c);
	public boolean UpdateVotiCandidato(CandidatiHash ch);
}
