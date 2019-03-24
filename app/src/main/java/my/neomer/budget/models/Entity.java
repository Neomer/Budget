package my.neomer.budget.models;

import android.database.Cursor;

import my.neomer.budget.core.database.EntityManager;

public interface Entity<T> {

    T getId();
    void setId(T value);

    void map(Cursor c);
}
