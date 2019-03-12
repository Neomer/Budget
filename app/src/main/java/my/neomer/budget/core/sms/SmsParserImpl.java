package my.neomer.budget.core.sms;

import android.database.Cursor;

class SmsParserImpl implements SmsParser {
    //// _id:3 thread_id:4 address:900 person:null date:1552126115847 date_sent:1552154915000 protocol:0 read:1 status:-1 type:1 reply_path_present:0 subject:null body:ECMC6994 14:08 Покупка 84.90р MAGNIT Баланс:1561.51 service_center:null locked:0 sub_id:1 error_code:0 creator:com.google.android.apps.messaging seen:1

    private static String COLUMN_ID = "_id";
    private static String COLUMN_DATE = "date";
    private static String COLUMN_DATE_SENT = "date_sent";
    private static String COLUMN_BODY = "body";
    private static String COLUMN_ADDRESS = "address";

    private SmsDateTimeParser dateTimeParser;

    public SmsParserImpl() {
        dateTimeParser = new SmsDateTimeParserImpl();
    }

    @Override
    public Sms parse(Cursor cursor) {
        Sms result = new Sms();

        if (cursor != null) {
            for (int idx = 0; idx < cursor.getColumnCount(); ++idx) {
                String column = cursor.getColumnName(idx);
                if (column.equals(COLUMN_ID)) {
                    result.setId(cursor.getInt(idx));
                } else if (column.equals(COLUMN_DATE)) {
                    result.setDate(dateTimeParser.parseDate(cursor.getString(idx)));
                } else if (column.equals(COLUMN_DATE_SENT)) {
                    result.setDateSent(dateTimeParser.parseDate(cursor.getString(idx)));
                } else if (column.equals(COLUMN_BODY)) {
                    result.setBody(cursor.getString(idx));
                } else if (column.equals(COLUMN_ADDRESS)) {
                    result.setAddress(cursor.getString(idx));
                }
            }
        }

        return result;
    }

}
