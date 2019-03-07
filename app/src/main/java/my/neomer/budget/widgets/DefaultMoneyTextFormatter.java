package my.neomer.budget.widgets;

import org.apache.commons.math3.util.Precision;

public class DefaultMoneyTextFormatter implements MoneyTextFormatter {

    @Override
    public String formatValue(double value) {
        return String.valueOf(Precision.round(value, 2));
    }

}
