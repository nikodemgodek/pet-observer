package com.example.pet_observer.model;

public class Email {

    private String to;
    private String subject;
    private String text;

    public Email(String text, String subject, String to) {
        this.text = text;
        this.subject = subject;
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
