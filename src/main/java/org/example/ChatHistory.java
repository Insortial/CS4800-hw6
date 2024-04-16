package org.example;
import java.time.LocalDateTime;
import java.util.*;

public class ChatHistory implements IterableByUser{
    private HashMap<String, ArrayList<Message>> userChats;

    public ChatHistory() {
        this.userChats = new HashMap<>();
    }

    public Iterator iterator(User userToSearchWith)
    {
        ArrayList<Message> messageList = getMessageHistoryByUser(userToSearchWith);
        return new searchMessagesByUser(messageList);
    }

    public void addMessage(Message message) {
        String userID = message.getSender();
        if(userChats.containsKey(userID)) {
            ArrayList<Message> userMessageHistory =  userChats.get(userID);
            userMessageHistory.add(message);
        } else {
            ArrayList<Message> userMessageHistory = new ArrayList<>();
            userMessageHistory.add(message);
            userChats.put(userID, userMessageHistory);
        }
    }

    public Message getLastMessage(String userID) {
        if(userChats.containsKey(userID)) {
            ArrayList<Message> userMessageHistory =  userChats.get(userID);
            return userMessageHistory.get(userMessageHistory.size() - 1);
        } else {
            return new Message("", "", "");
        }
    }

    public ArrayList<Message> getMessageHistoryByUser(User user) {
        return this.userChats.get(user.getUserID());
    }

    public void undoLastMessage(String userID, LocalDateTime timestamp, String messageBody) {
        if(userChats.containsKey(userID)) {
            ArrayList<Message> userMessageHistory =  userChats.get(userID);
            userMessageHistory.removeIf(msg -> (msg.getTimestamp().isEqual(timestamp)) && (msg.getMessageBody() == messageBody));
        }
    }
}
