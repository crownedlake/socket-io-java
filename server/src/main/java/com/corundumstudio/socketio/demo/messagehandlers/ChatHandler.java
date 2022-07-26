package com.corundumstudio.socketio.demo.messagehandlers;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.demo.messages.ChatMessage;
import com.corundumstudio.socketio.demo.users.UsersHandler;
import com.corundumstudio.socketio.listener.DataListener;

public class ChatHandler implements DataListener<ChatMessage> {
    private final SocketIOServer server;
    private final UsersHandler usersHandler;

    protected ChatHandler(SocketIOServer server, UsersHandler usersHandler) {
        this.server = server;
        this.usersHandler = usersHandler;
    }

    @Override
    public void onData(SocketIOClient client, ChatMessage data, AckRequest ackRequest) throws Exception {
        String pvtUsrName = parseMessage(data.getMessage());
        boolean isPvtMsg = pvtUsrName != null;

        if (!isPvtMsg) {
            server.getBroadcastOperations().sendEvent(EventNames.ChatEvent.name(), data);
        } else {
            data.setMessage(pvtUsrName+data.getMessage());
            server.getClient(usersHandler.getIOClient(pvtUsrName).getUuid()).sendEvent(EventNames.PvtChatEvent.name(), data);
            client.sendEvent(EventNames.PvtChatEvent.name(), data);
        }
    }

    private String parseMessage(String message) {
        String[] split = message.split(">", 3);
        return split.length > 1 ? parseUserName(split[0]) : null;
    }

    private String parseUserName(String s) {
        if (s.charAt(0) == '<') {
            return s.substring(1);
        }
        return null;
    }
}
