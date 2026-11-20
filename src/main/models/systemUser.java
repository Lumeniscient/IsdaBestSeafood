package models;

import javafx.stage.Stage;

import java.io.IOException;

public abstract class systemUser {
    abstract String getRole();
    abstract void switchScene(Stage stage, String path) throws IOException;
}
