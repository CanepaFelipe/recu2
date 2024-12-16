package com.example.demo.service;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class ChatHandler extends TextWebSocketHandler {

    
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();
 
    private final ArrayList<TextMessage> messages = new ArrayList<TextMessage>();

   
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        
        sessions.add(session);
      
        session.sendMessage(new TextMessage("¡Bienvenido"));;
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
       
        sessions.remove(session);
    }

  
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
      
        if (message.getPayload().toString().contains("salir")) {
            session.sendMessage(new TextMessage("chau"));
            
        } else {
            
            messages.add(message);
            
            for (WebSocketSession webSocketSession : sessions) {
                webSocketSession.sendMessage(message);
            }
        }
    }
}




