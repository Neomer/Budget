package my.neomer.budget.models;

import android.database.Cursor;

import org.joda.time.DateTime;

import my.neomer.budget.core.database.EntityManager;
import my.neomer.budget.core.database.TransactionManager;
import my.neomer.budget.core.types.Money;
import my.neomer.budget.core.types.TransactionCategory;

public class Transaction extends AbstractEntity<Integer>  {

    @DatabaseField(Name = "sms_id")
    private int smsId;
    private String title;
    private String detailed;
    private Money amount;
    private Money balance;
    private DateTime date;
    private String bill;
    private TransactionCategory category;
    private int id;

    @Override
    public void map(Cursor c) {
        int idColIdx = c.getColumnIndex("id");
        int smsIdColIdx = c.getColumnIndex("sms_id");
        int titleColIdx = c.getColumnIndex("title");
        int billColIdx = c.getColumnIndex("bill");
        int amountColIdx = c.getColumnIndex("amount");
        int transactionTypeColIdx = c.getColumnIndex("transactionType");
        int categoryColIdx = c.getColumnIndex("category");

        setId(c.getInt(idColIdx));
        setSmsId(c.getInt(smsIdColIdx));
        setTitle(c.getString(titleColIdx));
        setBill(String.valueOf(c.getInt(billColIdx)));
        setAmount(new Money(c.getDouble(amountColIdx)));
        setType(TransactionType.valueOf(c.getString(transactionTypeColIdx)));
        category = null;
    }

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

    public void setBalance(Money balance) {
        this.balance = balance;
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

    public int getSmsId() {
        return smsId;
    }

    public void setSmsId(int smsId) {
        this.smsId = smsId;
    }
}
