package my.neomer.budget.core.sms.banks;

import my.neomer.budget.core.sms.Sms;
import my.neomer.budget.core.sms.TransactionSmsParser;
import my.neomer.budget.models.Transaction;

/**
 * Парсер для СМС от Быстробанка
 */
public class BystrobankTransactionSmsParser implements TransactionSmsParser {

    //08.03.19 13:31 Pokupka 270.00 RUB Schet 2726149 Karta **6654 BUKET Balans 11973.45 RUB
    //08.03.19 12:14 Pokupka 1600.00 RUB Schet 2726149 Karta **6654 IP STRIZHOV S.A, Balans 12243.45 RUB
    //01.03.19 06:40 Komissiya - gashenie Rashod 59.00 RUB Schet 2726149 Balans 23091.72 RUB
    //28.02.19 04:21 Kapitalizaciya Prihod 0.74 RUB Schet 2726149 Balans 23150.72 RUB
    //27.02.19 15:21 Platezhnoe poruchenie - Z.P. Prihod 10000.00 RUB Schet 2726149 Balans 23492.98 RUB

    @Override
    public boolean isValid(Sms sms) {
        return sms.getAddress()!= null && sms.getAddress().equals("BystroBank") &&
                sms.getBody() != null && sms.getBody().matches("^\\d{2}\\.\\d{2}\\.\\d{2}\\s\\d{2}\\:\\d{2}(\\s[\\w\\s\\-\\.]*)*(Rashod|Pokupka|Prihod)\\s(\\d*(\\.\\d{1,2})?)(\\s[\\w]+)\\s(Schet\\s\\d*\\s)(Karta\\s\\*\\*\\d*\\s[\\w\\s\\.\\,]*)?Balans[\\s\\:]\\d*(\\.\\d{1,2})?\\s[\\w]+$");
    }

    @Override
    public Transaction createTransaction(Sms sms) {
        return null;
    }
}
