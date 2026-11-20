package models;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

 public abstract class user {
    private String username;
    private String password;


    user(String username, String password){
        this.username = username;
        this.password = password;
    }


    public abstract void accessDashboard(Stage stage) throws IOException;
}
