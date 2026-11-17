package controller;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.user;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import models.userAdmin;
import models.userStaff;

import java.io.IOException;


public class loginController {
    @FXML private TextField txtUsername, txtPassword;
    @FXML private Button btnLogin;

    @FXML
    public void initialize(){
        btnLogin.setOnMousePressed(e -> btnLogin.setStyle("-fx-background-color: derive(#00ffad, -30%);; -fx-background-radius: 15;"));
        btnLogin.setOnMouseReleased(e -> btnLogin.setStyle(""));
    }


    @FXML
    private void handleLogin() throws IOException {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();
        Stage stage = (Stage) txtUsername.getScene().getWindow();
        if (username.equals("Admin") && password.equals("A123")) {
            user admin = new userAdmin(username, password);
            admin.accessDashboard(stage);
        } else if (username.equals("Staff") && password.equals("S123")) {
            user staff = new userStaff(username, password);
            staff.accessDashboard(stage);
        }
    }
}
