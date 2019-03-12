package my.neomer.budget.core.sms;

import com.sun.org.apache.commons.beanutils.BeanUtils;

import org.joda.time.DateTime;

import java.util.Date;

public class Sms {
    private int id;
    private int threadId;
    private String address;
    private DateTime date;
    private DateTime dateSent;
    private int protocol;
    private int read;
    private int status;
    private int type;
    private int replyPathPresent;
    private String subject;
    private String body;
    private int seen;

    public Sms() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getReplyPathPresent() {
        return replyPathPresent;
    }

    public void setReplyPathPresent(int replyPathPresent) {
        this.replyPathPresent = replyPathPresent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public DateTime getDateSent() {
        return dateSent;
    }

    public void setDateSent(DateTime dateSent) {
        this.dateSent = dateSent;
    }
}
