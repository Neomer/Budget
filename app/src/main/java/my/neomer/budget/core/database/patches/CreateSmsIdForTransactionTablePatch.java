package my.neomer.budget.core.database.patches;

import android.database.sqlite.SQLiteDatabase;

import my.neomer.budget.core.database.DatabasePatch;

/**
 * Создает поле sms_id для таблицы tr
 */
public class CreateSmsIdForTransactionTablePatch implements DatabasePatch {

    @Override
    public void execute(SQLiteDatabase db) {
        db.execSQL("alter table tr add column sms_id integer;");
    }
}
