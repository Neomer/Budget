package my.neomer.budget.widgets;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MoneyViewTest {

    private Context createContext() {
        return  InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void setMoneyTextFormatter() {
        MoneyView view = new MoneyView(createContext());
        view.setMoneyTextFormatter(null);
    }

    @Test
    public void setMoneyTextStylist() {
        MoneyView view = new MoneyView(createContext());
        view.setMoneyTextFormatter(null);
    }
}