package my.neomer.budget.core.types;

import java.util.ArrayList;
import java.util.List;

import my.neomer.budget.R;

public class TransactionCategoryFactory {

    //region sigleton
    private static final TransactionCategoryFactory ourInstance = new TransactionCategoryFactory();

    public static TransactionCategoryFactory getInstance() {
        return ourInstance;
    }
    //endregion

    private List<TransactionCategory > categoryList;

    private TransactionCategoryFactory() {
        categoryList = new ArrayList<>();
        categoryList.add(new TransactionCategory(0, "Unknown", null));
        categoryList.add(new TransactionCategory(1, "Food", null, R.xml.food_category_patterns));
        categoryList.add(new TransactionCategory(2, "Medicine", null));
        categoryList.add(new TransactionCategory(3, "Clothes", null));
        categoryList.add(new TransactionCategory(4, "Other", null));
    }

    public TransactionCategory getById(int id) {
        for(TransactionCategory category : categoryList) {
            if (category.getId() == id) {
                try {
                    return (TransactionCategory)category.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public TransactionCategory findByPattern(String needle) {
        for(TransactionCategory category : categoryList) {
            if (category.isValid(needle)) {
                try {
                    return (TransactionCategory)category.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
