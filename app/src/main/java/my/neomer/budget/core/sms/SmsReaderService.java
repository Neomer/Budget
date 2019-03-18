package my.neomer.budget.core.sms;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import my.neomer.budget.core.sms.banks.BystrobankTransactionSmsParser;
import my.neomer.budget.core.sms.banks.SbTransactionSmsParser;
import my.neomer.budget.core.sms.banks.TinkoffTransactionSmsParser;
import my.neomer.budget.core.sms.banks.exceptions.SmsFormatException;
import my.neomer.budget.models.Transaction;

public class SmsReaderService {

    //region sigleton
    private static final SmsReaderService ourInstance = new SmsReaderService();

    public static SmsReaderService getInstance() {
        return ourInstance;
    }
    //endregion

    private SmsReaderUpdateListener updateListener;
    private Context context;
    private SmsParser smsParser;
    private List<TransactionSmsParser> transactionSmsParserList;

    //region ctor
    private SmsReaderService() {
        smsParser = new SmsParserImpl();

        transactionSmsParserList = new ArrayList<>();
        transactionSmsParserList.add(new SbTransactionSmsParser());
        transactionSmsParserList.add(new BystrobankTransactionSmsParser());
        transactionSmsParserList.add(new TinkoffTransactionSmsParser());
    }
    //endregion

    public void registerUpdateListener(SmsReaderUpdateListener listener) {
        updateListener = listener;
    }

    public void clearUpdateListener() {
        updateListener = null;
    }

    @Nullable
    private Transaction createTransaction(Sms sms) {
        Log.d("my.neomer.budget", "Parsing SMS '" + sms.getBody() + "'");
        for (TransactionSmsParser parser : transactionSmsParserList) {
            if (parser.isValid(sms)) {
                Log.d("my.neomer.budget", "Found correct parser: " + parser.getClass().getCanonicalName());
                try {
                    return parser.createTransaction(sms);
                } catch (SmsFormatException e) {
                    return null;
                }
            }
        }
        return null;
    }

    public void update() {
        if (updateListener == null || context == null) {
            return;
        }
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            updateListener.requestSmsPermission();
            return;
        }

        List<Transaction> transactions = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, "date_sent desc");

        if (cursor.moveToFirst()) {
            do {
                Sms sms = smsParser.parse(cursor);
                Transaction tr = createTransaction(sms);
                if (tr != null) {
                    transactions.add(tr);
                }

            } while (cursor.moveToNext());
        } else {
            return;
        }
        updateListener.updateList(transactions);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
