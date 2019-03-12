package my.neomer.budget.core;

import java.util.List;

import my.neomer.budget.models.Transaction;

public interface DataLoaderProvider<T> {

    void registerDataLoader(DataLoader<T> dataLoader);

    void update();

    List<T> getList();

}
