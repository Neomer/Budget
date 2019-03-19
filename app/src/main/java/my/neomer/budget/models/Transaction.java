package my.neomer.budget.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.joda.time.DateTime;

import java.io.Serializable;

import my.neomer.budget.core.types.Money;
import my.neomer.budget.core.types.TransactionCategory;

@Entity(active = true, nameInDb = "Transactions")
public class Transaction {

    private String title;
    private String detailed;
    private Money amount;
    private Money balance;
    private DateTime date;
    private String bill;
    private TransactionCategory category;
    @Id
    private long id;

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

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balans) {
        this.balance = balans;
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

    public TransactionCategory getCategory() {
        return category;
    }

    public void setCategory(TransactionCategory category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
