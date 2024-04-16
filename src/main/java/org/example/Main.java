package org.example;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("u1", chatServer);
        User user2 = new User("u2", chatServer);
        User user3 = new User("u3", chatServer);
        chatServer.addUser(user1);
        chatServer.addUser(user2);
        chatServer.addUser(user3);
        String testMessage1 = "Example test";
        user1.sendMessage(testMessage1, "u2");
        System.out.println("Testing sending and receiving messages");
        System.out.println("user3 - Recent message from user1: " + user3.getLastMessageByUserID("u1").getMessageBody());

        String testMessage2 = "Hello what are you up to";
        user1.sendMessage(testMessage2, "u2", "u3");
        String testMessage3 = "Hello what are you u";
        user1.sendMessage(testMessage3, "u2", "u3");

        System.out.println("\nTesting undo feature");
        System.out.println("user2 - Recent message from user1: " + user2.getLastMessageByUserID("u1").getMessageBody());
        user1.undo();
        System.out.println("user2 - Recent message from user1 after undo: " + user2.getLastMessageByUserID("u1").getMessageBody());

        String blockedMessage = "This is a blocked message will not show up on user 3";
        user3.blockUser("u1");
        user1.sendMessage(blockedMessage, "u2", "u3");
        System.out.println("\nSending message, blocked by user3");
        System.out.println("user2 - Recent message from user1: " + user2.getLastMessageByUserID("u1").getMessageBody());
        System.out.println("user2 - Recent message from user1: " + user3.getLastMessageByUserID("u1").getMessageBody());

        System.out.println("\nDemonstrating viewing chat history and use of iterator");
        Iterator messageIterator = user2.iterator(user1);
        System.out.println("Message History for user2:");
        while(messageIterator.hasNext())
        {
            Message nextMessage = (Message) messageIterator.next();
            System.out.println(nextMessage.getMessageBody());
        }
    }
}