package org.example;

import java.time.LocalDateTime;

public class MessageMemento {
    private String[] recipients;
    private String messageBody;
    private LocalDateTime timestamp;

    public MessageMemento(String messageBody, LocalDateTime timestamp, String... recipients) {
        this.recipients = recipients;
        this.messageBody = messageBody;
        this.timestamp = timestamp;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String[] getRecipients() {
        return recipients;
    }

}
