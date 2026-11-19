package controller;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import models.manageInventory;
import models.product;
import controller.dashboardController;

import java.io.IOException;
import java.util.Objects;

public class menuController {
    @FXML private TableView<product> tblMenu;
    @FXML private TableColumn<product, String> colName;
    @FXML private TableColumn<product, Double> colPrice;
    @FXML private TableColumn<product, Integer> colStock;
    @FXML private TextField txtName, txtPrice, txtStock, txtSearchName, txtEditName, txtEditPrice, txtEditStock, txtUser;
    @FXML private Button btnExitSearch, btnSaveItem, btnCancelEdit, btnDashboard, btnMenu, btnSales, btnNewOrder;

    manageInventory inventory = new manageInventory();
    dashboardController dashboard = new dashboardController();
    loginController user = new loginController();

    @FXML
    public void initialize(){
        colName.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getProductName()));
        colPrice.setCellValueFactory(c->new SimpleDoubleProperty(c.getValue().getProductPrice()).asObject());
        colStock.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getProductStock()).asObject());


        btnExitSearch.setVisible(false);
        btnCancelEdit.setDisable(true);
        btnSaveItem.setDisable(true);
        txtSearchName.setFocusTraversable(false);

        inventory.addProduct(new product("Sizzling Bangus", 280, 23));
        inventory.addProduct(new product("Calamaris", 389, 14));
        inventory.sortProduct();
        refresh();

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
                showAlert(Alert.AlertType.WARNING, "Invalid Stock", "Stock must be a 0 or negative number.");
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
                String name = txtEditName.getText();

                if (name.isEmpty()) {
                    showAlert(Alert.AlertType.WARNING, "Missing name", "Please enter a product name.");
                    return;
                }
                try {
                    double price = Double.parseDouble(txtEditPrice.getText().trim());
                    int stock = Integer.parseInt(txtEditStock.getText().trim());

                    if (stock <= 0){
                        showAlert(Alert.AlertType.WARNING, "Invalid Stock", "Stock must be a 0 or negative number.");
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
