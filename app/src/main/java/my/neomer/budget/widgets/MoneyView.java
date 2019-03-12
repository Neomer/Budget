package my.neomer.budget.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import my.neomer.budget.core.types.Money;

public class MoneyView extends android.support.v7.widget.AppCompatTextView {

    private MoneyTextFormatter moneyTextFormatter;
    private MoneyTextStylist moneyTextStylist;

    public MoneyView(Context context) {
        super(context);
        moneyTextFormatter = new DefaultMoneyTextFormatter();
        moneyTextStylist = new DefaultMoneyTextStylist();
    }

    public MoneyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        moneyTextFormatter = new DefaultMoneyTextFormatter();
        moneyTextStylist = new DefaultMoneyTextStylist();
    }

    public MoneyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        moneyTextFormatter = new DefaultMoneyTextFormatter();
        moneyTextStylist = new DefaultMoneyTextStylist();
    }

    public void setMoney(Money value) {
        super.setText(moneyTextFormatter.formatValue(value));
        moneyTextStylist.Stylize(this, value);
    }

    public void setMoneyTextFormatter(MoneyTextFormatter moneyTextFormatter) throws NullPointerException {
        if (moneyTextFormatter == null) {
            throw new NullPointerException();
        }
        this.moneyTextFormatter = moneyTextFormatter;
    }

    public void setMoneyTextStylist(MoneyTextStylist moneyTextStylist) throws NullPointerException {
        if (moneyTextStylist == null) {
            throw new NullPointerException();
        }
        this.moneyTextStylist = moneyTextStylist;
    }
}
