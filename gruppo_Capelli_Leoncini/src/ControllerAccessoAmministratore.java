import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerAccessoAmministratore /*implements Initializable*/{

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblErrorLogin;

    @FXML
    private Label lblMessage;

    @FXML
    private PasswordField passwordAdmin;

    @FXML
    private TextField usernameAdmin;
    
    private SingleAuditingLog Log = SingleAuditingLog.getInstance();
    @FXML
    void handleLogin(ActionEvent event) {
    	lblErrorLogin.setVisible(false);
    	
    	if(!usernameAdmin.getText().trim().isEmpty() && !passwordAdmin.getText().trim().isEmpty()) {
    		//System.out.println(";;"+ usernameAdmin.getText() + passwordAdmin.getText() + ";;");
    		String usr = usernameAdmin.getText().trim();
    		String psw = passwordAdmin.getText().trim();
    		
    		System.out.println("dati inseriti da utente: " + usr + " : " + psw);
    		
    		Amministratore a = new Amministratore(usr,psw.toCharArray());
    		if (a.Login()) {
    			try {
    				System.out.println("nome ottenuto per benvenuto: " + a.getNome());
    				
    			/*	FXMLLoader loader = new FXMLLoader(getClass().getResource("benvenuto.fxml"));       
					Parent root = loader.load();
    		        ControllerBenvenuto BenvenutoCntrol = loader.getController();
    		        BenvenutoCntrol.setName("Amministratore: "+ a.getNome());
    		        btnLogin.getScene().setRoot(root); */
    				
    				FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewImpostazioni.fxml"));       
					Parent root = loader.load();
					Stage stage = new Stage();
					stage.setTitle("Impostazione Votazione");
					stage.setScene(new Scene(root, 923,625));
					ControllerViewImpostazioni ImpostazioniCntrol = loader.getController();
					ImpostazioniCntrol.setupPage(a.getUsernameAmministratore());
					stage.show();
    		        Stage thisStage = (Stage) btnLogin.getScene().getWindow();
    		        thisStage.close();
    		        
    			}catch (Exception e){
    				System.out.println("errore ControllerAmministratore " + a.getNome() + " ----- " + e.toString());
    			}
    		}else {
        		lblErrorLogin.setText("Credenziali non valide. Inserire di nuovo per accedere!");
        		lblErrorLogin.setVisible(true);
    		}
    		
    		
    	} else {
    		lblErrorLogin.setText("Credenziali non presenti. Inserire di nuovo per accedere!");
    		lblErrorLogin.setVisible(true);
    	}
    	
    	Log.WriteLog("Accesso login come amministratore: " + usernameAdmin.getText().trim());
    	
    }
/*
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		lblErrorLogin.setText("prova initialize");
		lblErrorLogin.setVisible(true);
		
		
	}
*/
}



/*
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class ControllerAccessoAmministratore {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField lblName;

    @FXML
    private TextField lblNumber;

    @FXML
    private Button btnOK;
    
    @FXML
    private Label lblMessage;

    @FXML
    void handleName(ActionEvent event) {

    }

    @FXML
    void handleNumber(ActionEvent event) {

    }

    @FXML
    void handleOK(ActionEvent event) {
    	//System.out.println("Button pressed");
    	//String messaggio = "Button pressed";
    	lblMessage.setVisible(true);
    	
    	String numS = lblNumber.getText();
    	Integer num = Integer.parseInt(numS);
    	
    	String message;
    	if (num>1) {
    		message = "vi";
    	} else {
    		message = "ti";
    	}
    	String messaggio = lblName.getText() + ", " + message + " aspettiamo!";
        lblMessage.setText(messaggio);
    }

    @FXML
    void initialize() {
        assert lblName != null : "fx:id=\"lblName\" was not injected: check your FXML file 'ex.fxml'.";
        assert lblNumber != null : "fx:id=\"lblNumber\" was not injected: check your FXML file 'ex.fxml'.";
        assert btnOK != null : "fx:id=\"btnOK\" was not injected: check your FXML file 'ex.fxml'.";
        assert lblMessage != null : "fx:id=\"lblMessage\" was not injected: check your FXML file 'ex.fxml'.";
        lblMessage.setVisible(false);
    }
}*/



