package com.speechrecognition.controller;

import com.alibaba.nls.client.protocol.NlsClient;
import com.speechrecognition.datamodel.request.OpenSpeechRecognitionParam;
import com.speechrecognition.task.RecognitionTask;
import com.speechrecognition.task.SpeechClient;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("yyq/speech")
@Log
public class SpeechController {
    @Autowired
    private RecognitionTask recognitionTask;
    @Autowired
    private SpeechClient speechClient;
    private static NlsClient client;

    /**
     * 首次开启语音识别配置
     */
    @GetMapping("config_speechrecognition" )
    public void  configSpeechRecognition(){

    }
    /**
     * 开始语音识别
     * @return
     */
    @PostMapping("open_speechrecognition" )
    public boolean openSpeechRecognition(@RequestBody OpenSpeechRecognitionParam openSpeechRecognitionParam){

        client= speechClient.createClient();
        recognitionTask.openSpeechTask(client,openSpeechRecognitionParam.getIsOpen4G(),openSpeechRecognitionParam.getUserId(),openSpeechRecognitionParam.getRequestList());
        log.info("语音识别开启成功");
        return true;
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
