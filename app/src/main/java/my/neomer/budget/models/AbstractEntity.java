package my.neomer.budget.models;

import android.database.Cursor;

public abstract class AbstractEntity<T> implements Entity<T> {

    private T id;

    public AbstractEntity(T id) {
        this.id = id;
    }

    public AbstractEntity() {

    }

    @Override
    public T getId() {
        return null;
    }

    @Override
    public void setId(T value) {
        id = value;
    }

}
