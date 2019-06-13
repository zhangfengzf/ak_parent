package com.speechrecognition.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nls.client.protocol.asr.SpeechTranscriberListener;
import com.alibaba.nls.client.protocol.asr.SpeechTranscriberResponse;
import com.speechrecognition.datamodel.request.Common;
import com.speechrecognition.service.SpeechTranscriberListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *  语音识别实现类
 */
@Service
public class SpeechTranscriberListenerServiceImpl implements SpeechTranscriberListenerService {

    private SpeechTranscriberListener speechTranscriberListener;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    // 存放英文文字的队列
    private static BlockingQueue<JSONObject> englishQueue  = new ArrayBlockingQueue(20);
    // 存放中文文字的队列
    private static BlockingQueue<JSONObject>  chineseQueue  = new ArrayBlockingQueue(20);

    @Override
    public SpeechTranscriberListener getSpeechTranscriberListener(String isOpen4G,String userId,String language,String soundCarName) {

       return speechTranscriberListener = new SpeechTranscriberListener() {
            /**
             *  语音识别
             * @param speechTranscriberResponse
             */
            @Override
            public void onTranscriberStart(SpeechTranscriberResponse speechTranscriberResponse) {

            }

            @Override
            public void onSentenceBegin(SpeechTranscriberResponse speechTranscriberResponse) {
            }

            /**
             * //识别出一句话.服务端会智能断句,当识别到一句话结束时会返回此消息
             * @param speechTranscriberResponse
             */
            @Override
            public void onSentenceEnd(SpeechTranscriberResponse speechTranscriberResponse) {
                onSendWordToUser(speechTranscriberResponse,language,userId);
            }

            /**
             *   识别出中间结果.服务端识别出一个字或词时会返回此消息.仅当setEnableIntermediateResult(true)时,才会有此类消息返回
             */
            @Override
            public void onTranscriptionResultChange(SpeechTranscriberResponse speechTranscriberResponse) {
                onSendWordToUser(speechTranscriberResponse,language,userId);

            }

            @Override
            public void onTranscriptionComplete(SpeechTranscriberResponse speechTranscriberResponse) {

            }

            @Override
            public void onFail(SpeechTranscriberResponse speechTranscriberResponse) {

            }

        };

    }
    
      @Async(value = "AsyncTaskExecutor")
      public void sendMessageToWordService(String isOpen4G,String language,String userId){
        if("open".equals(isOpen4G)){
            while(!Common.getIsCloseSpeech()){
                try {
                    if("chinese".equals(language)){
                        JSONObject json = chineseQueue.take();
                    }else{
                        JSONObject json =englishQueue.take();
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }


        }
      }

       public void  onSendWordToUser(SpeechTranscriberResponse speechTranscriberResponse,String language,String userId){
         String message =JSON.toJSONString(speechTranscriberResponse);
         // 将文字发送给局域网的用户
         simpMessagingTemplate.convertAndSendToUser(userId,"/"+language+"/getResponse",message );
         // 将文字发送至服务器，用于发送通过4G网络访问的用户
         putMessageQueue(message,language,userId);
      }

       public void putMessageQueue(String message,String language,String userId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",userId);
        jsonObject.put("JsonString",message);
          if("english".equals(language)){
              jsonObject.put("soundCarName","en");
             if(!englishQueue.offer(jsonObject)){
                 englishQueue.clear();
             }
          }else{
              jsonObject.put("soundCarName","cn");
             if(!chineseQueue.offer(jsonObject)){
               chineseQueue.clear();
           }

       }
   }
}

