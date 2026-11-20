package controller;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.manageInventory;
import models.product;
import models.userManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class menuController extends userManager {
    @FXML private TableView<product> tblMenu;
    @FXML private TableColumn<product, String> colName;
    @FXML private TableColumn<product, Double> colPrice;
    @FXML private TableColumn<product, Integer> colStock;
    @FXML private TextField txtName, txtPrice, txtStock, txtSearchName, txtEditName, txtEditPrice, txtEditStock;
    @FXML private Button btnExitSearch, btnSaveItem, btnCancelEdit, btnDashboard, btnMenu, btnSales, btnNewOrder;
    @FXML private Label lblDate;

    manageInventory inventory = manageInventory.getInstance();

    @FXML
    private void initialize(){
        colName.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getProductName()));
        colPrice.setCellValueFactory(c->new SimpleDoubleProperty(c.getValue().getProductPrice()).asObject());
        colStock.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getProductStock()).asObject());

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        lblDate.setText(today.format(formatter));
        btnExitSearch.setVisible(false);
        btnCancelEdit.setDisable(true);
        btnSaveItem.setDisable(true);
        txtSearchName.setFocusTraversable(false);


        if (getRole().equals("Staff")) {
            btnMenu.setVisible(false);
            btnSales.setVisible(false);
        }
        btnDashboard.setDisable(false);
        btnMenu.setDisable(true);
        btnNewOrder.setDisable(false);
        btnSales.setDisable(false);

    refresh();
    }

    @FXML
    private void handleDashboard() throws IOException {
        if (Objects.equals(getRole(), "Admin")){
            Stage stage = (Stage) txtSearchName.getScene().getWindow();
            switchScene(stage, "/app/dashboard.fxml");
        }else{
            showAlert(Alert.AlertType.INFORMATION, "Access Blocked", "Permission Denied");
        }

    }

    @FXML
    private void handleMenu() {}

    @FXML
    private void handleNewOrder() throws IOException {
        if (Objects.equals(getRole(), "Admin")){
            Stage stage = (Stage) txtSearchName.getScene().getWindow();
            switchScene(stage, "/app/newOrder.fxml");
        }else if (Objects.equals(getRole(), "Staff")){
            Stage stage = (Stage) txtSearchName.getScene().getWindow();
            switchScene(stage, "/app/newOrder.fxml");
        }
    }

    @FXML
    private void handleSales() throws IOException {
        if (Objects.equals(getRole(), "Admin")){
            Stage stage = (Stage) txtSearchName.getScene().getWindow();
            switchScene(stage, "/app/sales.fxml");
        }else if (Objects.equals(getRole(), "Staff")){
            Stage stage = (Stage) txtSearchName.getScene().getWindow();
            switchScene(stage, "/app/sales.fxml");
        }
    }

    @FXML
    private void handleLogout() throws IOException {
        Stage stage = (Stage) btnDashboard.getScene().getWindow();
        switchScene(stage, "/app/login.fxml");

    }

    private void refresh(){
        tblMenu.getItems().setAll(inventory.getInventory());
        tblMenu.refresh();
    }

    @FXML
    private void handleAdd(){
        String name = txtName.getText();
        if (name.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing name", "Please enter a product name.");
            return;
        }

        try {
            double price = Double.parseDouble(txtPrice.getText().trim());
            int stock = Integer.parseInt(txtStock.getText().trim());

            if (stock <= 0){
                showAlert(Alert.AlertType.WARNING, "Invalid Stock", "Stock must be greater than 0.");
                return;
            }else {
                inventory.addProduct(new product(name, price, stock));
            }
            txtName.clear();
            txtStock.clear();
            txtPrice.clear();
            inventory.sortProduct();
            refresh();

        } catch (NumberFormatException e){
            showAlert(Alert.AlertType.ERROR, "Invalid input",
                    "Product price and product Stock must be a number");
        }

    }

    @FXML
    private void handleDelete(){

        try {
            String selected = tblMenu.getSelectionModel().getSelectedItem().getProductName();
            inventory.removeProduct(selected);
            txtName.clear();
            refresh();
        }catch (NullPointerException e){
            showAlert(Alert.AlertType.WARNING, "No item selected", "Click an item in the table first.");
        }
    }

    @FXML
    private void handleEditItem(){
        product selected = tblMenu.getSelectionModel().getSelectedItem();

        try {
            txtEditName.setText(selected.getProductName());
            txtEditPrice.setText(String.valueOf(selected.getProductPrice()));
            txtEditStock.setText(String.valueOf(selected.getProductStock()));
            btnCancelEdit.setDisable(false);
            btnSaveItem.setDisable(false);

            btnSaveItem.setOnMouseClicked(e -> {
                btnCancelEdit.setDisable(true);
                btnSaveItem.setDisable(true);
                String name = txtEditName.getText();

                if (name.isEmpty()) {
                    showAlert(Alert.AlertType.WARNING, "Missing name", "Please enter a product name.");
                    return;
                }
                try {
                    double price = Double.parseDouble(txtEditPrice.getText().trim());
                    int stock = Integer.parseInt(txtEditStock.getText().trim());

                    if (stock <= 0){
                        showAlert(Alert.AlertType.WARNING, "Invalid Stock", "Stock must be greater than 0.");;
                        return;
                    }else {
                        inventory.updateProduct(selected, name, price, stock);
                    }
                    txtEditName.clear();
                    txtEditPrice.clear();
                    txtEditStock.clear();
                    inventory.sortProduct();
                    refresh();

                }catch (NumberFormatException n){
                    showAlert(Alert.AlertType.ERROR, "Invalid input",
                            "Product price and product Stock must be a number");
                }
            });
        }catch (NullPointerException e){
            showAlert(Alert.AlertType.WARNING, "No item selected", "Click an item in the table first.");
        }

        btnCancelEdit.setOnMouseClicked(e -> {
            txtEditName.clear();
            txtEditPrice.clear();
            txtEditStock.clear();
            btnCancelEdit.setDisable(true);
            btnSaveItem.setDisable(true);
            tblMenu.getSelectionModel().clearSelection();
            txtSearchName.deselect();
            refresh();
        });
    }

    @FXML
    private void handleSearch(){
        String name = txtSearchName.getText();
        int index = inventory.searchProduct(name);

        try {
            product found = inventory.getInventory().get(index);
            tblMenu.setItems(FXCollections.observableArrayList(found));
            btnExitSearch.setVisible(true);

            btnExitSearch.setOnMouseClicked(e -> {
                btnExitSearch.setVisible(false);
                txtSearchName.clear();
                refresh();
            });
        } catch (Exception e){
            showAlert(Alert.AlertType.WARNING, "No item found", name + " is not found.");
            refresh();
        }


    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
