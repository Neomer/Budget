package my.neomer.budget.widgets;

/**
 * Интерфейс для форматирования вывода суммы денег
 */
public interface MoneyTextFormatter {

    /**
     * Метод для форматирования вывода суммы денег
     * @param value Сумма
     * @return Форматированное значение
     */
    String formatValue(double value);

}
