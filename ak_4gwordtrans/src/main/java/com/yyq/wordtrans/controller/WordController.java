package com.yyq.wordtrans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("speech/reciverword")
public class WordController {
    //SimpMessagingTemplate：SpringBoot提供操作WebSocket的对象
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @RequestMapping("/english")
    public String reciveEnglishWord(String message){
        // 获取当前会议的id
        String userId ="";
        simpMessagingTemplate.convertAndSendToUser(userId,"/english/getResponse","");
        return "ok";
    }
    @RequestMapping("/chinese")
    public String reciveChineseWord(String message){
        String userId ="";
        simpMessagingTemplate.convertAndSendToUser(userId,"/chinese/getResponse","");
        return "ok";
    }
}
