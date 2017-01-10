package com.example.gleb.deliveryphones.events;

public class SignUpEvent {
    private String emailHash;

    public SignUpEvent(String emailHash) {
        this.emailHash = emailHash;
    }

    public String getEmailHash() {
        return emailHash;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }
}
