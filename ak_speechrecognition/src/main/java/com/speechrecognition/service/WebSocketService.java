package com.speechrecognition.service;


import javax.websocket.Session;

public interface WebSocketService {

    void onOpen(Session session,String id,String LanType);
    void onMessage(Session session,String context);
    void onClose(Session session);
    void onError(Session session);
}
