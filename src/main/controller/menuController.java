package controller;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import models.manageInventory;
import models.product;


public class menuController {
    @FXML private TableView<product> tblMenu;
    @FXML private TableColumn<product, String> colName;
    @FXML private TableColumn<product, Double> colPrice;
    @FXML private TableColumn<product, Integer> colStock;
    @FXML private TextField txtName, txtPrice, txtStock;

    manageInventory inventory = new manageInventory();

    @FXML
    public void initialize(){
        colName.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getProductName()));
        colPrice.setCellValueFactory(c->new SimpleDoubleProperty(c.getValue().getProductPrice()).asObject());
        colStock.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getProductStock()).asObject());

        inventory.addProduct(new product("Sizzling Bangus", 280, 23));
        inventory.addProduct(new product("Calamaris", 389, 14));
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

        refresh();
    }
}
