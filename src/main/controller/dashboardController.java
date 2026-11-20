package controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import models.*;

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

import java.util.ArrayList;


public class dashboardController extends userManager{

    @FXML private Button btnDashboard, btnNewOrder, btnMenu, btnSales;
    @FXML private Label lblTotalSales, lblTotalOrders, lblDashboard, lblDate;
    manageInventory inventory = manageInventory.getInstance();


    public void initialize() {

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        lblDate.setText(today.format(formatter));
        lblDate.setFont(Font.font("Berlin Sans FB", 15));
        if (getRole().equals("Admin")) {
            lblDashboard.setText("Admin Dashboard");
            btnMenu.setVisible(true);
            btnSales.setVisible(true);
        } else if (getRole().equals("Staff")) {
            lblDashboard.setText("Staff Dashboard");
            btnMenu.setVisible(false);
            btnSales.setVisible(false);
        }

        if (inventory.getInventory().isEmpty()) {
            inventory.addProduct(new product("Sizzling Bangus", 280, 23));
            inventory.addProduct(new product("Calamaris", 430, 12));
            inventory.addProduct(new product("Daing na Bangus", 389, 14));
            inventory.addProduct(new product("Sweet and Sour Fish Fillet", 380, 5));
            inventory.addProduct(new product("Salt and Pepper Fish", 350, 27));
            inventory.sortProduct();
        }
        int totalOrders = transactions.getTransactionHistory().size();
        double totalSales = transactions.getTransactionHistory().stream()
                .flatMap(ArrayList::stream)
                .mapToDouble(orderItem::getSubtotal)
                .sum();

        lblTotalOrders.setText(String.valueOf(totalOrders));
        lblTotalSales.setText(String.format("₱%.2f", totalSales));

        btnDashboard.setDisable(true);
        btnMenu.setDisable(false);
        btnNewOrder.setDisable(false);
        btnSales.setDisable(false);
    }

    @FXML
    public void handleMenu() throws IOException {
        Stage stage = (Stage) btnDashboard.getScene().getWindow();
        switchScene(stage, "/app/menu.fxml");

    }

    @FXML
    private void handleLogout() throws IOException {
        Stage stage = (Stage) btnDashboard.getScene().getWindow();
        switchScene(stage, "/app/login.fxml");

    }

    @FXML
    private void handleOrder() throws IOException {
        Stage stage = (Stage) btnDashboard.getScene().getWindow();
        switchScene(stage, "/app/newOrder.fxml");

    }

    @FXML
    private void handleSales() throws IOException {
        Stage stage = (Stage) btnDashboard.getScene().getWindow();
        switchScene(stage, "/app/sales.fxml");
    }
}

