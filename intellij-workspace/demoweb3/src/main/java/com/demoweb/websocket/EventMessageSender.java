package com.demoweb.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EventMessageSender {
    private final DemoWebSocketHandler socketHandler;

    @Autowired
    public EventMessageSender(DemoWebSocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

//    @Scheduled(fixedRate = 5000)
    public void sendPeriodicMessages() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String message = "WebSocket event - " + sdf.format(new Date());
        // String message = "WebSocket event - " + Math.ceil(Math.random() * 100);
        try {
            socketHandler.sendMessageToAll(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
