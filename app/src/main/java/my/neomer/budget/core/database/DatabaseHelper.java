package my.neomer.budget.core.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import my.neomer.budget.R;
import my.neomer.budget.core.database.patches.CreateSmsIdForTransactionTablePatch;
import my.neomer.budget.core.database.patches.InitialDatabasePatch;
import my.neomer.budget.core.database.patches.TransactionTypeStringValuesPatch;

public final class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 3;
    private List<DatabasePatch> patchList;

    public DatabaseHelper(@NonNull Context context) {
        super(context, "Transactions", null, DB_VERSION);
        patchList = new ArrayList<>();

        patchList.add(new InitialDatabasePatch());
        patchList.add(new CreateSmsIdForTransactionTablePatch());
        patchList.add(new TransactionTypeStringValuesPatch());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(DatabaseHelper.class.getCanonicalName(), "--- onCreate database ---");

        db.execSQL("create table currency ("
                + "id integer primary key autoincrement,"
                + "shortname integer,"
                + "fullname integer,"
                + "symbol varchar(10)" + ");");

        db.execSQL("insert into currency (shortname, fullname, symbol) values "
                + "('" + R.string.rouble_short + "', '" + R.string.rouble_fullname + "', '&#x20bd'),"
                + "('" + R.string.dollar_short + "', '" + R.string.dollar_fullname + "', '&#x0024'),"
                + "('" + R.string.euro_short + "', '" + R.string.euro_fullname + "', '&#x20ac')"
        );

        db.execSQL("create table bill ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "number text" + ");");

        db.execSQL("create table tr ("
                + "id integer primary key autoincrement,"
                + "sms_id integer,"
                + "title text,"
                + "bill integer,"
                + "amount double,"
                + "transactionType integer,"
                + "category integer" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(DatabaseHelper.class.getCanonicalName(), "--- Update database version from " + oldVersion + " to " + newVersion + " ---");
        for (int version = oldVersion; version < newVersion; ++version) {
            DatabasePatch patch = patchList.get(version);
            Log.d(DatabaseHelper.class.getCanonicalName(), "Patch: " + patch.getClass().getCanonicalName());
            patch.execute(db);
        }
    }
}
