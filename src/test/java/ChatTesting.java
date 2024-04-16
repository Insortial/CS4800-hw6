//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class ChatTesting {
    @Test
    public void messageMementoTest() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("u1", chatServer);
        User user2 = new User("u2", chatServer);
        chatServer.addUser(user1);
        chatServer.addUser(user2);

        String testMessage1 = "Hello what are you up to";
        String testMessage2 = "Hello are you up to";
        user1.sendMessage(testMessage1, "u2");
        user1.sendMessage(testMessage2, "u2");

        System.out.println(user1.undo().getMessageBody());

        Assertions.assertEquals(testMessage1, user1.getLastSentMessage());
    }

    @Test
    public void undoMessageTest() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("u1", chatServer);
        User user2 = new User("u2", chatServer);
        User user3 = new User("u3", chatServer);
        chatServer.addUser(user1);
        chatServer.addUser(user2);
        chatServer.addUser(user3);

        String testMessage = "Hello what are you up to";
        user1.sendMessage(testMessage, "u2", "u3");
        String testMessage2 = "Hello what are you u";
        user1.sendMessage(testMessage2, "u2", "u3");


        Assertions.assertEquals(testMessage2, user2.getLastMessageByUserID("u1").getMessageBody());
        Assertions.assertEquals(testMessage2, user3.getLastMessageByUserID("u1").getMessageBody());

        user1.undo();

        Assertions.assertEquals(testMessage, user2.getLastMessageByUserID("u1").getMessageBody());
        Assertions.assertEquals(testMessage, user3.getLastMessageByUserID("u1").getMessageBody());
    }

    @Test
    public void sendMessageTest() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("u1", chatServer);
        User user2 = new User("u2", chatServer);
        User user3 = new User("u3", chatServer);
        chatServer.addUser(user1);
        chatServer.addUser(user2);
        chatServer.addUser(user3);

        String testMessage = "Hello what are you up to";
        user1.sendMessage(testMessage, "u2", "u3");

        Assertions.assertEquals(testMessage, user2.getLastMessageByUserID("u1").getMessageBody());
        Assertions.assertEquals(testMessage, user3.getLastMessageByUserID("u1").getMessageBody());
    }

    @Test
    public void blockUserTest() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("u1", chatServer);
        User user2 = new User("u2", chatServer);
        User user3 = new User("u3", chatServer);
        chatServer.addUser(user1);
        chatServer.addUser(user2);
        chatServer.addUser(user3);

        String testMessage = "Hello what are you up to";
        user3.blockUser("u1");
        user1.sendMessage(testMessage, "u2", "u3");

        Assertions.assertEquals(testMessage, user2.getLastMessageByUserID("u1").getMessageBody());
        Assertions.assertNotEquals(testMessage, user3.getLastMessageByUserID("u1").getMessageBody());
    }

    @Test
    public void searchMessagesByUserTest() {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("u1", chatServer);
        User user2 = new User("u2", chatServer);
        chatServer.addUser(user1);
        chatServer.addUser(user2);

        String[] messageArray = {"Hello what are you up to", "Hello what p to", "Hell are you up to", "Hello what ar to"};
        user1.sendMessage(messageArray[0], "u2");
        user1.sendMessage(messageArray[1], "u2");
        user1.sendMessage(messageArray[2], "u2");
        user1.sendMessage(messageArray[3], "u2");

        Iterator messageIterator = user2.iterator(user1);
        int count = 0;
        while(messageIterator.hasNext())
        {
            Message nextMessage = (Message) messageIterator.next();
            Assertions.assertNotEquals(messageArray[count], nextMessage.getMessageBody());
            count += 1;
        }
    }

}
