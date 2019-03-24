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

07.03.19 18:04 Pokupka 825.00 RUB Schet 2726149 Karta **6654 Moscow, DELIVERY-CLUB Balans 14562.45 RUB
27.02.19 15:21 Platezhnoe poruchenie - Z.P. Prihod 10000.00 RUB Schet 2726149 Balans 23492.98 RUB
28.02.19 04:21 Kapitalizaciya Prihod 0.74 RUB Schet 2726149 Balans 23150.72 RUB
01.03.19 06:40 Komissiya - gashenie Rashod 59.00 RUB Schet 2726149 Balans 23091.72 RUB
08.03.19 12:14 Pokupka 1600.00 RUB Schet 2726149 Karta **6654 IP STRIZHOV S.A, Balans 12243.45 RUB
08.03.19 13:31 Pokupka 270.00 RUB Schet 2726149 Karta **6654 BUKET Balans 11973.45 RUB

 */

/**
 * Парсер для СМС от Быстробанка
 */
public class BystrobankTransactionSmsParser extends BaseTransactionSmsParser {

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yy HH:mm");

    public BystrobankTransactionSmsParser() {
        super();
    }

    @Override
    protected Pattern getBodyValidator() {
        return Pattern.compile("^(\\d{2}\\.\\d{2}\\.\\d{2}\\s\\d{2}\\:\\d{2})(:\\d{2})?(\\s[\\w\\s\\-\\.]*)*([rR]ashod|[pP]okupka|[pP]rihod)\\s(\\d*(\\.\\d{1,2})?)(\\s[\\w]+)\\s([sS]chet\\s\\d*\\s)(Karta\\s\\*\\*\\d*)?(\\s[\\w\\s\\.\\,\\-\\(\\)\\*]*)?[bB]alans[\\s\\:](\\d*(\\.\\d{1,2})?)\\s*([\\w]+)$");
    }

    @Override
    protected String getRequiredAddress() {
        return "BystroBank";
    }

    @Override
    protected void fillTransaction(@NonNull Matcher matcher, @NonNull Transaction transaction, @NonNull Sms sms) throws SmsFormatException {
        String dateTimeString = null;
        String descriptionString = null;
        String actionString = null;
        String sumString = null;
        String currencyString = null;
        String billString = null;
        String cardString = null;
        String shopString = null;
        String balanceSumString = null;
        String balanceCurrencyString = null;

        try {
            dateTimeString = matcher.group(1);
            descriptionString = matcher.group(3);
            actionString = matcher.group(4);
            sumString = matcher.group(5);
            currencyString = matcher.group(7);
            billString = matcher.group(8);
            cardString = matcher.group(9);
            shopString = matcher.group(10);
            balanceSumString = matcher.group(11);
            balanceCurrencyString = matcher.group(13);
        } catch (NullPointerException e) {
            throw new SmsFormatException(e.getMessage());
        }

        switch (actionString.trim().toLowerCase()) {
            case "rashod":
            case "pokupka":
                transaction.setType(Transaction.TransactionType.Spend);
                break;
            case "prihod":
                transaction.setType(Transaction.TransactionType.Income);
                break;
            default:
                throw new SmsFormatException("Unknown transaction type '" + actionString + "'");
        }

        transaction.setDetailed(descriptionString == null || descriptionString.trim().isEmpty() ? (shopString != null ? shopString.trim()  : "") : descriptionString.trim());
        transaction.setBill(cardString == null || cardString.trim().isEmpty() ? (billString != null ? billString.trim() : "" ) : cardString.trim());

        if (dateTimeString != null) {
            try {
                transaction.setDate(formatter.parseDateTime(dateTimeString.trim()));
            } catch (IllegalArgumentException e) {
                throw new SmsFormatException("DateTime parsing error '" + dateTimeString + "'");
            }
        }

        try {
            if (sumString != null) {
                transaction.setAmount(
                        new Money(
                                transaction.getType() == Transaction.TransactionType.Income ? Double.valueOf(sumString.trim()) : -Double.valueOf(sumString.trim()),
                                currencyString != null ? CurrencyFactory.getInstance().getCurrencyByShortName(currencyString.trim()) : null));
            }
            if (sumString != null) {
                transaction.setBalance(
                        new Money(
                                Double.valueOf(balanceSumString.trim()),
                                balanceCurrencyString != null ? CurrencyFactory.getInstance().getCurrencyByShortName(balanceCurrencyString.trim()) : null));
            }
        } catch (NumberFormatException e) {
            throw new SmsFormatException("Transaction sum wrong format '" + sumString + "'");
        }
    }
}
