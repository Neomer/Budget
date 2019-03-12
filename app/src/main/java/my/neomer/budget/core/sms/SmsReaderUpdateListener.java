package my.neomer.budget.core.sms;

import java.util.List;

import my.neomer.budget.models.Transaction;

public interface SmsReaderUpdateListener {

    void updateList(List<Transaction> transactionList);

    void requestSmsPermission();

}
