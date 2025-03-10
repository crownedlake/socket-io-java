package com.corundumstudio.socketio.demo;

import com.corundumstudio.socketio.AckCallback;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.VoidAckCallback;
import com.corundumstudio.socketio.demo.messages.ChatMessage;
import com.corundumstudio.socketio.listener.DataListener;

public class AckChatLauncher {

    public static void main(String[] args) throws InterruptedException {

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        final SocketIOServer server = new SocketIOServer(config);
//        server.addEventListener("ackevent1", ChatMessage.class, new DataListener<ChatMessage>() {
//            @Override
//            public void onData(final SocketIOClient client, ChatMessage data, final AckRequest ackRequest) {
//
//                // check is ack requested by client,
//                // but it's not required check
//                if (ackRequest.isAckRequested()) {
//                    // send ack response with data to client
//                    ackRequest.sendAckData("client message was delivered to server!", "yeah!");
//                }
//
//                // send message back to client with ack callback WITH data
//                ChatMessage ackChatMessageData = new ChatMessage(data.getUserName(), "message with ack data");
//                client.sendEvent("ackevent2", new AckCallback<String>(String.class) {
//                    @Override
//                    public void onSuccess(String result) {
//                        System.out.println("ack from client: " + client.getSessionId() + " data: " + result);
//                    }
//                }, ackChatMessageData);
//
//                ChatMessage ackChatMessageData1 = new ChatMessage(data.getUserName(), "message with void ack");
//                client.sendEvent("ackevent3", new VoidAckCallback() {
//                    protected void onSuccess() {
//                        System.out.println("void ack from: " + client.getSessionId());
//                    }
//                }, ackChatMessageData1);
//            }
//        });

        server.start();

        Thread.sleep(Integer.MAX_VALUE);

        server.stop();
    }

}
