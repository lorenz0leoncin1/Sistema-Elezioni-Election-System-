import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ControllerAccessoElettore {

    @FXML
    private Button Loginelettore;

    @FXML
    private Label lblErrorLogin;
    
    @FXML
    private Label lblMessage;

    @FXML
    private PasswordField passwordelettore;

    @FXML
    private TextField usernameelettore;
    private SingleAuditingLog Log = SingleAuditingLog.getInstance();
    
    @FXML
    void HandleLogin(ActionEvent event) {
    	
    	lblErrorLogin.setVisible(false);
    	
    	if(!usernameelettore.getText().trim().isEmpty() && !passwordelettore.getText().trim().isEmpty()) {
    		//System.out.println(";;"+ usernameAdmin.getText() + passwordAdmin.getText() + ";;");
    		String usr = usernameelettore.getText().trim();
    		String psw = passwordelettore.getText().trim();
    		
    		System.out.println("dati inseriti da utente: " + usr + " : " + psw);
    		
    		Elettore el = new Elettore(usr,psw.toCharArray());
    		if (el.Login()) {
    			try {
    				System.out.println("nome ottenuto per benvenuto: " + el.getNome());
    				
    				
    				FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewVota.fxml"));       
					Parent root = loader.load();
					Stage stage = new Stage();
					stage.setTitle("Scheda Elettorale");
					stage.setScene(new Scene(root, 900,550));
    		        ControllerViewVota VotaCntrol = loader.getController();
    		        VotaCntrol.setupPage(el);
					stage.show();
    		        Stage thisStage = (Stage) Loginelettore.getScene().getWindow();
    		        thisStage.close();
    		        
    			}catch (Exception e){
    				System.out.println("errore ControllerElettore" + el.getNome() + "----" + e.toString());
    			}
    		}else {
        		lblErrorLogin.setText("Credenziali non valide. Inserire di nuovo per accedere!");
        		lblErrorLogin.setVisible(true);
        		Log.WriteLog("Errore login, credenziali non valide: " + usernameelettore.getText().trim());
    		}
    		
    		
    	} else {
    		lblErrorLogin.setText("Credenziali non presenti. Inserire di nuovo per accedere!");
    		lblErrorLogin.setVisible(true);
    		Log.WriteLog("Errore login, credenziali non inserite ");
    	}
    	
    	
    	Log.WriteLog("Accesso login come elettore: " + usernameelettore.getText().trim());

    }

}

