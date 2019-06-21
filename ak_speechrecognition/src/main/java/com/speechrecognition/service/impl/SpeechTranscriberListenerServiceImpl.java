package com.speechrecognition.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nls.client.protocol.asr.SpeechTranscriberListener;
import com.alibaba.nls.client.protocol.asr.SpeechTranscriberResponse;
import com.speechrecognition.datamodel.request.Common;
import com.speechrecognition.datamodel.request.RequestAddress;
import com.speechrecognition.service.SpeechTranscriberListenerService;
import com.speechrecognition.util.HttpUrlConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

   private SpeechTranscriberListener speechTranscriberListener=null;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private HttpUrlConnectionUtil httpUrlConnection;
    @Autowired
    private RequestAddress address;
    // 存放英文文字的队列
    private static BlockingQueue<JSONObject> englishQueue  = new ArrayBlockingQueue(20);
    // 存放中文文字的队列
    private static BlockingQueue<JSONObject>  chineseQueue  = new ArrayBlockingQueue(20);
   // @Autowired
  //  private HttpClientUtil httpClientUtil;
    private static final Logger logger = LoggerFactory.getLogger(SpeechTranscriberListenerServiceImpl.class);

   @Override
    public SpeechTranscriberListener getSpeechTranscriberListener(String isOpen4G,String userId,String language,String soundCarName) {

          speechTranscriberListener = new SpeechTranscriberListener() {
           int count =0;

           /**
             *  语音识别
             * @param speechTranscriberResponse
             */
            @Override
            public void onTranscriberStart(SpeechTranscriberResponse speechTranscriberResponse) {

            }

            @Override
            public void onSentenceBegin(SpeechTranscriberResponse speechTranscriberResponse) {
                logger.info("语音识别：start.........");
            }

            /**
             * //识别出一句话.服务端会智能断句,当识别到一句话结束时会返回此消息
             * @param speechTranscriberResponse
             */
            @Override
            public void onSentenceEnd(SpeechTranscriberResponse speechTranscriberResponse) {

                String text = speechTranscriberResponse.getTransSentenceText();
                // 每9句话进行换行
                count++;
                if (count ==9){
                    text  +=  System.getProperty("line.separator")+System.getProperty("line.separator");
                    count =0;
                }
                onSendWordToUser(speechTranscriberResponse,language,userId,soundCarName,text,isOpen4G);
            }

            /**
             *   识别出中间结果.服务端识别出一个字或词时会返回此消息.仅当setEnableIntermediateResult(true)时,才会有此类消息返回
             */
            @Override
            public void onTranscriptionResultChange(SpeechTranscriberResponse speechTranscriberResponse) {
                String text = speechTranscriberResponse.getTransSentenceText();
                onSendWordToUser(speechTranscriberResponse,language,userId,soundCarName,text,isOpen4G);
            }

            @Override
            public void onTranscriptionComplete(SpeechTranscriberResponse speechTranscriberResponse) {
                logger.info("语音识别：end.........");
            }

            @Override
            public void onFail(SpeechTranscriberResponse speechTranscriberResponse) {
                logger.warn("语音识别：失败.....");
            }
        };
       return speechTranscriberListener;
    }
      @Async(value = "asyncTaskExecutor")
      public void sendMessageToWordService(String isOpen4G,String language){
        if("open".equals(isOpen4G)){
            logger.info("当前线程:   "+Thread.currentThread().getName()+"  4G开启，文字传输开始..........");
            // 切换语音或者关闭语音识别时，需要结束当前线程，跳出循环
            while(Common.getIsOpenSpeech()&&Common.getSoundCarAndLanguageState()){
                try {
                    if("CN".equals(language)){
                        if(!chineseQueue.isEmpty()){
                            JSONObject json = chineseQueue.take();
                            httpUrlConnection.httpPost(address.getServerWordAddress(),JSONObject.toJSONString(json));
                        }
                    }else{
                        if (!englishQueue.isEmpty()){
                            JSONObject json =englishQueue.take();
                            httpUrlConnection.httpPost(address.getServerWordAddress(),JSONObject.toJSONString(json));
                        }
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
      }

       public void  onSendWordToUser(SpeechTranscriberResponse speechTranscriberResponse,String language,String userId,String soundCarName,String text,String isOpen4G){
         String message =JSON.toJSONString(speechTranscriberResponse);
        // String text = speechTranscriberResponse.getTransSentenceText();
         // 将文字发送给局域网的用户
         simpMessagingTemplate.convertAndSendToUser(userId,"/serverwebsoket"+soundCarName,message );
         // 将文字发送至服务器，用于发送通过4G网络访问的用户
         if("open".equals(isOpen4G)){
             putMessageQueue(message,language,userId,text);
         }
      }

       public void putMessageQueue(String message,String language,String userId,String text){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",userId);
        jsonObject.put("JsonString",message);
        jsonObject.put("text",text);
          if("CN".equals(language)){
              jsonObject.put("soundCarName","cn");
             if(!chineseQueue.offer(jsonObject)){
                 chineseQueue.clear();
             }
          }else{
              jsonObject.put("soundCarName","en");
             if(!englishQueue.offer(jsonObject)){
                 englishQueue.clear();
           }

       }
   }
}

