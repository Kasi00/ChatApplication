package com.example.springbootdemoiu.chat.security;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String[] messageParts = message.getPayload().split(":");
        String recipientUsername= messageParts[0];
        String messageContent =messageParts[1];

        WebSocketSession recipientSession = sessions.get(recipientUsername);
        if (recipientSession != null) {
            recipientSession.sendMessage(new TextMessage(messageContent));

        }else{
            session.sendMessage(new TextMessage("user Not Connected"));
        }
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String username =(String) session.getAttributes().get("username");

        if(username != null){
            sessions.put(username, session);
            System.out.println("user connected "+username+"Connected with Session Id: "+session.getId());

        }else {
            session.close();
            System.out.println("No username has been provided");
        }
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String username=(String) session.getAttributes().get("username");

        if(username != null){
            sessions.remove(username);
            System.out.println("user Disconnected "+username);
        }
    }


}
