package models;

public class product {
    private int productStock;
    private String productName;
    private double productPrice;


    public product(String productName, double productPrice, int productStock){
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }
    public int getProductStock() {
        return productStock;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double Price) {
        this.productPrice = Price;
    }

    void setStock(int Stock){
        this.productStock = Stock;
    }

    void setProductName(String name){
        this.productName = name;
    }

    @Override
    public String toString() {
        return String.format("%s PHP %.2f  (%d pcs)", productName, productPrice, productStock);
    }
}
