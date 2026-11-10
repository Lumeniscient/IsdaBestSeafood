package models;
import models.product;

public class orderItem {
    private product orderProduct;
    private int quantity;

    public product getOrderProduct() {
        return orderProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public orderItem(product orderProduct, int quantity){
        this.orderProduct = orderProduct;
        this.quantity = quantity;
    }

    public double getSubtotal(){
        return orderProduct.getProductPrice() * quantity;
    }
}
