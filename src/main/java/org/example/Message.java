package org.example;

import java.time.LocalDateTime;

public class Message {
    private String[] recipients;
    private String sender;
    private String messageBody;
    private LocalDateTime timestamp;

    public Message(String sender, String messageBody, String... recipients) {
        this.sender = sender;
        this.recipients = recipients;
        this.messageBody = messageBody;
        this.timestamp = LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public String getSender() {
        return sender;
    }

    public MessageMemento takeSnapshot() {
        return new MessageMemento(this.messageBody, this.timestamp, this.recipients);
    }

}
