import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ButtonBar;

import javafx.event.ActionEvent;

public class ControllerViewVota {
    @FXML
    private Button BtnSu;
	
    @FXML
    private Button BtnGiu;

    @FXML
    private Button Btn_SelVotazione;

    @FXML
    private Button Btn_Vota;

    @FXML
    private ChoiceBox<String> ChoiceVotazione;

    @FXML
    private ListView<String> LstViewCandidati;

    @FXML
    private Pane PaneVota;

    @FXML
    private CheckBox ChkSchedaBianca;

    private List<Candidato> CandidatiScelti = new ArrayList<Candidato>();
    private Votazione v;
    private Elettore Elettor;
    private Voto vote;
    private SingleManager Autenticazione = SingleManager.getInstance();
    private SingleAuditingLog Log = SingleAuditingLog.getInstance();
    
    public void setupPage(Elettore el) {

        Elettor = el;

        ChoiceVotazione.getItems().clear();
        ChoiceVotazione.getItems().addAll(Autenticazione.getVotazioni(true));

    }
    
    @FXML
    void BtnVotaClick(ActionEvent event) {
    	
    	
    	if (ChkSchedaBianca.isSelected()) {
    		//vota scheda bianca
    		vote.Save(null);
    		
    	}else {
    		
    		if (LstViewCandidati.getSelectionModel().getSelectedItems().size() < 1 && !v.getTipo_voto().equals("Voto Ordinale")) {
    			Alert alert = new Alert(AlertType.ERROR);
            	alert.setTitle("ERROR");
            	alert.setHeaderText("ATTENZIONE!");
            	alert.setContentText("Selezionare il candidato che si vuole votare");
            	alert.showAndWait().ifPresent(rs -> {
            	    if (rs == ButtonType.OK) {
            	        System.out.println("Candidato non selezionato --> Pressed OK. ");
            	    }
            	});   
            return;	
    		}
    		
    		//System.out.println("Tipo Votazione = " + v.getTipo_voto() + " Candidato Selezionato = "+ LstViewCandidati.getSelectionModel().getSelectedItem());
    		
    		try {
    			
      		String s = "";
        		
    		switch(v.getTipo_voto()){
    		case "Referendum":
    			s = LstViewCandidati.getSelectionModel().getSelectedItem();
        		Candidato c = new Candidato(s,"");
        		if(s.contains(" Partito: ")) {
        			int index = s.indexOf(":");
        			String partito = s.substring(index+2);
        			String nomeCandidato = s.substring(0,index-8);
        			c = new Candidato(nomeCandidato,partito);
        		}
        		CandidatiScelti.add(c);
    			break;
    		
    		case "Voto Categorico":
    			s = LstViewCandidati.getSelectionModel().getSelectedItem();
        		c = new Candidato(s,"");
        		if(s.contains(" Partito: ")) {
        			int index = s.indexOf(":");
        			String partito = s.substring(index+2);
        			String nomeCandidato = s.substring(0,index-8);
        			c = new Candidato(nomeCandidato,partito);
        		}
        		CandidatiScelti.add(c);
    			break;
    		
    		case "Voto Categorico Con Preferenze":
    			String PartitoSelezionato = ""; boolean partitoChk = false;
    			for (String st :LstViewCandidati.getSelectionModel().getSelectedItems()) {
    				
            		c = new Candidato(st,"");
            		
            		if(st.contains(" Partito: ")) {
            			int index = st.indexOf(":");
            			String partito = st.substring(index+2);
            			System.out.println(st+index);
            			String nomeCandidato = st.substring(0,index-8);
            			System.out.println(nomeCandidato);
            			c = new Candidato(nomeCandidato,partito);
            			/*
            			if (!partito.equals(PartitoSelezionato)) {
            				Alert alert = new Alert(AlertType.ERROR);
                        	alert.setTitle("ERROR");
                        	alert.setHeaderText("ATTENZIONE!");
                        	alert.setContentText("Selezionare solo candidati del medesimo partito");
                        	alert.showAndWait().ifPresent(rs -> {
                        	    if (rs == ButtonType.OK) {
                        	        System.out.println("Candidato di diversi partiti --> Pressed OK. ");
                        	    }
                        	});   
                        return;	
                        
            			}
							*/
            		} else {
            			
            			if(partitoChk == false) {
            			c = new Candidato(st, "");
            			PartitoSelezionato = st.substring(9);
            			partitoChk = true;
            			}else {
                				Alert alert = new Alert(AlertType.ERROR);
                            	alert.setTitle("ERROR");
                            	alert.setHeaderText("ATTENZIONE!");
                            	alert.setContentText("Selezionare un solo partito");
                            	alert.showAndWait().ifPresent(rs -> {
                            	    if (rs == ButtonType.OK) {
                            	        System.out.println("Più partiti selezionati --> Pressed OK. ");
                            	    }
                            	});   
                            return;
                            }	
                		}
            		
            		
            		
            		CandidatiScelti.add(c);
    				}
    				
    				boolean errore = true;
    				for (Candidato ca : CandidatiScelti){
    						for (Candidato cc : CandidatiScelti) {
    							
    							if (ca.getNomeCandidato().substring(9).equals(cc.getPartito())) {
    								errore = false;
    							}
    							
    						}
    				}
    				
    				if (errore) {
        				Alert alert = new Alert(AlertType.ERROR);
                    	alert.setTitle("ERROR");
                    	alert.setHeaderText("ATTENZIONE!");
                    	alert.setContentText("Selezionare solo candidati del medesimo partito");
                    	alert.showAndWait().ifPresent(rs -> {
                    	    if (rs == ButtonType.OK) {
                    	        System.out.println("Candidato di diversi partiti --> Pressed OK. ");
                    	    }
                    	});   
                    return;	
                    
        			}
    			
    			break;
    		
    		case "Voto Ordinale":

    			for (String st :LstViewCandidati.getItems()) {
    				c = new Candidato(st,"");
    				if(st.contains(" Partito: ")) {
            			int index = st.indexOf(":");
            			String partito = st.substring(index+2);
            			System.out.println(st+index);
            			String nomeCandidato = st.substring(0,index-8);
            			c = new Candidato(nomeCandidato,partito);
    				}
    				CandidatiScelti.add(c);
    			}
    			
    			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        		alert.setTitle("Conferma del voto");
        		String candidati = "";
        		for (Candidato cA : CandidatiScelti) { candidati += cA.getNomeCandidato() + " "; }
        		alert.setContentText("Vuoi confermare il tuo voto per " + candidati + "?");
        		ButtonType okButton = new ButtonType("SI", ButtonBar.ButtonData.YES);
        		ButtonType noButton = new ButtonType("NO", ButtonBar.ButtonData.NO);
        		alert.getButtonTypes().setAll(okButton, noButton);
        		alert.showAndWait().ifPresent(type -> {
        			if (type == okButton) {
            			
            			for(int j = 0; j<CandidatiScelti.size(); j++) {
            				System.out.println(CandidatiScelti.get(j).getNomeCandidato() + " voti = " + (CandidatiScelti.size()-j) );
            				vote.Save(CandidatiScelti.get(j), CandidatiScelti.size()-j );
            				
            			}
    		        	
    		        	try {
    		        	FXMLLoader loader = new FXMLLoader(getClass().getResource("arrivederci.fxml"));       
    					Parent root = loader.load();
    					Stage stage = new Stage();
    					stage.setScene(new Scene(root, 825,301));
    					ControllerArrivederci ArrivederciCntrol = loader.getController();
    					ArrivederciCntrol.setName(Elettor.getNome());
    					stage.show();
        		        Stage thisStage = (Stage) Btn_Vota.getScene().getWindow();
        		        thisStage.close();
    		        	}catch(Exception e) {
    		        		e.printStackTrace();
    		        	}
    		        	
    		        } else if (type == noButton) {
    		        	return;
    		        }
        		});

    			
    		/*	ordine = 0;
    			
    			for (String st :LstViewCandidati.getItems()) {
    				System.out.println("candidati in lista = " + st);
    				c = new Candidato(st,"");
    				CandidatiScelti.add(c);
    				System.out.println("non crashato qui");
    			}
    			
    			List<Candidato> ListaSuDB = Autenticazione.getCandidatiVotazione(v);
    			
    			for (int x = 0; x < v.getListaCandidati().size(); x++) {
    				//System.out.println("lista: " + ListaSuDB.get(x).getNomeCandidato() + "x=" + x);
    			}
    			
    			
    			for(int j = 0; j<CandidatiScelti.size(); j++) {
    				
    				for (int i = 0; i<ListaSuDB.size(); i ++) {
    					
    					//System.out.println( "LstView: " +CandidatiScelti.get(j).getNomeCandidato() + "DB: "+ListaSuDB.get(i).getNomeCandidato() );
    					
    					if (CandidatiScelti.get(j).getNomeCandidato().equals(ListaSuDB.get(i).getNomeCandidato())) {
    						if (j==0) {	ordine = i; } else { ordine = ordine*10+i; }    						
    						}
    					}
    					
    				}
    
    			System.out.println("ordine:" + ordine);*/
    			break;

    		}
    	
    		} catch (Exception e) { System.out.println(e.toString()); }
    		
    		if(!v.getTipo_voto().equals("Voto Ordinale")) {
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    		alert.setTitle("Conferma del voto");
    		String candidati = "";
    		for (Candidato c : CandidatiScelti) { candidati += c.getNomeCandidato() + " "; }
    		alert.setContentText("Vuoi confermare il tuo voto per " + candidati + "?");
    		ButtonType okButton = new ButtonType("SI", ButtonBar.ButtonData.YES);
    		ButtonType noButton = new ButtonType("NO", ButtonBar.ButtonData.NO);
    		alert.getButtonTypes().setAll(okButton, noButton);
    		alert.showAndWait().ifPresent(type -> {
    			if (type == okButton) {
		        	for (Candidato c : CandidatiScelti) { vote.Save(c); }
		        	
		        	try {
		        	FXMLLoader loader = new FXMLLoader(getClass().getResource("arrivederci.fxml"));       
					Parent root = loader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root, 825,301));
					ControllerArrivederci ArrivederciCntrol = loader.getController();
					ArrivederciCntrol.setName(Elettor.getNome());
					stage.show();
    		        Stage thisStage = (Stage) Btn_Vota.getScene().getWindow();
    		        thisStage.close();
		        	}catch(Exception e) {
		        		e.printStackTrace();
		        	}
		        	
		        } else if (type == noButton) {
		        	return;
		        }
    		});
    		
    		}
    		
    	}
    	
    	Log.WriteLog("Voto Avvenuto" + Elettor.getUsernameElettore());
    	
    }

    @FXML
    void SchedaBiancaClick(ActionEvent event) {

    	if (LstViewCandidati.isDisabled()) {
    		LstViewCandidati.setDisable(false);
    	}else {
    		LstViewCandidati.setDisable(true);
    	}
    	
    	Log.WriteLog("Scheda bianca abilitata/disabilitata");
    }

    @FXML
    void SelVotazioneClick(ActionEvent event) {
    	
    	LstViewCandidati.getItems().clear();
    	String titolo = ChoiceVotazione.getValue();
    	v = Autenticazione.SelectVotazione(titolo);
    	
    	List<Candidato> lst = Autenticazione.getCandidatiVotazione(v);

    	for (Candidato c : lst) {
    			if(c.getPartito().isEmpty()) {
    				LstViewCandidati.getItems().add(c.getNomeCandidato());
    			}else {
    				LstViewCandidati.getItems().add(c.getNomeCandidato()+ " Partito: " + c.getPartito());
    			}
    		}
    	
    	vote = new Voto(Elettor, v);
    	boolean Check = Autenticazione.CheckVotoElettore(vote);
    	
    	if (Check) {
    		Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("ERROR");
        	alert.setHeaderText("ATTENZIONE!");
        	alert.setContentText("Il voto per la Votazione è gia stato effettuato, si prega di selezionare una nuova Votazione");
        	alert.showAndWait().ifPresent(rs -> {
        	    if (rs == ButtonType.OK) {
        	        System.out.println("Voto per la votazione già presente --> Pressed OK. ");
        	    }
        	});   
        	
        	v = null;
        	LstViewCandidati.getItems().clear();
        	return;
    	}else {
    		BtnSu.setDisable(true);
			BtnGiu.setDisable(true);
    		if(v.getTipo_voto().equals("Voto Categorico Con Preferenze")) {
    			LstViewCandidati.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    		}else if (v.getTipo_voto().equals("Voto Ordinale")){
    			BtnSu.setDisable(false);
    			BtnGiu.setDisable(false);
    		}
    	}
    	
    	Log.WriteLog("Votazione Selezionata");
    	
    	}

    @FXML
    void BtnSuClick(ActionEvent event) {

    	//System.out.println("su pigiato " + LstViewCandidati.getSelectionModel().getSelectedIndex() + " (" + LstViewCandidati.getSelectionModel().getSelectedItem() + ")");
    	List<String> SortLstView = new ArrayList<String>();
    	
    	for(String item : LstViewCandidati.getItems()) {
    		SortLstView.add(item);
    		//System.out.println(item);
    	}
    	
    	for(String item : SortLstView) {
    		//System.out.println(item);
    		
    		if(item.equals(LstViewCandidati.getSelectionModel().getSelectedItem())){
    			
    			//System.out.println("trovato");
    			
    			if(LstViewCandidati.getSelectionModel().getSelectedIndex()>0) {
    				
    			String prec = SortLstView.get(LstViewCandidati.getSelectionModel().getSelectedIndex()-1);
    			
    			SortLstView.set(LstViewCandidati.getSelectionModel().getSelectedIndex(), prec);
    			
    			SortLstView.set(LstViewCandidati.getSelectionModel().getSelectedIndex()-1, item);
    			
    			//System.out.println("prec = "+ prec);
    			}
    	}
    	}
    	
    	LstViewCandidati.getItems().clear();
    	
    	for (String s : SortLstView) {

			LstViewCandidati.getItems().add(s);
			//System.out.println(s + SortLstView.size());
		}
    	
    	Log.WriteLog("Modifica Ordine Candidati");
    }
    

    @FXML
    void BtnGiuClick(ActionEvent event) {

    	List<String> SortLstView = new ArrayList<String>();
    	
    	for(String item : LstViewCandidati.getItems()) {
    		SortLstView.add(item);
    	}
    	
    	for(String item : SortLstView) {
    		
    		if(item.equals(LstViewCandidati.getSelectionModel().getSelectedItem())){

    			if(LstViewCandidati.getSelectionModel().getSelectedIndex()<SortLstView.size()-1) {
    				
    			String suc = SortLstView.get(LstViewCandidati.getSelectionModel().getSelectedIndex()+1);
    			
    			
    			SortLstView.set(LstViewCandidati.getSelectionModel().getSelectedIndex(), suc);
    			
    			SortLstView.set(LstViewCandidati.getSelectionModel().getSelectedIndex()+1, item);
    			
    			break;
    			}
    		}
    	}
    	
    	LstViewCandidati.getItems().clear();
    	
    	for (String s : SortLstView) {

			LstViewCandidati.getItems().add(s);
		}
    	Log.WriteLog("Modifica Ordine Candidati");
    }
    	
    }
