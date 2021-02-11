package com.mm.ussd.request;

import org.springframework.lang.NonNull;

public class UssdRequest {
    @NonNull
    private String sessionId;
    @NonNull
    private String msisdn;

    private String userEntry;

    @NonNull
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @NonNull
    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getUserEntry() {
        return userEntry;
    }

    public void setUserEntry(String userEntry) {
        this.userEntry = userEntry;
    }
}
