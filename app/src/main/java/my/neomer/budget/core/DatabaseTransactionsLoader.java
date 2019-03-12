package my.neomer.budget.core;

import java.util.ArrayList;
import java.util.List;

import my.neomer.budget.core.types.Currency;
import my.neomer.budget.core.types.Money;
import my.neomer.budget.models.Transaction;

public class DatabaseTransactionsLoader implements DataLoader<Transaction> {
    @Override
    public List<Transaction> loadData() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction("transaction1", "detailed", new Money(1200, new Currency())));
        transactionList.add(new Transaction("transaction2", "detailed", new Money(10.23, new Currency())));
        transactionList.add(new Transaction("transaction3", "detailed", new Money(-120, new Currency())));
        transactionList.add(new Transaction("transaction4", "detailed", new Money(10.23, new Currency())));
        transactionList.add(new Transaction("transaction5", "detailed", new Money(-130.546, new Currency())));
        transactionList.add(new Transaction("transaction6", "detailed", new Money(-1000, new Currency())));
        return  transactionList;
    }
}
