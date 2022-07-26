package com.corundumstudio.socketio.demo.messages;

public class UpdateNameMessage {
    private String userName;

    public UpdateNameMessage(){

    }
    public UpdateNameMessage(String userName) {
        super();
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
}
