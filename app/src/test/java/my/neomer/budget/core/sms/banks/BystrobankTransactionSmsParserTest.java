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

        sms.setBody("07.03.19 18:04 Pokupka 825.00 RUB Schet 2726149 Karta **6654 Moscow, DELIVERY-CLUB Balans 14562.45 RUB");
        assertTrue(parser.isValid(sms));
    }


    @Test
    public void ValidSpendMessageTest() {

        Sms sms = new Sms();
        sms.setAddress("BystroBank");
        TransactionSmsParser parser = new BystrobankTransactionSmsParser();

        sms.setBody("01.03.19 06:40 Komissiya - gashenie Rashod 59.00 RUB Schet 2726149 Balans 23091.72 RUB");
        assertTrue(parser.isValid(sms));

        sms.setBody("08.03.19 12:14 Pokupka 1600.00 RUB Schet 2726149 Karta **6654 IP STRIZHOV S.A, Balans 12243.45 RUB");
        assertTrue(parser.isValid(sms));

        sms.setBody("08.03.19 13:31 Pokupka 270.00 RUB Schet 2726149 Karta **6654 BUKET Balans 11973.45 RUB");
        assertTrue(parser.isValid(sms));
    }
}