package my.neomer.budget.core.sms.banks;

import my.neomer.budget.core.sms.Sms;
import my.neomer.budget.core.sms.TransactionSmsParser;
import my.neomer.budget.models.Transaction;

public class TinkoffTransactionSmsParser implements TransactionSmsParser {
    @Override
    public boolean isValid(Sms sms) {
        return false;
    }

    @Override
    public Transaction createTransaction(Sms sms) {
        return null;
    }
}
