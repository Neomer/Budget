package my.neomer.budget.models;

public class Transaction {

    private String title;
    private String detailed;
    private double amount;

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
}
