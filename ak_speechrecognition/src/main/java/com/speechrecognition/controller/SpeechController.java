package com.speechrecognition.controller;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nls.client.protocol.NlsClient;
import com.speechrecognition.task.SpeechClient;
import com.speechrecognition.task.RecognitionTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("yyq/speech")
public class SpeechController {
    @Autowired
    private RecognitionTask recognitionTask;
    @Autowired
    private SpeechClient speechClient;
    private static NlsClient client;

    /**
     * 首次开启语音识别配置
     */
    @RequestMapping("config_speechrecognition" )
    public void  configSpeechRecognition(){

    }
    /**
     * 开始语音识别
     * @return
     */
    @RequestMapping("open_speechrecognition" )
    public String openSpeechRecognition(JSONObject json){
        client= speechClient.createClient();
        recognitionTask.openSpeechTask(client);
        return  "语音识别开启成功";
    }

    /**
     * 关闭语音识别
     * @return
     */

    @RequestMapping("close_speechrecognition")
    public String closeSpeechRecognition(){
        if( null !=client ){
            speechClient.closeClient();
        }
        return "语音识别关闭成功";
    }

}
