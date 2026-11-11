package models;

import javafx.stage.Stage;

import java.io.IOException;

abstract public class user {
    private String username;
    private String password;

    user(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getRole(){
        return username;
    }

    public String getPassword(){
        return password;
    }


    public abstract void accessDashboard(Stage stage) throws IOException;
}
