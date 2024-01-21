
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerStartPage {

    @FXML
    private Button BtnAdmin;

    @FXML
    private Button BtnElettore;

    private SingleAuditingLog Log = SingleAuditingLog.getInstance();
    
    @FXML
    void handleAdmin(ActionEvent event) throws IOException {
    	Main m = new Main();
    	m.changeScene("accessoamministratore.fxml", 300, 210);
    	Log.WriteLog("Selezione amministratore Start Page ");
    }

    @FXML
    void handleElettore(ActionEvent event) throws IOException {
    	Main m = new Main();
    	m.changeScene("accessoelettore.fxml", 300,210);
    	Log.WriteLog("Selezione elettore Start Page ");
    }

}
