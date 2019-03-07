package my.neomer.budget.widgets;

import org.apache.commons.math3.util.Precision;

public class DefaultMoneyTextFormatter implements MoneyTextFormatter {

    @Override
    public String formatValue(double value) {
        double roundedValue = Precision.round(value, 2);
        int fl = ((int)(Math.abs(roundedValue) * 1000)) % 100;
        return String.valueOf(roundedValue) + (fl > 0 ? "" : "0");
    }

}
