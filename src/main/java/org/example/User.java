package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class User implements IterableByUser{
   private String userID;
   private ChatServer chatServer;
   private ChatHistory chatHistory;
   private Deque<MessageMemento> lastMessageHistory;

    public User(String userID, ChatServer chatServer)
    {

        this.userID = userID;
        this.chatServer = chatServer;
        this.chatHistory = new ChatHistory();
        this.lastMessageHistory = new LinkedList<>();
    }

    public Iterator iterator(User userToSearchWith)
    {
        return chatHistory.iterator(userToSearchWith);
    }

    public String getUserID() {
        return userID;
    }

    public void blockUser(String blockedUserID)
    {
        this.chatServer.blockUser(this.userID, blockedUserID);
    }
    public void sendMessage(String messageBody, String... recipients)
    {
        Message sentMessage = chatServer.sendMessage(this.userID, messageBody, recipients);
        this.lastMessageHistory.push(sentMessage.takeSnapshot());
        receiveMessage(sentMessage);
    }

    public Message receiveMessage(Message receivedMessage)
    {
        this.chatHistory.addMessage(receivedMessage);
        return receivedMessage;
    }

    public String getLastSentMessage()
    {
        return this.lastMessageHistory.peek().getMessageBody();
    }
    public Message getLastMessageByUserID(String userID)
    {
        return this.chatHistory.getLastMessage(userID);
    }

    public MessageMemento undo() {
        MessageMemento lastMessageMemento = this.lastMessageHistory.pop();
        chatServer.undo(this.userID, lastMessageMemento);
        return lastMessageMemento;
    }

    public void undoMessageSent(String sender, LocalDateTime timestamp, String messageBody)
    {
        chatHistory.undoLastMessage(sender, timestamp, messageBody);
    }
}
