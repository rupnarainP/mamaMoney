package com.mm.ussd.entity;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="Ussd")
public class UssdEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String sessionId;

    private String msisdn;

    private String userEntry;

    private int processId;

    private String foreignCurrencyCode;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String id) {
        this.sessionId = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String name) {
        this.msisdn = name;
    }

    public String getUserEntry() {
        return userEntry;
    }

    public void setUserEntry(String email) {
        this.userEntry = email;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public String getForeignCurrencyCode() {
        return foreignCurrencyCode;
    }

    public void setForeignCurrencyCode(String foreignCurrencyCode) {
        this.foreignCurrencyCode = foreignCurrencyCode;
    }
}
