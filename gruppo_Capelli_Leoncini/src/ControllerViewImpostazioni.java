import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

public class ControllerViewImpostazioni{

	private String Admin;
	
    @FXML
    private Button Btn_CreaVotazione;

    @FXML
    private Button Btn_TerminaVotazione;

    @FXML
    private Button Btn_VediVincitore;

    @FXML
    private ChoiceBox<String> ChoiceVictoryMode;

    @FXML
    private ChoiceBox<String> ChoiceVotazioneDaTerminare;

    @FXML
    private ChoiceBox<String> ChoiceVotazioneVincitore;

    @FXML
    private ChoiceBox<String> ChoiceVoteMode;
    
    @FXML
    private AnchorPane Creazione_Votazione;

    @FXML
    private ListView<String> LstViewCandidati;

    @FXML
    private Pane PaneCreazione;

    @FXML
    private TabPane TabPaneCreazione;

    @FXML
    private Tab TerminazioneScrutinio;

    @FXML
    private Tab VisualizzaRisultati;

    @FXML
    private TextField TextBoxTitolo;
    
    @FXML
    private Text TxtVincitore;
    
    private SingleManager Autenticazione = SingleManager.getInstance();
    private SingleAuditingLog Log = SingleAuditingLog.getInstance();
    @FXML
    void BtnCandidati_Click(ActionEvent event) {
    	
    	LstViewCandidati.setDisable(false);
    	
    	List<Candidato> lst = new ArrayList<Candidato>();
    	Autenticazione.getCandidati(lst);

    	for (Candidato c : lst) {
    		
    		/*if(c.getNomeCandidato().substring(0,1).equals("0")) {
    			LstViewCandidati.getItems().add(c.getNomeCandidato().substring(14, 16));
    		} else if(c.getNomeCandidato().substring(0,1).equals("1")) {
    			LstViewCandidati.getItems().add(c.getNomeCandidato().substring(9));
    		} else {*/
    			if(c.getPartito().isEmpty()) {
    			LstViewCandidati.getItems().add(c.getNomeCandidato());
    			}else {
    				LstViewCandidati.getItems().add(c.getNomeCandidato() + " Partito: " + c.getPartito());
    			}
    		//}
    		
    		
    	}
    	
    	LstViewCandidati.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	
    	Log.WriteLog("Inserimento Candidati");
    }
    
