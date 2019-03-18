package my.neomer.budget.core.sms;

import android.support.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.neomer.budget.core.sms.banks.exceptions.SmsFormatException;
import my.neomer.budget.core.types.TransactionCategoryFactory;
import my.neomer.budget.models.Transaction;

public abstract class BaseTransactionSmsParser implements TransactionSmsParser {

    protected abstract Pattern getBodyValidator();

    protected abstract String getRequiredAddress();

    protected abstract void fillTransaction(@NonNull Matcher matcher, @NonNull Transaction transaction, @NonNull Sms sms) throws SmsFormatException;

    @Override
    public boolean isValid(Sms sms) {
        return sms.getAddress()!= null && sms.getAddress().equals(getRequiredAddress()) &&
                sms.getBody() != null && getBodyValidator().matcher(sms.getBody()).matches();
    }

    @Override
    public Transaction createTransaction(Sms sms) throws SmsFormatException {
        Transaction transaction = new Transaction();
        String body = sms.getBody().trim();

        Matcher matcher = getBodyValidator().matcher(body);
        if (matcher.find())
        {
            fillTransaction(matcher, transaction, sms);
        }

        transaction.setId(sms.getId());
        transaction.setCategory(TransactionCategoryFactory.getInstance().findByPattern(transaction.getDetailed()));

        return transaction;
    }
}
