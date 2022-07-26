package com.corundumstudio.socketio.demo.messagehandlers;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.demo.messages.ActiveUsersList;
import com.corundumstudio.socketio.demo.users.UsersHandler;
import com.corundumstudio.socketio.listener.DataListener;

import java.util.ArrayList;

public class FetchUsersHandler implements DataListener<ActiveUsersList> {
    private final UsersHandler usersHandler;
    private final SocketIOServer server;

    public FetchUsersHandler(SocketIOServer server, UsersHandler usersHandler) {
        this.usersHandler = usersHandler;
        this.server = server;
    }

    @Override
    public void onData(SocketIOClient client, ActiveUsersList activeUsersList, AckRequest ackRequest) throws Exception {
        ArrayList<String> activeUNames = usersHandler.fetchUsers(true,server);
        activeUsersList.setUserName(activeUNames);
        client.sendEvent(EventNames.FetchUsers.name(),activeUsersList);
    }
}
