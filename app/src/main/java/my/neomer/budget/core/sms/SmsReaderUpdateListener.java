package my.neomer.budget.core.sms;

import java.util.List;

import my.neomer.budget.models.Transaction;

/**
 * Листенер, ожидающий обновление списка транзакций, полученных из Смс. Должен владеть контекстом,
 * на случай необходимости запроса прав на чтений Смс.
 */
public interface SmsReaderUpdateListener {

    /**
     * Обновить список транзакций, прочитанными из Смс сообщений
     * @param transactionList список транзакций из Смс
     */
    void updateList(List<Transaction> transactionList);

    /**
     * Необходимо запросить права доступа на чтение Смс сообщений
     */
    void requestSmsPermission();

}
