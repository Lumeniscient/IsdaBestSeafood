
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Login");
            stage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("/app/login.fxml"))));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        launch();
    }

}
