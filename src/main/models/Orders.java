package models;
import models.orderItem;
import java.util.ArrayList;

public class Orders {
    public ArrayList<orderItem> getOrders() {
        return orders;
    }

    private ArrayList<orderItem> orders = new ArrayList<>();

    public void addOrder(orderItem order){
        orders.add(order);
    }

    public void removeOrder(orderItem order){
        orders.remove(order);
    }

    public double getTotal(){
        double total = 0;
        for (orderItem item: orders){
            total += item.getSubtotal();
        }

        return total;
    }

    public void clear(){
        orders.clear();
    }
}
