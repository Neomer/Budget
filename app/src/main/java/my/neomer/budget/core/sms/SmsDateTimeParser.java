package my.neomer.budget.core.sms;

import org.joda.time.DateTime;

public interface SmsDateTimeParser {

    DateTime parseDate(String dateString);

}
