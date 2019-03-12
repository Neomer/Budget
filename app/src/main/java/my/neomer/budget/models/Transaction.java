package my.neomer.budget.models;

import org.joda.time.DateTime;

public class Transaction {

    private String title;
    private String detailed;
    private double amount;
    private double balans;
    private DateTime date;
    private String bill;

    public enum TransactionType {
        Spend,
        Income
    }

    private TransactionType type;

    public Transaction() {

    }

    public Transaction(String title, String detailed, double amount) {
        this.title = title;
        this.detailed = detailed;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getBalans() {
        return balans;
    }

    public void setBalans(double balans) {
        this.balans = balans;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

}
