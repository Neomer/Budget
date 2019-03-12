package my.neomer.budget.core.sms.banks;

import org.junit.Test;

import my.neomer.budget.core.sms.Sms;
import my.neomer.budget.core.sms.banks.SbTransactionSmsParser;

import static org.junit.Assert.*;

public class SbTransactionSmsParserTest {

    @Test
    public void ValidIncomeMessageTest() {

        Sms sms = new Sms();
        sms.setAddress("900");
        sms.setBody("ECMC6994 12:27 зачисление 13100р Баланс:14753.41р");

        SbTransactionSmsParser parser = new SbTransactionSmsParser();

        assertTrue(parser.isValid(sms));
    }

    @Test
    public void ValidSpendMessageTest() {

        Sms sms = new Sms();
        sms.setAddress("900");
        SbTransactionSmsParser parser = new SbTransactionSmsParser();

        sms.setBody("ECMC6994 10:12 покупка 85.15р EIG*MYDOMAIN Баланс:1646.41р");
        assertTrue(parser.isValid(sms));

        sms.setBody("ECMC6994 14:08 покупка 84р MAGNIT Баланс:1561.51р");
        assertTrue(parser.isValid(sms));

        sms.setBody("ECMC6994 10:12 покупка 85.15р EIG-MY_D.MAIN Баланс:1646.41р");
        assertTrue(parser.isValid(sms));
    }
}