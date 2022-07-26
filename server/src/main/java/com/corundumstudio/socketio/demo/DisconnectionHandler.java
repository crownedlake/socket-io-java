package com.corundumstudio.socketio.demo;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.demo.users.UsersHandler;
import com.corundumstudio.socketio.listener.DisconnectListener;

public class DisconnectionHandler implements DisconnectListener {
    private final UsersHandler usersHandler;
    public DisconnectionHandler(UsersHandler usersHandler){
        this.usersHandler = usersHandler;
    }
    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {
        usersHandler.disconnect(socketIOClient);
    }
}
