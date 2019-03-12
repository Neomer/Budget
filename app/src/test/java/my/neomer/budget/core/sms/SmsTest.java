package my.neomer.budget.core.sms;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.*;

public class SmsTest {

    @Test
    public void CreationTest() {
        Sms sms = new Sms();

        sms.setId(1);
        sms.setAddress("address");
        sms.setBody("body");
        sms.setDate(new DateTime(0));
        sms.setDateSent(new DateTime(0));
        sms.setRead(1);
        sms.setReplyPathPresent(1);
        sms.setSeen(1);
        sms.setStatus(1);
        sms.setSubject("subject");
        sms.setProtocol(1);
        sms.setThreadId(1);
        sms.setType(1);

        assertEquals(1, sms.getId());
        assertEquals("address", sms.getAddress());
        assertEquals("body", sms.getBody());
        assertEquals(new DateTime(0), sms.getDate());
        assertEquals(new DateTime(0), sms.getDateSent());
        assertEquals(1, sms.getRead());
        assertEquals(1, sms.getReplyPathPresent());
        assertEquals(1, sms.getSeen());
        assertEquals(1, sms.getStatus());
        assertEquals("subject", sms.getSubject());
        assertEquals(1, sms.getProtocol());
        assertEquals(1, sms.getThreadId());
        assertEquals(1, sms.getType());
    }

}