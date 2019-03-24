package my.neomer.budget.core.types;

public final class Money {

    private double amount;
    private Currency currency;

    public Money() {
        this.amount = 0;
        this.currency = null;
    }

    public Money(double amount) {
        this.amount = amount;
        this.currency = null;
    }

    public Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
