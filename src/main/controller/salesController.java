package controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.orderItem;
import models.orders;
import models.userManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ArrayList;
import java.util.stream.Collectors;

import models.transactions;

public class salesController extends userManager {

    private final LocalDate today = LocalDate.now();


    @FXML private TableView<ArrayList<orderItem>> tblTransactions;
    @FXML private TableColumn<ArrayList<orderItem>, String> colTime;
    @FXML private TableColumn<ArrayList<orderItem>, String> colItems;
    @FXML private TableColumn<ArrayList<orderItem>, Double> colTotal;

    @FXML private Button btnMenu, btnDashboard, btnNewOrder, btnSales;
    @FXML private Label lblTotalEarnings, lblTotalOrder, lblDate;

    @FXML
    private void initialize() {
        colTime.setCellValueFactory(cellData -> {
            int index = tblTransactions.getItems().indexOf(cellData.getValue());
            return new SimpleStringProperty(transactions.getTransactionTime(index));
        });
        colItems.setCellValueFactory(cellData -> {
            String items = cellData.getValue().stream()
                    .map(i -> i.getOrderProduct().getProductName() + " ×" + i.getQuantity())
                    .collect(Collectors.joining(", "));
            return new SimpleStringProperty(items);
        });
        colTotal.setCellValueFactory(cellData -> {
            double total = cellData.getValue().stream().mapToDouble(orderItem::getSubtotal).sum();
            return new SimpleDoubleProperty(total).asObject();
        });

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        lblDate.setText(today.format(formatter));
        if (getRole().equals("Staff")) {
            btnMenu.setVisible(false);
            btnSales.setVisible(false);
        }
        btnDashboard.setDisable(false);
        btnMenu.setDisable(false);
        btnNewOrder.setDisable(false);
        btnSales.setDisable(true);
        refresh();
    }

    private void refresh() {
        tblTransactions.setItems(FXCollections.observableArrayList(transactions.getTransactionHistory()));

        int totalOrders = transactions.getTransactionHistory().size();
        double totalRevenue = transactions.getTransactionHistory().stream().flatMap(ArrayList::stream).mapToDouble(orderItem::getSubtotal).sum();
        double avgOrder;

        if (totalOrders == 0) {
            avgOrder = 0;
        } else {
            avgOrder = totalRevenue / totalOrders;
        }

        lblTotalOrder.setText(String.valueOf(totalOrders));
        lblTotalEarnings.setText(String.format("₱%.2f", totalRevenue));
    }



    @FXML
    private void handleDashboard() throws IOException {
        if (Objects.equals(getRole(), "Admin")){
            Stage stage = (Stage) btnDashboard.getScene().getWindow();
            switchScene(stage, "/app/dashboard.fxml");
        }else if (Objects.equals(getRole(), "Staff")){
            Stage stage = (Stage) btnDashboard.getScene().getWindow();
            switchScene(stage, "/app/dashboard.fxml");
        }

    }

    @FXML
    private void handleMenu() throws IOException {
        if (Objects.equals(getRole(), "Admin")){
            Stage stage = (Stage) btnDashboard.getScene().getWindow();
            switchScene(stage, "/app/menu.fxml");
        }else if (Objects.equals(getRole(), "Staff")){
            Stage stage = (Stage) btnDashboard.getScene().getWindow();
            switchScene(stage, "/app/menu.fxml");
        }

    }

    @FXML
    private void handleNewOrder() throws IOException {
        if (Objects.equals(getRole(), "Admin")){
            Stage stage = (Stage) btnDashboard.getScene().getWindow();
            switchScene(stage, "/app/newOrder.fxml");
        }else if (Objects.equals(getRole(), "Staff")){
            Stage stage = (Stage) btnDashboard.getScene().getWindow();
            switchScene(stage, "/app/newOrder.fxml");
        }
    }

    @FXML
    private void handleLogout() throws IOException {
        Stage stage = (Stage) btnDashboard.getScene().getWindow();
        switchScene(stage, "/app/login.fxml");

    }
}