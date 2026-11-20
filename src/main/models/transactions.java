package models;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class transactions {
    private LocalDate today = LocalDate.now();

    transactions(LocalDate today) {
        this.today = today;
    }

    public LocalDate getDate() { return today; }

    private static ArrayList<ArrayList<orderItem>> transactionHistory = new ArrayList<>();
    private static ArrayList<String> transactionTimes = new ArrayList<>();

    public static void addTransaction(ArrayList<orderItem> transaction) {
        transactionHistory.add(transaction);
        transactionTimes.add(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")));
    }

    public static String getTransactionTime(int index) {
        return transactionTimes.get(index);
    }

    public static ArrayList<ArrayList<orderItem>> getTransactionHistory() {
        return transactionHistory;
    }

    public abstract double computeTotal();
}