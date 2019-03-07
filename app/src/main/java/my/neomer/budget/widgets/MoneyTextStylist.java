package my.neomer.budget.widgets;

/**
 * Интерфейс для стилизирования виджета для отображения текста исходя из значения
 */
public interface MoneyTextStylist {

    /**
     * Стилизирует вывод суммы, исходя из значения
     * @param view Элемент, для отобржаения суммы
     * @param value Сумма
     */
    void Stylize(MoneyView view, double value);

}
