package models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import models.userManager;
public class userAdmin extends user{

    public userAdmin(String username, String password){
        super(username, password);
        userManager user = new userManager();
        user.setRole("Admin");
    }

    @Override
    public void accessDashboard(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(userAdmin.class.getResource("/app/dashboard.fxml")));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Admin Dashboard");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

}
