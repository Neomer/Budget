package my.neomer.budget.core.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import my.neomer.budget.core.AppContext;
import my.neomer.budget.models.Transaction;

public class TransactionManager implements EntityManager<Integer, Transaction> {
    @Override
    public String getTableName() {
        return "tr";
    }

    @Override
    @Nullable
    public Transaction getById(Integer id) {
        SQLiteDatabase db = AppContext.getInstance().getDatabaseHelper().getReadableDatabase();
        Cursor c = db.query(getTableName(), null, "id=?", new String[] { id.toString() }, null, null, null);
        if (!c.moveToFirst()) {
            return null;
        }
        Transaction t = new Transaction();
        t.map(c);
        return t;
    }

    @Override
    public List<Transaction> findAll() {
        List<Transaction> result = new ArrayList<>();
        SQLiteDatabase db = AppContext.getInstance().getDatabaseHelper().getReadableDatabase();
        Cursor c = db.query(getTableName(), null, null,null, null, null, null);
        if (c.moveToFirst()) {
            do {
                Transaction t = new Transaction();
                t.map(c);
                result.add(t);
            } while (c.moveToNext());
        }
        return result;
    }
}
