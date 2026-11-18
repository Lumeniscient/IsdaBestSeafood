package controller;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import models.manageInventory;
import models.product;


public class menuController {
    @FXML private TableView<product> tblMenu;
    @FXML private TableColumn<product, String> colName;
    @FXML private TableColumn<product, Double> colPrice;
    @FXML private TableColumn<product, Integer> colStock;
    @FXML private TextField txtName, txtPrice, txtStock, txtSearchName;
    @FXML private Button btnExitSearch;

    manageInventory inventory = new manageInventory();

    @FXML
    public void initialize(){
        colName.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getProductName()));
        colPrice.setCellValueFactory(c->new SimpleDoubleProperty(c.getValue().getProductPrice()).asObject());
        colStock.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getProductStock()).asObject());

        inventory.addProduct(new product("Sizzling Bangus", 280, 23));
        inventory.addProduct(new product("Calamaris", 389, 14));
        inventory.sortProduct();
        refresh();

    }
    private void refresh(){ // this is necessary to load the table per transaction. This is also necessary to initially load the table with content
        tblMenu.getItems().setAll(inventory.getInventory()); // getting all fruits from the inventory
        tblMenu.refresh();
    }

    @FXML
    private void handleAdd(){
        String name = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText().trim());
        int stock = Integer.parseInt(txtStock.getText().trim());
        inventory.addProduct(new product(name, price, stock));
        txtName.clear();
        txtStock.clear();
        txtPrice.clear();
        inventory.sortProduct();
        refresh();
    }

    @FXML
    private void handleDelete(){
        String selected = tblMenu.getSelectionModel().getSelectedItem().getProductName();
        inventory.removeProduct(selected);
        txtName.clear();
        refresh();
    }

    @FXML
    private void handleEditItem(){
        product selected = tblMenu.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handleSaveEdit(){
        product selected = tblMenu.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handleSearch(){
        String name = txtSearchName.getText();
        int index = inventory.searchProduct(name);

        if (index != -1 || name == null){
            product found = inventory.getInventory().get(index);
            tblMenu.setItems(FXCollections.observableArrayList(found));
        }
        else {
            // Make alert here
            refresh();
        }

        btnExitSearch.setOnMouseReleased(e -> refresh());
        btnExitSearch.setOnMouseClicked(e -> txtName.clear());

    }

}
