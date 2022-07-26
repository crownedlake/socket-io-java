package com.corundumstudio.socketio.demo.messages;

import java.util.ArrayList;

public class ActiveUsersList {
    private ArrayList<String> userNames = new ArrayList<>();

    public ActiveUsersList() {

    }

    public ActiveUsersList(ArrayList<String> userNames) {
        super();
        this.userNames = userNames;
    }

    public ArrayList<String> getUserNames() {
        return userNames;
    }

    public void setUserName(ArrayList<String> userNames) {
        this.userNames = userNames;
    }
}
