package controller;
import javafx.stage.Stage;
import models.user;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.userAdmin;
import java.io.IOException;


public class loginController {
    @FXML private TextField txtUsername, txtPassword;


    @FXML
    private void handleLogin() throws IOException {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();
        Stage stage = (Stage) txtUsername.getScene().getWindow();
        if (username.equals("Admin") && password.equals("A123")) {
            user admin = new userAdmin(username, password);
            admin.accessDashboard(stage);
        }
    }
}
