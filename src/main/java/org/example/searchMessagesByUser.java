package org.example;

import java.util.ArrayList;
import java.util.Iterator;

public class searchMessagesByUser implements Iterator {
    private ArrayList<Message> chatHistory;
    private int indexInUserChatHistory;
    private int userChatHistorySize;

    searchMessagesByUser(ArrayList<Message> obj) {
        this.indexInUserChatHistory = 0;
        this.userChatHistorySize = obj.size();
        this.chatHistory = obj;
    }

    // Checks if the next element exists
    @Override
    public boolean hasNext() {
        while(indexInUserChatHistory < userChatHistorySize) {
            return true;
        }
        return false;
    }

    // moves the cursor/iterator to next element
    @Override
    public Message next() {
        if(hasNext()) {
            return chatHistory.get(indexInUserChatHistory++);
        }
        return null;
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }
}
