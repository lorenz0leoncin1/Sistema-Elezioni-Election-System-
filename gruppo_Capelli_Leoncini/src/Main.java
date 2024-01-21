
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

	//CLASSE MAIN PER FACILITARE CAMBIO DI SCENA E AVVIO INIZIALE DELL'APPLICAZIONE 
	//CAPELLI STEFANO 922842 - LEONCINI LORENZO 930937
public class Main extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception{
    	stg = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("startpage.fxml"));
        primaryStage.setTitle("LOGIN SISTEMA ELETTORALE");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void changeScene(String fxml, int x, int y) throws IOException {
    	Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.setScene(new Scene(pane, x,y));
        stg.getScene().setRoot(pane);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
