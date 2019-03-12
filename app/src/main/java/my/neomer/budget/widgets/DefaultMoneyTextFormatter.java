package my.neomer.budget.widgets;

import org.apache.commons.math3.util.Precision;

import my.neomer.budget.core.types.Money;

public class DefaultMoneyTextFormatter implements MoneyTextFormatter {

    @Override
    public String formatValue(Money value) {
        double roundedValue = Precision.round(value.getAmount(), 2);
        int fl = ((int)(Math.abs(roundedValue) * 1000)) % 100;
        return String.valueOf(roundedValue) + (fl > 0 ? "" : "0");
    }

}
