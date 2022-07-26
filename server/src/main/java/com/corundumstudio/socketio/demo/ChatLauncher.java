package com.corundumstudio.socketio.demo;

import com.corundumstudio.socketio.demo.messagehandlers.EventNames;
import com.corundumstudio.socketio.demo.messagehandlers.MessageHandlers;
import com.corundumstudio.socketio.demo.messages.ActiveUsersList;
import com.corundumstudio.socketio.demo.messages.ChatMessage;
import com.corundumstudio.socketio.demo.messages.UpdateNameMessage;
import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.demo.users.UsersHandler;

public class ChatLauncher {
    private static final UsersHandler usersHandler = new UsersHandler();

    public static void main(String[] args) throws InterruptedException {

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        final SocketIOServer server = new SocketIOServer(config);
        registerHandlers(server);

        server.start();

        Thread.sleep(Integer.MAX_VALUE);

        server.stop();
    }

    private static void registerHandlers(SocketIOServer server) {
        MessageHandlers messageHandler = new MessageHandlers(server, ChatLauncher.usersHandler);

        //client custom events
        server.addEventListener(EventNames.ChatEvent.name(), ChatMessage.class, messageHandler.chatHandler);
        server.addEventListener(EventNames.UpdateDisplayName.name(), UpdateNameMessage.class, messageHandler.updateNameHandler);
        server.addEventListener(EventNames.FetchUsers.name(), ActiveUsersList.class, messageHandler.fetchUsersHandler);

        DisconnectionHandler disconnectionHandler = new DisconnectionHandler(ChatLauncher.usersHandler);
        server.addDisconnectListener(disconnectionHandler);
    }
}
