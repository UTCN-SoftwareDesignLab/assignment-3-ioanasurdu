package com.example.assignment3.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Message {
    private String patient;
    private String user;
    private String date;
    private String time;
    private String type;
}
