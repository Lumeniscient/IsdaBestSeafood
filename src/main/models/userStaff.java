package models;

import controller.dashboardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class userStaff extends user{

    public userStaff(String username, String password){
        super(username, password);
        userManager user = new userManager();
        user.setRole("Staff");
    }

    @Override
    public void accessDashboard(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(userAdmin.class.getResource("/app/dashboard.fxml")));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Staff Dashboard");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
