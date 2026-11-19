package controller;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.manageInventory;
import models.product;
import models.Orders;

public class newOrderController {
    @FXML private TableView<product> tblOrder;
    @FXML private TableColumn<product, String> colName;
    @FXML private TableColumn<product, Double> colPrice;
    @FXML private TableColumn<product, Integer> colStock;
    @FXML private TextField txtQuantity;

    manageInventory inventory = new manageInventory();
    @FXML
    private void initialize(){
        colName.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getProductName()));
        colPrice.setCellValueFactory(c->new SimpleDoubleProperty(c.getValue().getProductPrice()).asObject());
        colStock.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getProductStock()).asObject());

        inventory.addProduct(new product("Sizzling Bangus", 280, 23));
        inventory.addProduct(new product("Calamaris", 389, 14));
        inventory.sortProduct();
        refresh();

    }
    private void refresh(){
        tblOrder.getItems().setAll(inventory.getInventory());
        tblOrder.refresh();
    }

    @FXML
    private void handleAdd(){
        try {
            int quantity = Integer.parseInt(txtQuantity.getText().trim());

            if (quantity <= 0){
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

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
