package com.speechrecognition.service.impl;

import com.speechrecognition.service.WebSocketService;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("")
@Component
public class WebSocketServiceImpl implements WebSocketService {
   // private static Map<String,Integer> userCount = new ConcurrentHashMap<String, Integer>();
    // 外层Map的key存放会议id，内层map的key存放用户，value存放用户收听的语种
    private static Map<String, Map<Session,String>> allUserSessionMap = new ConcurrentHashMap<String, Map<Session,String>>();

    @OnOpen
    @Override
    public void onOpen(Session session, String userId, String soundCarName) {
        // 获取是否有当前会议的信息
        Map<Session,String> sessionMap = allUserSessionMap.get(userId);
        if(null ==sessionMap){
            sessionMap = new ConcurrentHashMap<Session, String>();
        }
        sessionMap.put(session,userId);

    }


    @OnMessage
    @Override
    public void onMessage(Session session, String context) {

    }

    @OnClose
    @Override
    public void onClose(Session session) {

    }


    @OnError
    @Override
    public void onError(Session session) {

    }
}
