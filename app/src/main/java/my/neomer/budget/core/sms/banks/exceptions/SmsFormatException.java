package my.neomer.budget.core.sms.banks.exceptions;

/**
 * Формат СМС не поддерживается
 */
public class SmsFormatException extends Exception {

    public SmsFormatException() {

    }

    public SmsFormatException(String message) {
        super(message);
    }

}

