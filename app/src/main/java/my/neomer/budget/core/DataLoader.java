package my.neomer.budget.core;

import java.util.List;

public interface DataLoader<Type> {

    List<Type> loadData();

}
