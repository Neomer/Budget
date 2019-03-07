package my.neomer.budget.widgets;

import android.graphics.Color;

public class DefaultMoneyTextStylist implements MoneyTextStylist {

    @Override
    public void Stylize(MoneyView view, double value) {
        if (value < 0) {
            view.setTextColor(Color.rgb(136, 0, 0));
        } else {
            view.setTextColor(Color.rgb(0, 136, 0));
        }
    }

}
