package my.neomer.budget.core.sms.banks;

import android.nfc.FormatException;
import android.support.annotation.NonNull;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.neomer.budget.core.sms.BaseTransactionSmsParser;
import my.neomer.budget.core.sms.Sms;
import my.neomer.budget.core.sms.TransactionSmsParser;
import my.neomer.budget.core.sms.banks.exceptions.SmsFormatException;
import my.neomer.budget.core.types.CurrencyFactory;
import my.neomer.budget.core.types.Money;
import my.neomer.budget.models.Transaction;

    /*
ECMC6994 14:08 покупка 84р MAGNIT Баланс:1561.51р
ECMC6994 12:42 покупка 447р FIXPRICE 914 Баланс: 3064.06р
ECMC6994 12:27 зачисление 13100р Баланс: 14746.41р
ECMC6994 13:20 Покупка 13100р NAIDY Баланс: 1646.41р
ECMC6994 14:08 Покупка 84.90р MAGNIT Баланс: 1561.51р
ECMC6994 19:15 Покупка 381.80р MAGNIT Баланс: 1179.71р
ECMC6994 19:15 Покупка 381.80р MAGNIT Баланс: 1179.71р
ECMC6994 08:17 списание 100р Баланс: 1079.71р
ECMC6994 16:23 Покупка 139.90р MAGNIT Баланс: 939.81р
ECMC6994 08:17 списание 100р Баланс: 1079.71р
     */

/**
 * Парсер для СМС от Сбербанка
 */
public class SbTransactionSmsParser extends BaseTransactionSmsParser {

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");

    @Override
    protected Pattern getBodyValidator() {
        return Pattern.compile("^ECMC(\\d+)\\s+(\\d{2}:\\d{2})\\s+([пП]окупка|[зЗ]ачисление|[сС]писание)\\s+(\\d+(\\.\\d+)?)(\\s*[а-яА-Я\\w]+)((\\s+[а-яА-Я\\w\\-\\_\\.\\,\\*]+)*)\\s[бБ]аланс:\\s*(\\d+(\\.\\d+)?)(\\s*[а-яА-Я\\w]+)$");
    }

    @Override
    protected String getRequiredAddress() {
        return "900";
    }

    @Override
    protected void fillTransaction(@NonNull Matcher matcher, @NonNull Transaction transaction) throws SmsFormatException {
        String cardString = matcher.group(1).trim();
        String dateTimeString = matcher.group(2).trim();
        String actionString = matcher.group(3).trim();
        String sumString = matcher.group(4).trim();
        String currencyString = matcher.group(6).trim();
        String shopString = matcher.group(7).trim();
        String balanceSumString = matcher.group(9).trim();
        String balanceCurrencyString = matcher.group(11).trim();

        switch (actionString.toLowerCase()) {
            case "покупка":
            case "списание":
                transaction.setType(Transaction.TransactionType.Spend);
                break;
            case "зачисление":
                transaction.setType(Transaction.TransactionType.Income);
                break;
            default:
                throw new SmsFormatException("Unknown transaction type '" + actionString + "'");
        }

        transaction.setDetailed(shopString);
        transaction.setBill(cardString);

        try {
            transaction.setDate(formatter.parseDateTime(dateTimeString));
        } catch (IllegalArgumentException e) {
            throw new SmsFormatException("DateTime parsing error '" + dateTimeString + "'");
        }

        try {
            transaction.setAmount(new Money(Double.valueOf(sumString), CurrencyFactory.getCurrencyByShortname(currencyString)));
            transaction.setBalance(new Money(Double.valueOf(balanceSumString), CurrencyFactory.getCurrencyByShortname(balanceCurrencyString)));
        } catch (NumberFormatException e) {
            throw new SmsFormatException("Transaction sum wrong format '" + sumString + "'");
        }
    }
}
