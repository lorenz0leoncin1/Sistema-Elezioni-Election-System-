import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerArrivederci {

    @FXML
    private Label lblBenvenuto;

    @FXML
    private Label lblNome;

    
    public void setName(String name) {
        lblNome.setText(name + "");
    }
}
