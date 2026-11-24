package models;
import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;
import models.orderItem;

public class orders extends transactions{
    private int totalOrders = 0;
    public ArrayList<orderItem> orderList = new ArrayList<>();


    @Override
    public double computeTotal() {
        double total = 0;
        for (orderItem item : orderList) total += item.getSubtotal();
        return total;
    }

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

    public void confirm() {
        totalOrders++;
        for (orderItem item : orderList) {
            int newStock = item.getOrderProduct().getProductStock() - item.getQuantity();
            item.getOrderProduct().setStock(newStock);
        }
        transactions.addTransaction(new ArrayList<>(orderList));

        String receipt = orderList.stream().map(Object::toString).collect(Collectors.joining("\n"));
        String message = receipt + "\n--------------------\nTotal: ₱" + String.format("%.2f", computeTotal());
        JOptionPane.showMessageDialog(null, message, "Receipt", JOptionPane.INFORMATION_MESSAGE);
        orderList.clear();
    }
    public void clear(){
        orderList.clear();
    }


}
