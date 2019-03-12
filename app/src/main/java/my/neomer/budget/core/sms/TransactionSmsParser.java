package my.neomer.budget.core.sms;

import my.neomer.budget.core.sms.banks.exceptions.SmsFormatException;
import my.neomer.budget.models.Transaction;

/**
 * Парсеры для СМС от банков с транзакциями
 */
public interface TransactionSmsParser {

    boolean isValid(Sms sms);

    Transaction createTransaction(Sms sms) throws SmsFormatException;

}
