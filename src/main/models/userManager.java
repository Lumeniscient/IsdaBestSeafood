package models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class userManager extends systemUser{


    private static String currentRole;
    public void setRole(String role) {
        currentRole = role;
    }

    public String getRole(){
        return currentRole;
    }

    private static ArrayList<user> userList = new ArrayList<>();

    public static void addUser(user user) {
        userList.add(user);
    }

    public static ArrayList<user> getUsers() {
        return userList;
    }
    public void switchScene(Stage stage, String fxmlPath) throws IOException {

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(userAdmin.class.getResource(fxmlPath)));
        stage.setScene(new Scene(loader.load()));
        stage.centerOnScreen();
        stage.show();
    }
}
