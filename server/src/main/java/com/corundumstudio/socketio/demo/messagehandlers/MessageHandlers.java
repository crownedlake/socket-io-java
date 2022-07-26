package com.corundumstudio.socketio.demo.messagehandlers;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.demo.users.User;
import com.corundumstudio.socketio.demo.users.UsersHandler;

public class MessageHandlers {
    public final ChatHandler chatHandler;
    public final UpdateNameHandler updateNameHandler;
    public final FetchUsersHandler fetchUsersHandler;

    public MessageHandlers(SocketIOServer server, UsersHandler usersHandler) {
        this.chatHandler = new ChatHandler(server,usersHandler);
        this.updateNameHandler = new UpdateNameHandler(server,usersHandler);
        this.fetchUsersHandler = new FetchUsersHandler(server,usersHandler);
    }
}
