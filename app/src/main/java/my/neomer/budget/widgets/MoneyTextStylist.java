package my.neomer.budget.widgets;

import my.neomer.budget.core.types.Money;

/**
 * Интерфейс для стилизирования виджета для отображения текста исходя из значения
 */
public interface MoneyTextStylist {

    /**
     * Стилизирует вывод суммы, исходя из значения
     * @param view Элемент, для отобржаения суммы
     * @param value Сумма
     */
    void Stylize(MoneyView view, Money value);

}
