package models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class userManager {

    private String currentRole;
    public void setRole(String role) {
        this.currentRole = role;
    }

    public String getRole(){
        return currentRole;
    }

    public void switchScene(Stage stage, String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(user.class.getResource(fxmlPath));
        stage.setScene(new Scene(loader.load()));
        stage.centerOnScreen();
        stage.show();
    }
}
