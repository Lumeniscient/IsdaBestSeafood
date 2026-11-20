package models;

import java.util.Comparator;

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


    public String toString(){
        return String.format("%s ×%d = ₱%.2f", orderProduct.getProductName(), quantity, getSubtotal());
    }

}
