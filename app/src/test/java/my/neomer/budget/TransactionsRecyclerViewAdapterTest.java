package my.neomer.budget;

import org.junit.Test;

import java.util.ArrayList;

import my.neomer.budget.models.Transaction;

import static org.junit.Assert.*;

public class TransactionsRecyclerViewAdapterTest {

    @Test(expected = NullPointerException.class)
    public void NullListTest()
    {
        TransactionsRecyclerViewAdapter adapter = new TransactionsRecyclerViewAdapter(null);
    }

}