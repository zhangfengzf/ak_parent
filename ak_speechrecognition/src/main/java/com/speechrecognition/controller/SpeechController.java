package com.speechrecognition.controller;

import com.alibaba.nls.client.protocol.NlsClient;
import com.speechrecognition.datamodel.request.Common;
import com.speechrecognition.datamodel.request.OpenSpeechRecognitionParam;
import com.speechrecognition.datamodel.request.RequestModle;
import com.speechrecognition.task.RecognitionTask;
import com.speechrecognition.task.SpeechClient;
import com.speechrecognition.task.WebSocketJSClient;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    private WebSocketJSClient webSocketJSClientl;
    private static NlsClient client;
    private static final Logger logger = LoggerFactory.getLogger(SpeechController.class);
    /**
     * 首次开启语音识别配置,  文字保存在本地
     */
    @GetMapping("config_speechRecognition" )
    public void  configSpeechRecognition(){

    }
    /**
     * 开始语音识别
     * @return
     */
    @PostMapping("open_speechRecognition" )
    public boolean openSpeechRecognition(@RequestBody OpenSpeechRecognitionParam openSpeechRecognitionParam){
        logger.info("开始语音识别，生成token..........");
        client= speechClient.createClient();
        // 开启定时任务：重新生成阿里语音识别token
        Common.setScheduleTaskstate(true);
        // 语音识别开启状态
        Common.setIsOpenSpeech(true);

        for(RequestModle requestModle:openSpeechRecognitionParam.getRequestList()){

            recognitionTask.openSpeechTask(client,openSpeechRecognitionParam.getIsOpen4G(),openSpeechRecognitionParam.getUserId(),requestModle.getLanguageType(),requestModle.getSoundCarName());
           /* if(WebSockJsCount == 0)
            try{
                webSocketJSClientl.SocketConnect(openSpeechRecognitionParam.getUserId());
                WebSockJsCount = 1;
            }catch (Exception e){
                e.printStackTrace();
            }*/

        }
        log.info("语音识别开启成功");
        return true;
    }

    /**
     * 关闭语音识别
     * @return
     */

    @PostMapping("close_speechRecognition")
    public String closeSpeechRecognition() throws  Exception{

        Common.setIsOpenSpeech(false);
        Thread.sleep(1000);
        if( null != client ){
            logger.info("关闭语音识别........");
            speechClient.closeClient();

        }
        // WebSockJsCount = 0;
        return "语音识别关闭成功";
    }

    /**
     *  语音切换状态，true------>正常状态 （声卡类型对应语音类型）
     *        false -------> 交换状态
     * @param
     * @return
     */
    @PostMapping ("change_speechRecognition")
    public String changeSoundCarAndLanguageStatus(@RequestBody OpenSpeechRecognitionParam openSpeechRecognitionParam){
        String type = openSpeechRecognitionParam.getType();
        //语音切换 中文声卡----英文语音 change    中文声卡---中文语音  normal
        Common.setSoundCarAndLanguageType(type);
        // 跳出循环，重启语音识别
        Common.setSoundCarAndLanguageState(false);
        return  "语音交换成功";
    }

}
