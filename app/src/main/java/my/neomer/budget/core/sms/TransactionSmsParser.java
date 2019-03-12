package my.neomer.budget.core.sms;

import my.neomer.budget.core.sms.banks.exceptions.SmsFormatException;
import my.neomer.budget.models.Transaction;

/**
 * Парсеры для СМС от банков с транзакциями. Парсит тело Смс и получает из него корректную транзакцию.
 */
public interface TransactionSmsParser {
    /**
     * Проверяет сможет ли данный парсер корректно распарсить данное сообщение. Проверяет адрес Смс и по регулярке тело сообщения.
     * @param sms Смс сообщение
     * @return True - если парсер сможет обработать данное сообщение
     */
    boolean isValid(Sms sms);

    /**
     * Парсит тело сообщение и возвращает валидную транзакцию
     * @param sms Смс сообщение
     * @return Транзакция из тела сообщения
     * @throws SmsFormatException не удалось распарсить сообщение, вероятно, что не была выполнена проверка методом isValid
     */
    Transaction createTransaction(Sms sms) throws SmsFormatException;

}
