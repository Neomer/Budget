package my.neomer.budget.core.sms;

import org.joda.time.DateTime;

/**
 * Парсер даты / времени для Смс сообщения (не в теле сообщения, а при чтении из папки), используется SmsParser.
 */
public interface SmsDateTimeParser {

    /**
     * Распарсить Дату / Время
     * @param dateString
     * @return
     */
    DateTime parseDate(String dateString);

}
