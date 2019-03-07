package my.neomer.budget.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class MoneyView extends android.support.v7.widget.AppCompatTextView {

    private MoneyTextFormatter moneyTextFormatter;

    public MoneyView(Context context) {
        super(context);
        moneyTextFormatter = new DefaultMoneyTextFormatter();
    }

    public MoneyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        moneyTextFormatter = new DefaultMoneyTextFormatter();
    }

    public MoneyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        moneyTextFormatter = new DefaultMoneyTextFormatter();
    }

    public void setText(double value) {
        if (value > 0) {
            super.setText(moneyTextFormatter.formatValue(value));
        }
    }

    public void setMoneyTextFormatter(@NonNull MoneyTextFormatter moneyTextFormatter) {
        this.moneyTextFormatter = moneyTextFormatter;
    }
}
