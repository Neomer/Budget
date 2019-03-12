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
        return  transactionList;
    }
}
