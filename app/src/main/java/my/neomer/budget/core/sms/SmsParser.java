package my.neomer.budget.core.sms;

import android.database.Cursor;

/**
 * Интрфейс для классов, отвечающих десериализацию СМС в класс Sms
 */
public interface SmsParser {

    Sms parse(Cursor cursor);

}
