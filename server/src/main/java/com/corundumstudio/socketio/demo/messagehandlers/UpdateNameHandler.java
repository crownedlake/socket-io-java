package com.corundumstudio.socketio.demo.messagehandlers;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.demo.messages.UpdateNameMessage;
import com.corundumstudio.socketio.demo.users.UsersHandler;
import com.corundumstudio.socketio.listener.DataListener;

import java.util.UUID;

public class UpdateNameHandler implements DataListener<UpdateNameMessage> {
    private final SocketIOServer server;
    private final UsersHandler usersHandler;

    protected UpdateNameHandler(SocketIOServer server, UsersHandler usersHandler) {
        this.server = server;
        this.usersHandler = usersHandler;
    }

    @Override
    public void onData(SocketIOClient client, UpdateNameMessage data, AckRequest ackRequest) throws Exception {
        UUID id = client.getSessionId();
        usersHandler.addUser(data.getUserName(),id);
    }
}
