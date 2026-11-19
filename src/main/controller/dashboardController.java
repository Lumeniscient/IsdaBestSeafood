package controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import models.userAdmin;
import models.userManager;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.Objects;


public class dashboardController{
    @FXML Button btnDashboard, btnNewOrder, btnMenu, btnSales;

    userManager user = new userManager();

//    public void initialize(){
//        setRole("Admin");
//    }

    public void setRole(String role){
        if (role.equals("Admin")){
            btnDashboard.setVisible(true);
            btnNewOrder.setVisible(true);
            btnMenu.setVisible(true);
            btnSales.setVisible(true);
        }
    }

    @FXML
    public void handleMenu() throws IOException {
        Stage stage = (Stage) btnMenu.getScene().getWindow();
        user.switchScene(stage, "/app/menu.fxml");

    }

}

