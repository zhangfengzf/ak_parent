package com.speechrecognition.service;


import javax.websocket.Session;

public interface WebSocketService {

    public void onOpen(Session session,String id,String LanType);
    public void onMessage(Session session,String context);
    public void onClose(Session session);
    public void onError(Session session);
}
