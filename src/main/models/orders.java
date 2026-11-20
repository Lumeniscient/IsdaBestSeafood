package models;
import java.time.LocalDate;
import java.util.ArrayList;

public class orders extends transactions{
    private int totalOrders = 0;
    public ArrayList<orderItem> orderList = new ArrayList<>();

    public ArrayList<orderItem> getOrders() {
        return orderList;
    }

    private static orders instance;

    public static orders getInstance() {
        if (instance == null) instance = new orders(LocalDate.now());
        return instance;
    }

    public orders(LocalDate date){
        super(date);
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }


    public void addOrder(orderItem order){
        orderList.add(order);
    }

    public void removeOrder(orderItem order){
        orderList.remove(order);
    }

    @Override
    public double computeTotal() {
        double total = 0;
        for (orderItem item : orderList) total += item.getSubtotal();
        return total;
    }

    public void confirm() {
        totalOrders++;
        for (orderItem item : orderList) {
            int newStock = item.getOrderProduct().getProductStock() - item.getQuantity();
            item.getOrderProduct().setStock(newStock);
        }
        transactions.addTransaction(new ArrayList<>(orderList));
        orderList.clear();
    }
    public void clear(){
        orderList.clear();
    }


}
