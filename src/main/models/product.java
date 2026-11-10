package models;

public class product {
    private int productId = 1, productStock;
    private String productName;
    private double productPrice;
    public int getProductId() {
        return productId;
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

    public product(String productName, double productPrice, int productStock){
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    void setStock(int productStock){
        if (productStock < 0){
            this.productStock = 0;
        }
        else {
            this.productStock = productStock;
        }

    }

    @Override
    public String toString() {
        return String.format("%s PHP %.2f  (%d pcs)", productName, productPrice, productStock);
    }
}
