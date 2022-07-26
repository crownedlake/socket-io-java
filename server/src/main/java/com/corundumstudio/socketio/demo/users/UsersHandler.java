package com.corundumstudio.socketio.demo.users;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UsersHandler {
    private final Map<UUID, User> userMap = new ConcurrentHashMap<>();
    private final Map<String, User> userNameMap = new ConcurrentHashMap<>();

    public void addUser(String displayName, UUID id) {
        if (userMap.containsKey(id)) {
            throw new IllegalStateException("user with id:" + id + " exists with name::" + userMap.get(id).getDisplayName());
        }

        User user = new User(displayName, id);

        userMap.put(id, user);
        userNameMap.put(user.getDisplayName(), user);
    }

    //todo handle duplicates
    public boolean updateUserName(String displayName, UUID id) {
        if (!userMap.containsKey(id)) {
            throw new IllegalStateException("user with id:" + id + " does not exist");
        }

        if (userNameMap.containsKey(displayName)) {
            throw new IllegalStateException("user with name:" + displayName + " already exist");
        }

        userMap.get(id).updateDisplayName(displayName);
        return true;
    }

    public User getIOClient(String name) {
        if (!userNameMap.containsKey(name)) {
            throw new IllegalStateException("user with name:" + name + " does not exist");
        }

        return userNameMap.get(name);
    }

    public void refreshActiveUsers(SocketIOServer server) {
//        for (User user : userMap.values()) {
//            if(server.getClient(user.getUuid())!=null){
//                System.err.println(user.getDisplayName()+"::"+server.getClient(user.getUuid()).isChannelOpen());
//            }else {
//                System.err.println(user.getDisplayName()+"::"+false);
//            }
//        }
    }

    public void disconnect(SocketIOClient client) {
        User removed = userMap.remove(client.getSessionId());
        if(removed != null){
            userNameMap.remove(removed.getDisplayName());
        }
    }

    public ArrayList<String> fetchUsers(boolean isActive, SocketIOServer server) {
        ArrayList<String> activeUsers = new ArrayList<>();
        for(SocketIOClient client : server.getAllClients()){
            if(!userMap.containsKey(client.getSessionId())){
                continue;
            }

            if(client.isChannelOpen() == isActive){
                activeUsers.add(userMap.get(client.getSessionId()).getDisplayName());
            }
        }

        return activeUsers;
    }
}
