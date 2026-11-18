package models;
import java.util.ArrayList;
import java.util.Comparator;

import models.product;

public class manageInventory {

    // our inventory that contains our products
    private ArrayList<product> inventory = new ArrayList<>();
    private double earnings;

    // adds a product in the inventory
    public void addProduct(product f){
        inventory.add(f);
    }

    // sorts the inventory by name
    public void sortProduct(){
        inventory.sort(new Comparator<product>() {
            @Override
            public int compare(product a, product b) {
                return a.getProductName().compareToIgnoreCase(b.getProductName());
            }
        });
    }
    // removes/deletes a product in the inventory
    public void removeProduct(String prodName){
        int prodLoc = searchProduct(prodName);
            if (prodLoc >= 0){
                inventory.remove(prodLoc);
            }

    }

    // Displays all the product in inventory
    public ArrayList<product> getInventory(){
        return inventory;
    }

    // adding updateProduct() soon....
    public void updateProduct(String prodName, double prodPrice, int prodStock){
        ArrayList<product> a = inventory;

        int nameIndex = inventory.indexOf(prodName);
        int priceIndex = inventory.indexOf(prodPrice);
        int stockIndex = inventory.indexOf(prodStock);

    }

    // search product using binary search (halin sa DataStruc BST)
    public int searchProduct(String targetProduct){
        ArrayList<product> prodList = inventory;
        int leftProd = 0;
        int rightProd = prodList.size() - 1;

        while (leftProd <= rightProd){
            int midProd = leftProd + (rightProd - leftProd) / 2;
            int compareProd = prodList.get(midProd).getProductName().compareToIgnoreCase(targetProduct);

            if (compareProd == 0){
                System.out.println("Found " + targetProduct);
                return midProd;
            }

            else if (compareProd < 0){
                leftProd = midProd + 1;
            }
            else{
                rightProd = midProd - 1;
            }
        }

        System.out.println("Could not find " + targetProduct);
        return -1;
    }
}
