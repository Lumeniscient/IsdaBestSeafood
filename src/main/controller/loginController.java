package controller;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.user;
import javafx.fxml.FXML;
import models.userAdmin;
import models.userStaff;


import java.io.IOException;


public class loginController {


    @FXML private TextField txtUsername, txtPassword;

    @FXML
    private void handleLogin() throws IOException {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();
        Stage stage = new Stage();
        if (username.equals("Admin") && password.equals("A123")) {
            user admin = new userAdmin(username, password);
            admin.accessDashboard(stage);
            txtUsername.getScene().getWindow().hide();
        } else if (username.equals("Staff") && password.equals("S123")) {
            user staff = new userStaff(username, password);
            staff.accessDashboard(stage);
        }

    }


}
