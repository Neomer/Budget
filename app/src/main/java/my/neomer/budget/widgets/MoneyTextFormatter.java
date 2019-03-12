package my.neomer.budget.widgets;

import my.neomer.budget.core.types.Money;

/**
 * Интерфейс для форматирования вывода суммы денег
 */
public interface MoneyTextFormatter {

    /**
     * Метод для форматирования вывода суммы денег
     * @param value Сумма
     * @return Форматированное значение
     */
    String formatValue(Money value);

}
