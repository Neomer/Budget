package my.neomer.budget;

import org.junit.Test;

import my.neomer.budget.activities.main.TransactionsRecyclerViewAdapter;

public class TransactionsRecyclerViewAdapterTest {

    @Test(expected = NullPointerException.class)
    public void NullListTest()
    {
        TransactionsRecyclerViewAdapter adapter = new TransactionsRecyclerViewAdapter(null);
    }

}