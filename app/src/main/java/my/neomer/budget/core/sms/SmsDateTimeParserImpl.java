package my.neomer.budget.core.sms;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

class SmsDateTimeParserImpl implements SmsDateTimeParser {

    private DateTimeFormatter dateTimeFormatter;

    public SmsDateTimeParserImpl() {
        dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public DateTime parseDate(String dateString) {
        return new DateTime(Long.valueOf(dateString));
    }
}
