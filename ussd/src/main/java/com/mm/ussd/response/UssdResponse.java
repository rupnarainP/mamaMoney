package com.mm.ussd.response;

import org.springframework.lang.NonNull;

public class UssdResponse {
    @NonNull
    private String sessionId;
    @NonNull
    private String message;

    @NonNull
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @NonNull
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
