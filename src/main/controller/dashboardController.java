package controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class dashboardController{
    @FXML Button btnDashboard, btnNewOrder, btnMenu, btnSales;
    @FXML Label lblDate;

    userManager user = new userManager();

    public void initialize(){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        String formattedDate = today.format(formatter);
        lblDate.setFont(Font.font("Berlin Sans FB", 15));
        lblDate.setText(formattedDate);
    }

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
        Stage stage = new Stage();
        user.switchScene(stage, "/app/menu.fxml");
        btnDashboard.getScene().getWindow().hide();
    }

    @FXML
    private void HandleLogout() throws IOException {
        Stage stage = new Stage();
        user.switchScene(stage, "/app/login.fxml");
        btnDashboard.getScene().getWindow().hide();
    }

    @FXML
    private void handleOrder() throws IOException {
        Stage stage = new Stage();
        user.switchScene(stage, "/app/newOrder.fxml");
        btnDashboard.getScene().getWindow().hide();
    }

    @FXML
    private void handleSales() throws IOException {
        Stage stage = new Stage();
        user.switchScene(stage, "/app/sales.fxml");
        btnDashboard.getScene().getWindow().hide();
    }
}

