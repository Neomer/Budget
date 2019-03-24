package my.neomer.budget.core.database.patches;

import android.database.sqlite.SQLiteDatabase;

import my.neomer.budget.core.database.DatabasePatch;

/**
 * Меняет тип поля transactionType в таблице tr на String
 */
public class TransactionTypeStringValuesPatch implements DatabasePatch {
    @Override
    public void execute(SQLiteDatabase db) {
        db.beginTransaction();
        db.execSQL("alter table tr rename to tr_old;");
        db.execSQL("create table tr ("
                + "id integer primary key autoincrement,"
                + "sms_id integer,"
                + "title text,"
                + "bill integer,"
                + "amount double,"
                + "transactionType varchar(20),"
                + "category integer" + ");");

        db.execSQL("INSERT INTO tr SELECT * from tr_old;");
        db.execSQL("drop table tr_old;");
        db.setTransactionSuccessful();
        db.endTransaction();

    }
}
