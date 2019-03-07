package my.neomer.budget.core;

import java.util.ArrayList;
import java.util.List;

import my.neomer.budget.models.Transaction;

public class DatabaseTransactionsLoader implements DataLoader<Transaction> {
    @Override
    public List<Transaction> loadData() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction("transaction1", "detailed", 1200));
        transactionList.add(new Transaction("transaction2", "detailed", 10.23));
        transactionList.add(new Transaction("transaction3", "detailed", -120));
        transactionList.add(new Transaction("transaction4", "detailed", 10.23));
        transactionList.add(new Transaction("transaction5", "detailed", -130.546));
        transactionList.add(new Transaction("transaction6", "detailed", -1000));
        return  transactionList;
    }
}
