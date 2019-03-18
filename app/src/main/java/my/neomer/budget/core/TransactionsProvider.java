package my.neomer.budget.core;

import java.util.List;
import my.neomer.budget.models.Transaction;

public class TransactionsProvider {

    //region Singleton

    private static final TransactionsProvider ourInstance = new TransactionsProvider();

    public static TransactionsProvider getInstance() {
        return ourInstance;
    }
    //endregion

    private List<Transaction> transactionList;

    private TransactionsProvider() {

    }

    public void init() {

    }

    public synchronized List<Transaction> getTransactions() {
        return transactionList;
    }

    public void appendTransactions(List<Transaction> additionalRegion) {
        for (Transaction t : additionalRegion) {
            boolean found = false;
            for (Transaction ct : this.getTransactions()) {
                if (ct.getId() == t.getId()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                this.getTransactions().add(t);
            }
        }
    }


}
