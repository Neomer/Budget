package my.neomer.budget.core;

import java.util.List;

class DataLoaderProviderImpl<T> implements DataLoaderProvider<T> {

    List<DataLoader<T>> loadersList;

    @Override
    public void registerDataLoader(DataLoader<T> dataLoader) {
        loadersList.add(dataLoader);
    }

    @Override
    public void update() {
        for (DataLoader<T> l : loadersList) {
            l.loadData();
        }
    }

    @Override
    public List<T> getList() {
        return null;
    }
}
