package my.neomer.budget.core.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import my.neomer.budget.core.DataLoader;
import my.neomer.budget.core.types.Money;
import my.neomer.budget.models.Transaction;

public class TransactionsDatabaseHelper extends SQLiteOpenHelper implements DataLoader<Transaction> {

    private static final String TABLE_TRANSACTIONS = "Transactions";
    private static final String COLUMN_TRANSACTIONS_ID = "Id";
    private static final String COLUMN_TRANSACTIONS_TITLE = "Title";
    private static final String COLUMN_TRANSACTIONS_CREATED = "Created";
    private static final String COLUMN_TRANSACTIONS_DETAILED = "Detailed";
    private static final String COLUMN_TRANSACTIONS_BALANCE = "Balance";
    private static final String COLUMN_TRANSACTIONS_BILL = "Bill";
    private static final String COLUMN_TRANSACTIONS_CATEGORY = "Category";

    public TransactionsDatabaseHelper(@Nullable @android.support.annotation.Nullable Context context, @Nullable @android.support.annotation.Nullable String name, @Nullable @android.support.annotation.Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public TransactionsDatabaseHelper(@Nullable @android.support.annotation.Nullable Context context, @Nullable @android.support.annotation.Nullable String name, @Nullable @android.support.annotation.Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable @android.support.annotation.Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE " + TABLE_TRANSACTIONS + "("
                + COLUMN_TRANSACTIONS_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_TRANSACTIONS_TITLE + " TEXT,"
                + COLUMN_TRANSACTIONS_DETAILED + " TEXT,"
                + COLUMN_TRANSACTIONS_BALANCE + " DOUBLE,"
                + COLUMN_TRANSACTIONS_BILL + " INTEGER,"
                + COLUMN_TRANSACTIONS_CATEGORY + " INTEGER,"
                + COLUMN_TRANSACTIONS_CREATED + " DATETIME" + ")";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
        onCreate(db);
    }

    @Override
    public List<Transaction> LoadAll() {
        List<Transaction> result = new ArrayList<>();

        String countQuery =
                        "SELECT "
                        + COLUMN_TRANSACTIONS_ID + ", "
                        + COLUMN_TRANSACTIONS_TITLE + ", "
                        + COLUMN_TRANSACTIONS_DETAILED + ", "
                        + COLUMN_TRANSACTIONS_BALANCE + ", "
                        + COLUMN_TRANSACTIONS_BILL + ", "
                        + COLUMN_TRANSACTIONS_CATEGORY + ", "
                        + COLUMN_TRANSACTIONS_CREATED
                        + " FROM " + TABLE_TRANSACTIONS
                        + " ORDER BY " + COLUMN_TRANSACTIONS_CREATED + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        if (cursor.moveToFirst()) {
            do
            {
                Transaction item = new Transaction();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setTitle(cursor.getString(1));
                item.setDetailed(cursor.getString(2));
                item.setBalance(new Money(Double.parseDouble(cursor.getString(3)), null));
                item.setBill(cursor.getString(4));
                result.add(item);
            }
            while (cursor.moveToNext());
        }

        cursor.close();

        return result;
    }
}
