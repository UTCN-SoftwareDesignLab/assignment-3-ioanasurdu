package com.example.assignment3.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

    @MessageMapping("/consultation")
    @SendTo("/message/consultations")
    public Websocket websocket(Message message) throws Exception {
        return new Websocket(message.getType() + " consultation for " + message.getPatient() + " with " + message.getUser() + " at " + message.getDate() + ", " + message.getTime());

    }
}
