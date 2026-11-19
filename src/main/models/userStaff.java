package models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class userStaff extends user{

    public userStaff(String username, String password){
        super(username, password);
    }

    @Override
    public void accessDashboard(Stage stage) throws IOException {
        stage.setTitle("Staff Dashboard");
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(userAdmin.class.getResource("/app/staffDashboard.fxml")))));
        stage.centerOnScreen();

        stage.show();
    }
}
