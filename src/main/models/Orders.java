package models;
import models.orderItem;
import java.util.ArrayList;

public class Orders {
    public ArrayList<orderItem> getItems() {
        return items;
    }

    private ArrayList<orderItem> items = new ArrayList<>();

    public ArrayList<orderItem> getOrders(){
        return items;
    }


}
