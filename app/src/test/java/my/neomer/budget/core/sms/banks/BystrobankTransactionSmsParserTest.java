package my.neomer.budget.core.sms.banks;

import org.junit.Test;

import my.neomer.budget.core.sms.Sms;
import my.neomer.budget.core.sms.TransactionSmsParser;

import static org.junit.Assert.*;

public class BystrobankTransactionSmsParserTest {

    @Test
    public void ValidIncomeMessageTest() {
        Sms sms = new Sms();
        sms.setAddress("BystroBank");
        TransactionSmsParser parser = new BystrobankTransactionSmsParser();

        sms.setBody("28.02.19 04:21 Kapitalizaciya Prihod 0.74 RUB Schet 2726149 Balans 23150.72 RUB");
        assertTrue(parser.isValid(sms));

        sms.setBody("27.02.19 15:21 Platezhnoe poruchenie - Z.P. Prihod 10000.00 RUB Schet 2726149 Balans 23492.98 RUB");
        assertTrue(parser.isValid(sms));
    }


    @Test
    public void ValidSpendMessageTest() {

        Sms sms = new Sms();
        sms.setAddress("900");
        TransactionSmsParser parser = new BystrobankTransactionSmsParser();

        sms.setBody("ECMC6994 10:12 покупка 85.15р EIG*MYDOMAIN Баланс:1646.41р");
        assertTrue(parser.isValid(sms));

        sms.setBody("ECMC6994 14:08 покупка 84р MAGNIT Баланс:1561.51р");
        assertTrue(parser.isValid(sms));

        sms.setBody("ECMC6994 10:12 покупка 85.15р EIG-MY_D.MAIN Баланс:1646.41р");
        assertTrue(parser.isValid(sms));
    }
}