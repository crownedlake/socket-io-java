package com.corundumstudio.socketio.demo.messages;

public class ChatMessage {
    private String userName;
    private String message;

    public ChatMessage() {
    }

    public ChatMessage(String userName, String message) {
        super();
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
