package my.neomer.budget.core.types;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;

import java.util.ArrayList;
import java.util.List;

import my.neomer.budget.core.AppContext;
import my.neomer.budget.core.database.DatabaseHelper;

public class CurrencyFactory {

    //region sigleton
    private static final CurrencyFactory ourInstance = new CurrencyFactory();

    public static CurrencyFactory getInstance() {
        return ourInstance;
    }
    //endregion

    private List<Currency> currencyList;

    private CurrencyFactory() {
        currencyList = new ArrayList<>();
    }

    public void loadRegisteredCurrencies(@NonNull Context context) {
        SQLiteDatabase db = AppContext.getInstance().getDatabaseHelper().getReadableDatabase();
        Cursor c = db.query("currency", null, null,null,null,null,null);
        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int shortNameColIndex = c.getColumnIndex("shortname");
            int fullNameColIndex = c.getColumnIndex("fullname");
            int symbolColIndex = c.getColumnIndex("symbol");

            do {
                currencyList.add(new Currency(
                        c.getInt(idColIndex),
                        context.getString(c.getInt(shortNameColIndex)),
                        context.getString(c.getInt(fullNameColIndex)),
                        c.getString(symbolColIndex)
                ));
            } while (c.moveToNext());
        }
        c.close();
    }

    @Nullable
    public Currency getCurrencyByShortName(String name) {
        for (Currency c : currencyList) {
            if (c.getShortName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    @Nullable
    public Currency getCurrencyByFullName(String name) {
        for (Currency c : currencyList) {
            if (c.getFullName().equals(name)) {
                return c;
            }
        }
        return null;
    }
}
