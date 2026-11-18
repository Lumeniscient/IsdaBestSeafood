package controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.userAdmin;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

import java.util.Objects;

public class dashboardController extends loginController{
    @FXML Button btnDashboard, btnNewOrder, btnMenu, btnSales;
    loginController user = new loginController();



    void accessMenu(Stage stage) throws IOException {
        stage.setTitle("Menu");
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(userAdmin.class.getResource("/app/menu.fxml")))));
        stage.centerOnScreen();
        stage.show();
    }

    public void handleMenu() throws IOException {
        Stage stage = (Stage) btnMenu.getScene().getWindow();
        accessMenu(stage);

    }

}

