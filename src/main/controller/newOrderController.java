package controller;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.manageInventory;
import models.orderItem;
import models.product;
import models.orders;
import models.userManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class newOrderController extends userManager {
    @FXML private TableView<product> tblOrder;
    @FXML private TableColumn<product, String> colName;
    @FXML private TableColumn<product, Double> colPrice;
    @FXML private TableColumn<product, Integer> colStock;
    @FXML private TextField txtQuantity;
    @FXML private ListView<orderItem> lstOrderList;
    @FXML private Button btnMenu, btnDashboard, btnNewOrder, btnSales, btnConfirm;
    @FXML private Label lblDate;

    manageInventory inventory = manageInventory.getInstance();
    orders orders = models.orders.getInstance();
    @FXML
    private void initialize(){
        colName.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getProductName()));
        colPrice.setCellValueFactory(c->new SimpleDoubleProperty(c.getValue().getProductPrice()).asObject());
        colStock.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getProductStock()).asObject());
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        lblDate.setText(today.format(formatter));

        if (getRole().equals("Staff")) {
            btnMenu.setVisible(false);
            btnSales.setVisible(false);
        }
        btnDashboard.setDisable(false);
        btnMenu.setDisable(false);
        btnNewOrder.setDisable(true);
        btnSales.setDisable(false);

        refresh();

    }
    private void refresh(){
        tblOrder.getItems().setAll(inventory.getInventory());;
        lstOrderList.getItems().setAll(orders.getOrders());
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
    private void handleSales() throws IOException {
        if (Objects.equals(getRole(), "Admin")){
            Stage stage = (Stage) btnDashboard.getScene().getWindow();
            switchScene(stage, "/app/sales.fxml");
        }else if (Objects.equals(getRole(), "Staff")){
            Stage stage = (Stage) btnDashboard.getScene().getWindow();
            switchScene(stage, "/app/sales.fxml");
        }
    }

    @FXML
    private void handleLogout() throws IOException {
        Stage stage = (Stage) btnDashboard.getScene().getWindow();
        switchScene(stage, "/app/login.fxml");

    }



    @FXML
    private void handleAdd(){
        try {
            btnConfirm.setDisable(false);
            int quantity = Integer.parseInt(txtQuantity.getText().trim());
            product selected = tblOrder.getSelectionModel().getSelectedItem();

            if (selected == null) {
                showAlert(Alert.AlertType.WARNING, "No item selected", "Please select a product from the table first.");
                return;
            }
            if (quantity <= 0){
                showAlert(Alert.AlertType.WARNING, "Invalid Quantity", "Quantity must be greater than 0.");
                return;
            }
            if (quantity > selected.getProductStock()) {
                showAlert(Alert.AlertType.WARNING, "Insufficient Stock", "Only " + selected.getProductStock() + " available.");
                return;
            }
            orders.addOrder(new orderItem(selected, quantity));
            lstOrderList.setItems(FXCollections.observableArrayList(orders.getOrders()));
            btnConfirm.setDisable(orders.getOrders().isEmpty());
            txtQuantity.clear();
            refresh();

        } catch (NumberFormatException e){
            showAlert(Alert.AlertType.ERROR, "Invalid input", "Quantity must be a number.");
        }
    }

    @FXML
    public void handleDelete(){
        orderItem selected = lstOrderList.getSelectionModel().getSelectedItem();
        orders.removeOrder(selected);
        txtQuantity.clear();
        refresh();
    }

    @FXML
    public void handleConfirm(){
        if (orders.getOrders().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Empty Order", "Please add items before confirming.");
            return;
        }
        orders.confirm();
        btnConfirm.setDisable(true);
        refresh();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
