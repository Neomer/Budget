package my.neomer.budget.core.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import my.neomer.budget.core.AppContext;
import my.neomer.budget.core.types.TransactionCategory;

public class CategoryManager implements EntityManager<Integer, TransactionCategory> {
    @Override
    public String getTableName() {
        return "category";
    }

    @Override
    public TransactionCategory getById(Integer id) {
        return null;
    }

    @Override
    public List<TransactionCategory> findAll() {
        List<TransactionCategory> result = new ArrayList<>();
        SQLiteDatabase db = AppContext.getInstance().getDatabaseHelper().getReadableDatabase();
        Cursor c = db.query(getTableName(), null, null,null, null, null, null);
        if (!c.moveToFirst()) {
            return null;
        }
        do {
            TransactionCategory t = new TransactionCategory();
            t.map(c);
            result.add(t);
        } while (c.moveToNext());
        return result;

    }
}
