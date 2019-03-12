package my.neomer.budget.core.sms.banks;

import android.support.annotation.NonNull;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.neomer.budget.core.sms.BaseTransactionSmsParser;
import my.neomer.budget.core.sms.Sms;
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
    protected void fillTransaction(@NonNull Matcher matcher, @NonNull Transaction transaction, @NonNull Sms sms) throws SmsFormatException {
        String cardString = null;
        String dateTimeString = null;
        String actionString = null;
        String sumString = null;
        String currencyString = null;
        String shopString = null;
        String balanceSumString = null;
        String balanceCurrencyString = null;

        try {
            cardString = matcher.group(1);
            dateTimeString = matcher.group(2);
            actionString = matcher.group(3);
            sumString = matcher.group(4);
            currencyString = matcher.group(6);
            shopString = matcher.group(7);
            balanceSumString = matcher.group(9);
            balanceCurrencyString = matcher.group(11);
        } catch (NullPointerException e) {
            throw new SmsFormatException(e.getMessage());
        }

        switch (actionString.trim().toLowerCase()) {
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

        transaction.setDetailed(shopString != null ? shopString.trim() : "");
        transaction.setBill(cardString != null ? cardString.trim() : "");

        if (dateTimeString != null) {
            try {
                transaction.setDate(sms.getDateSent());
            } catch (IllegalArgumentException e) {
                throw new SmsFormatException("DateTime parsing error '" + dateTimeString + "'");
            }
        }

        try {
            if (sumString != null) {
                transaction.setAmount(
                        new Money(
                                transaction.getType() == Transaction.TransactionType.Income ? Double.valueOf(sumString.trim()) : -Double.valueOf(sumString.trim()),
                                currencyString != null ? CurrencyFactory.getCurrencyByShortName(currencyString) : null));
            }
            if (balanceSumString != null) {
                transaction.setBalance(
                        new Money(
                                Double.valueOf(balanceSumString.trim()),
                                balanceCurrencyString != null ? CurrencyFactory.getCurrencyByShortName(balanceCurrencyString) : null));
            }
        } catch (NumberFormatException e) {
            throw new SmsFormatException("Transaction sum wrong format '" + sumString + "'");
        }

    }
}
