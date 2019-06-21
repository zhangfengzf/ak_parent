package com.yyq.wordtrans.controller;

import com.yyq.wordtrans.task.WriteFileTask;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("speech/reciverword")
public class WordController {
    //SimpMessagingTemplate：SpringBoot提供操作WebSocket的对象
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private WriteFileTask writeFileTask;

    /**
     * 接收英文文字
     *
     * @param message
     * @return
     */
    @RequestMapping(value = "/english", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String reciveEnglishWord(@RequestBody String message) {
        // 获取当前会议的id
        return "ok";
    }

    /**
     * 接收中文文字
     *
     * @param message
     * @return
     */
    @RequestMapping(value = "/chinese", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String reciveChineseWord(@RequestBody String message) {
        sendJsonMessageToUser(message);
        return "ok";
    }

    public void sendJsonMessageToUser(String message) {
        try {

            JSONObject jsonObject = new JSONObject(message);
            String userId = jsonObject.getString("userId");
            String soundCarName = jsonObject.getString("soundCarName");
            String sendMessage = jsonObject.getString("JsonString");
            String text = jsonObject.getString("text");
            simpMessagingTemplate.convertAndSendToUser(userId, "/websocket/" + soundCarName, sendMessage);
            // 将文字保存到word文档中
            if (sendMessage.contains("SentenceEnd")) {
                writeFileTask.wirteWordTask(userId, soundCarName, text);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
