package my.neomer.budget.models;

import org.joda.time.DateTime;

import my.neomer.budget.core.types.Money;

public class Transaction {

    private String title;
    private String detailed;
    private Money amount;
    private Money balans;
    private DateTime date;
    private String bill;

    public enum TransactionType {
        Spend,
        Income
    }

    private TransactionType type;

    public Transaction() {

    }

    public Transaction(String title, String detailed, Money amount) {
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

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Money getBalans() {
        return balans;
    }

    public void setBalance(Money balans) {
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
