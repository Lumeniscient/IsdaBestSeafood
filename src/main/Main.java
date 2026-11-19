
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage stage)  throws Exception {
            stage.setTitle("Login");
            stage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("/app/adminDashboard.fxml"))));
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();

    }

    public static void main(String[] args){
        launch();

    }

}
