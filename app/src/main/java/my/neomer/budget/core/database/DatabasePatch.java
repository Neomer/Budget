package my.neomer.budget.core.database;

import android.database.sqlite.SQLiteDatabase;

public interface DatabasePatch {

    void execute(SQLiteDatabase db);

}