    public void setupPage(String usernameAdmin) {
    	
    	Admin = usernameAdmin;
    	
    	ChoiceVictoryMode.getItems().add("Maggioranza");
    	ChoiceVictoryMode.getItems().add("Maggioranza Assoluta");
    	ChoiceVictoryMode.getItems().add("Referendum Senza Quorum");
    	ChoiceVictoryMode.getItems().add("Referendum con Quorum");
    	
    	ChoiceVoteMode.getItems().add("Voto Ordinale");
    	ChoiceVoteMode.getItems().add("Voto Categorico");
    	ChoiceVoteMode.getItems().add("Voto Categorico Con Preferenze");
    	ChoiceVoteMode.getItems().add("Referendum");
    	
    	TabPaneCreazione.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
    		        @Override
    		        public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
    		        	
    		        	ChoiceVotazioneDaTerminare.getItems().clear();
    		        	ChoiceVotazioneDaTerminare.getItems().addAll(Autenticazione.getVotazioni(true));
    		        	
    		        	ChoiceVotazioneVincitore.getItems().clear();
    		        	ChoiceVotazioneVincitore.getItems().addAll(Autenticazione.getVotazioni(false));
    		        }
    		    }
    		);
    	
    	
    	
    }
    
    @FXML
    void CreaVotazioneClick(ActionEvent event) {
    	
    	String VoteMode = ChoiceVoteMode.getValue();
    	String VictoryMode = ChoiceVictoryMode.getValue();
    	String Titolo = TextBoxTitolo.getText();
    	
    	boolean error = false;
    	
    	List<Candidato> lst = new ArrayList<Candidato>();
    	if (Titolo.isEmpty()) {
    		error = true;
    	}
    	
    	
    	if (VoteMode.equals("Referendum") && (!(VictoryMode.equals("Referendum con Quorum") || VictoryMode.equals("Referendum Senza Quorum")))) {
    		System.out.println("Errore: " + VoteMode + " -- " + VictoryMode);
    		Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("ERROR");
        	alert.setHeaderText("ATTENZIONE!");
        	alert.setContentText("Impostazioni per referendum non valide");
        	alert.showAndWait().ifPresent(rs -> {
        	    if (rs == ButtonType.OK) {
        	        System.out.println("Pressed OK.");
        	    }
        	});   
        	return;
    	} else if (!(VoteMode.equals("Referendum")) && (VictoryMode.equals("Referendum con Quorum") || VictoryMode.equals("Referendum Senza Quorum"))){
    		System.out.println("Errore: " + VoteMode + " -- " + VictoryMode);
    		Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("ERROR");
        	alert.setHeaderText("ATTENZIONE!");
        	alert.setContentText("Impostazioni per Votazione non valide");
        	alert.showAndWait().ifPresent(rs -> {
        	    if (rs == ButtonType.OK) {
        	        System.out.println("Pressed OK.");
        	    }
        	});   
        	return;
    	}

    	if (VoteMode.equals("Voto Categorico Con Preferenze") && !VictoryMode.equals("Maggioranza Assoluta")) {
    		System.out.println("Errore: " + VoteMode + " -- " + VictoryMode);
    		Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("ERROR");
        	alert.setHeaderText("ATTENZIONE!");
        	alert.setContentText("In caso di Voto Categorico Con Preferenze selezionare Maggioranza Assoluta come modalità di vittoria");
        	alert.showAndWait().ifPresent(rs -> {
        	    if (rs == ButtonType.OK) {
        	        System.out.println("Pressed OK.");
        	    }
        	});   
        	return;
    	}
    	
    	for (String s :LstViewCandidati.getSelectionModel().getSelectedItems()) {
    		if (VoteMode.equals("Referendum")) 
    		{
    			if (!s.equals("0Referendum = SI")) {
    				if (!s.equals("0Referendum = NO")) {
    					error = true;
        			}
    			}
    		
    		}else {
    			if (s.equals("0Referendum = SI") || s.equals("0Referendum = NO")) {	error = true;	}
    		}
    		Candidato c = new Candidato(s,"");
    		if(s.contains(" Partito: ")) {
    			int index = s.indexOf(":");
    			String partito = s.substring(index+2);
    			String nomeCandidato = s.substring(0,index-8);
    			c = new Candidato(nomeCandidato,partito);
    		}
    		lst.add(c);
    		//System.out.println("nuovo candidato = |" + c.getNomeCandidato() + "| partito: |" + c.getPartito() );
    	}
    	
    	if (lst.size()<2) {	error = true;	}
    	

    	if (VoteMode.equals("Voto Categorico Con Preferenze")) {
    		boolean errorPreferenze = true;
    		for(Candidato c: lst ) {
    			if(c.getNomeCandidato().substring(0,1).equals("1")){
    					errorPreferenze = false;
    					System.out.println(c.getNomeCandidato() + " - " + c.getNomeCandidato().substring(0,1));
    			}
    		}
    		if (errorPreferenze) {
    			Alert alert = new Alert(AlertType.ERROR);
            	alert.setTitle("ERROR");
            	alert.setHeaderText("ATTENZIONE!");
            	alert.setContentText("Inserire almeno un Partito");
            	alert.showAndWait().ifPresent(rs -> {
            	    if (rs == ButtonType.OK) {
            	        System.out.println("Error Votazione con Preferenze --> Pressed OK. ");
            	    }
            	});   
            	return;
    		}
    	}
    	if (error) {
    		Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("ERROR");
        	alert.setHeaderText("ATTENZIONE!");
        	alert.setContentText("Verificare impostazioni della votazione! /n Inserire SI e NO come candidati in caso di Referendum /n I candidati non possono essere meno di 2");
        	alert.showAndWait().ifPresent(rs -> {
        	    if (rs == ButtonType.OK) {
        	        System.out.println("Generic Error on Votazione --> Pressed OK. ");
        	    }
        	});   
        	return;
    	}
    	
    	HashMap<Candidato, Integer> LstCandidati= new HashMap<Candidato, Integer>();
    	for(Candidato c : lst) {
    		LstCandidati.put(c, 0);
    	}
    	
    	
    	Votazione v = new Votazione(Titolo,VoteMode,VictoryMode,true,"",Admin, LstCandidati);
    	boolean ok = v.Save();
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("INFO STATUS");
    	alert.setHeaderText("ATTENZIONE!");
    	alert.setContentText("Votazione Creata con successo!");
    	if (ok ==  false) {alert.setContentText("Errore nella creazione della votazione!");}
    	alert.showAndWait().ifPresent(rs -> {
    	    if (rs == ButtonType.OK) {
    	        System.out.println("Pressed OK.");
    	    }
    	});   
    	
    	Log.WriteLog("Votazione creata");
    }

    @FXML
    void TerminaVotazioneCLick(ActionEvent event) {
    	String titolo = ChoiceVotazioneDaTerminare.getValue();
    	
    	//System.out.println("Pulsante Termina Premuto");
    	boolean vincitoreChk = Autenticazione.setVincitore(titolo);


        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("INFO STATUS");
        	alert.setHeaderText("ATTENZIONE!");
        	alert.setContentText("Votazione Terminata e Scrutinio Avvenuto con successo!");
        	if (vincitoreChk ==  false) {alert.setContentText("Errore nel calcolo dello scrutinio!");}
        	alert.showAndWait().ifPresent(rs -> {
        	    if (rs == ButtonType.OK) {
        	        System.out.println("Pressed OK.");
        	    }
        	});   
        	
        	Log.WriteLog("Votazione terminata e scrutinio avviato");
    }

    @FXML
    void VisualizzaVincitoreClick(ActionEvent event) {

    	String titolo = ChoiceVotazioneVincitore.getValue();
    	String vincitore = Autenticazione.getVincitore(titolo);
    	
    	TxtVincitore.setText(vincitore);
    	
    	Log.WriteLog("Visualizzazione del vincitore");
    }
    
}

