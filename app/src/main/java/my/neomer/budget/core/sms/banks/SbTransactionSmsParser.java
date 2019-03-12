package my.neomer.budget.core.sms.banks;

import android.nfc.FormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.neomer.budget.core.sms.Sms;
import my.neomer.budget.core.sms.TransactionSmsParser;
import my.neomer.budget.core.sms.banks.exceptions.SmsFormatException;
import my.neomer.budget.models.Transaction;

/**
 * Парсер для СМС от Сбербанка
 */
public class SbTransactionSmsParser implements TransactionSmsParser {

    //ECMC6994 14:08 покупка 84р MAGNIT Баланс:1561.51р

    @Override
    public boolean isValid(Sms sms) {
        return sms.getAddress()!= null && sms.getAddress().equals("900") &&
                sms.getBody() != null && sms.getBody().matches("^ECMC\\d{4}\\s+(\\d{2}:\\d{2})\\s+(покупка|зачисление)\\s+(\\d+(\\.\\d{1,2})?\\s*[а-яА-Я\\w]+)(\\s+[а-яА-Я\\d\\w\\*\\-\\_\\.]+)*\\s+(Баланс:(\\d+(\\.\\d{1,2})?\\s*[а-яА-Я\\w]+))$");
    }

    @Override
    public Transaction createTransaction(Sms sms) throws SmsFormatException {
        String[] parts = sms.getBody().split(" ");

        Transaction res = new Transaction();

        String body = sms.getBody();
        int sp = body.indexOf(' '), sp2 = 0;
        String part = body.substring(0, sp);
        if (!Pattern.compile("ECMC\\d{4}").matcher(part).matches()) {
            throw new SmsFormatException();
        }
        res.setBill(part);
        sp2 = sp;

        sp = body.indexOf(' ', sp2);
        body.substring(0, sp - sp2);
        sp2 = sp;

        sp = body.indexOf(' ', sp2);
        String typeString = body.substring(0, sp - sp2).toLowerCase();
        if (typeString == "покупка") {
            res.setType(Transaction.TransactionType.Spend);
        } else if (typeString == "зачисление") {
            res.setType(Transaction.TransactionType.Income);
        } else {
            throw new SmsFormatException();
        }
        sp2 = sp;

        sp = body.indexOf(' ', sp2);
        part = body.substring(0, sp - sp2);
        if (!Pattern.compile("(\\d+(\\.\\d{1,2})?\\s*[а-яА-Я\\w]+)").matcher(part).matches()) {
            throw new SmsFormatException();
        }
        Matcher m = Pattern.compile("\\d+(\\.\\d{1,2})").matcher(part);
        if (!m.find()) {
            throw new SmsFormatException();
        }
        res.setAmount(Double.valueOf(m.group()));
        sp2 = sp;

        return res;
    }
}
