import java.util.List;

public interface CandidatiDAO {
	
	   public void selectCandidati(List<Candidato> listaC);
	   public Candidato selectCandidato(String nome);
	   public List<Candidato> selectCandidatoPartito(String partitoInput);
}