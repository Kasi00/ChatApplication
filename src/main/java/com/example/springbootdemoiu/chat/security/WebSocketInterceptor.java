package com.example.springbootdemoiu.chat.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.net.URI;
import java.util.Map;

@Component
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        URI uri = request.getURI();
        String query = uri.getQuery();

        if (query != null) {
            String[] params = query.split("&"); // Handle multiple query parameters
            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2) {
                    if (keyValue[0].equals("username")) {
                        attributes.put("username", keyValue[1]); // Store username in session attributes
                    }
                }
            }
        }

        if (!attributes.containsKey("username")) {
            System.out.println("No username has been provided");
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception ex) {
        System.out.println("WebSocket Handshake completed.");
    }



}
