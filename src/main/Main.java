
import models.manageInventory;
import models.product;
import models.Orders;

public class Main {

    public static void main(String[] args){
      manageInventory inventory = new manageInventory();

      inventory.addProduct(new product("Bangrus", 55.2, 32));
      inventory.addProduct(new product("Tilapia", 39.49, 21));
      inventory.addProduct(new product("Sushi", 25, 322));

      System.out.println(inventory.getInventory());
      inventory.removeProduct("Tilapia");
      System.out.println(inventory.getInventory());
      inventory.searchProduct("Sushi");

    }

}
