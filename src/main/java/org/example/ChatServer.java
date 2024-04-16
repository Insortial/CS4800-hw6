package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ChatServer {
    private HashMap<String, User> registeredUsers;
    private HashMap<String, ArrayList<String>> userBlockList;
    private HashMap<Set,ChatHistory> chatLogs;

    public ChatServer() {
        this.registeredUsers = new HashMap<>();
        this.userBlockList = new HashMap<>();
    }

    public void addUser(User user) {
        registeredUsers.put(user.getUserID(), user);
        userBlockList.put(user.getUserID(), new ArrayList<>());
    }

    public void blockUser(String sender, String blockedUser)
    {
        if(registeredUsers.containsKey(sender))
        {
            ArrayList<String> blockList = userBlockList.get(sender);
            blockList.add(blockedUser);
        }
    }
    public boolean removeUser(String userID) {
        if(registeredUsers.containsKey(userID))
        {
            registeredUsers.remove(userID);
            userBlockList.remove(userID);
            return true;
        } else
        {
            return false;
        }
    }

    public Message sendMessage(String userID, String messageBody, String... recipients) {
        for (String user: recipients)
        {
            if(registeredUsers.containsKey(user) && !userBlockList.get(user).contains(userID))
            {

                Message sentMessage = new Message(userID, messageBody, new String[]{user});
                User recipient = registeredUsers.get(user);
                recipient.receiveMessage(sentMessage);
            }
        }
        Message sentMessage = new Message(userID, messageBody, recipients);
        return sentMessage;
    }

    public void undo(String userID, MessageMemento lastMessageMemento) {
        for(String user : lastMessageMemento.getRecipients())
        {
            if(registeredUsers.containsKey(user))
            {
                User recipient = registeredUsers.get(user);
                recipient.undoMessageSent(userID, lastMessageMemento.getTimestamp(), lastMessageMemento.getMessageBody());
            }
        }
    }
}
