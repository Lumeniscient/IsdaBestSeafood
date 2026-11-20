package controller;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.user;
import javafx.fxml.FXML;
import models.userAdmin;
import models.userManager;
import models.userStaff;


import java.io.IOException;


public class loginController extends userManager {

    @FXML private TextField txtUsername, txtPassword;

    @FXML
    private void handleLogin() throws IOException {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();
        Stage stage = (Stage) txtUsername.getScene().getWindow();
        if (username.equals("Admin") && password.equals("A123")) {
            setRole("Admin");
            user admin = new userAdmin(username, password);
            userManager.addUser(admin);
            admin.accessDashboard(stage);

        } else if (username.equals("Staff") && password.equals("S123")) {
            setRole("Staff");
            user staff = new userStaff(username, password);
            userManager.addUser(staff);
            staff.accessDashboard(stage);
        }

    }


}
