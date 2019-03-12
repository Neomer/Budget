package my.neomer.budget.widgets;

import android.graphics.Color;

import my.neomer.budget.core.types.Money;

public class DefaultMoneyTextStylist implements MoneyTextStylist {

    @Override
    public void Stylize(MoneyView view, Money value) {
        if (value.getAmount() < 0) {
            view.setTextColor(Color.rgb(136, 0, 0));
        } else {
            view.setTextColor(Color.rgb(0, 136, 0));
        }
    }

}
