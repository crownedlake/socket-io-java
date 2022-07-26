package com.corundumstudio.socketio.demo.users;

import java.util.UUID;

public class User {
    private String displayName;
    private final UUID uuid;

    public User(String displayName, UUID uuid){
        this.displayName = displayName;
        this.uuid = uuid;
    }

    public void updateDisplayName(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UUID getUuid(){
        return uuid;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
