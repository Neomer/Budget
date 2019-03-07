package my.neomer.budget;

import org.junit.Test;

import my.neomer.budget.widgets.DefaultMoneyTextFormatter;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DefaultMoneyTextFormatterTests {

    /**
     * Проверка на работу с положительными и отрицательными числами
     */
    @Test
    public void NegativePositiveTest() {
        DefaultMoneyTextFormatter formatter = new DefaultMoneyTextFormatter();

        assertEquals("-34.22", formatter.formatValue(-34.22));
        assertEquals("34.22", formatter.formatValue(34.22));
    }

    /**
     * Тест, проверяющий корректность округления введенной суммы до 2х знаков при вводе суммы с большим числом знаков.
     * Введенное число должно быть округлено до 2х знаков.
     */
    @Test
    public void CheckLongPrecision() {
        DefaultMoneyTextFormatter formatter = new DefaultMoneyTextFormatter();

        assertEquals("34.22", formatter.formatValue(34.2233));
        assertEquals("34.00", formatter.formatValue(34.0000));
        assertEquals("34.00", formatter.formatValue(34.0010));
        assertEquals("34.01", formatter.formatValue(34.0050));
        assertEquals("34.46", formatter.formatValue(34.4567));
        assertEquals("34.46", formatter.formatValue(34.4557));
        assertEquals("34.46", formatter.formatValue(34.4557));
    }

    /**
     * Тест, проверяющий корректность округления введенной суммы до 2х знаков при вводе суммы с меньшим числом знаков.
     * При этом должны быть установлены незначащие нули.
     */
    @Test
    public void CheckLessPrecision() {
        DefaultMoneyTextFormatter formatter = new DefaultMoneyTextFormatter();

        assertEquals("-120.00", formatter.formatValue(-120));
        assertEquals("34.20", formatter.formatValue(34.2));
        assertEquals("34.00", formatter.formatValue(34.0));
        assertEquals("34.00", formatter.formatValue(34));
        assertEquals("0.00", formatter.formatValue(0));
        assertEquals("0.00", formatter.formatValue(-0));
    }

}