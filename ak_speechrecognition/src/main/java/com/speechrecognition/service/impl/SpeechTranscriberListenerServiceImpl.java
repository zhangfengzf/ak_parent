package com.speechrecognition.service.impl;

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

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 语音识别实现类
 */
@Service
public class SpeechTranscriberListenerServiceImpl implements SpeechTranscriberListenerService {

    private SpeechTranscriberListener speechTranscriberListener = null;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private HttpUrlConnectionUtil httpUrlConnection;
    @Autowired
    private RequestAddress address;
    // 存放英文文字的队列
    private static BlockingQueue<JSONObject> englishQueue = new ArrayBlockingQueue(20);
    // 存放中文文字的队列
    private static BlockingQueue<JSONObject> chineseQueue = new ArrayBlockingQueue(20);
    private static Map<Integer, Integer> cnMap = new ConcurrentHashMap<>();
    private static Map<Integer, Integer> enMap = new ConcurrentHashMap<>();
    // 统计中文同一段话返回的次数
    private static int cnNum;
    private static int cnCount;
    private static String charIndexCn = ""; // id + 字母形式返回
    private static int lastIndexCn = 0;   // 返回的id，逗号存在的位置
    private static int enNum;
    private static int enCount;
    private static String charIndexEn = "";
    private static int lastIndexEn = 0;


    // @Autowired
    //  private HttpClientUtil httpClientUtil;
    private static final Logger logger = LoggerFactory.getLogger(SpeechTranscriberListenerServiceImpl.class);

    @Override
    public SpeechTranscriberListener getSpeechTranscriberListener(String isOpen4G, String userId, String language, String soundCarName) {
        System.out.println("识别线程:"+Thread.currentThread().getName()+ "  "+language );


        speechTranscriberListener = new SpeechTranscriberListener() {
            int count = 0;
            int num = 0;

            @Override
            public void onTranscriberStart(SpeechTranscriberResponse speechTranscriberResponse) {

            }

            @Override
            public void onSentenceBegin(SpeechTranscriberResponse speechTranscriberResponse) {
                //logger.info("语音识别：start.........");
            }

            /**
             * 识别出一句话.服务端会智能断句,当识别到一句话结束时会返回此消息
             * @param speechTranscriberResponse
             */
            @Override
            public void onSentenceEnd(SpeechTranscriberResponse speechTranscriberResponse) {
                String transSentenceText = speechTranscriberResponse.getTransSentenceText();
                JSONObject jsonObject = nextLineText(language, speechTranscriberResponse);

                // 每返回9个不同id的句子进行换行，文字保存成word时，方便用户观看！
                count++;
                if (count == 9) {
                    transSentenceText += System.getProperty("line.separator") + System.getProperty("line.separator");
                    count = 0;
                }

                onSendWordToUser(jsonObject, language, userId, soundCarName, transSentenceText, isOpen4G);

            }

            /**
             *   识别出中间结果.服务端识别出一个字或词时会返回此消息.仅当setEnableIntermediateResult(true)时,才会有此类消息返回
             */
            @Override
            public void onTranscriptionResultChange(SpeechTranscriberResponse speechTranscriberResponse) {
                String text = speechTranscriberResponse.getTransSentenceText();
                JSONObject jsonObject = nextLineText(language, speechTranscriberResponse);

                onSendWordToUser(jsonObject, language, userId, soundCarName, text, isOpen4G);
            }

            @Override
            public void onTranscriptionComplete(SpeechTranscriberResponse speechTranscriberResponse) {
                // logger.info("语音识别：结束.........");
            }

            @Override
            public void onFail(SpeechTranscriberResponse speechTranscriberResponse) {

            }
        };
        return speechTranscriberListener;
    }

    public JSONObject nextLineText(String lan, SpeechTranscriberResponse speechTranscriberResponse) {

        JSONObject jsonObject = new JSONObject();

        Object name = speechTranscriberResponse.header.get("name");
        Integer transSentenceIndex = speechTranscriberResponse.getTransSentenceIndex();
        String transSentenceText = speechTranscriberResponse.getTransSentenceText();
        int length = transSentenceText.length();

        if ("CN".equals(lan)) {
            int i = transSentenceText.lastIndexOf("，");
            if ("SentenceEnd".equals(name)) {
                // SentenceEnd 事件表示服务端检测到了一句话的结束，并附带返回该句话的识别结果
                if(length <= lastIndexCn){
                    lastIndexCn = 0;
                }
                transSentenceText = transSentenceText.substring(lastIndexCn, length);
                cnMap.remove(transSentenceIndex);
            } else if (cnMap.get(transSentenceIndex) == null) {
                cnNum = 0;
                cnCount = 0;
                lastIndexCn = 0;
                charIndexCn = "";
                transSentenceText = transSentenceText.substring(lastIndexCn, length);
                cnMap.put(transSentenceIndex, lastIndexCn);
            } else {
                cnNum++;
                if (cnNum < 30) {
                    System.out.println(lastIndexCn+"  "+ length);
                    transSentenceText = transSentenceText.substring(lastIndexCn, length);
                    //cnMap.put(transSentenceIndex,lastIndex);
                } else {
                    if (length - i < 5) {
                        cnCount = cnCount + 1;
                        lastIndexCn = i + 1;
                        transSentenceText = transSentenceText.substring(lastIndexCn, length);
                        charIndexCn = String.valueOf((char) (97 + cnCount));
                        cnMap.put(transSentenceIndex, lastIndexCn);
                        cnNum = 0;
                    } else {
                        transSentenceText = transSentenceText.substring(lastIndexCn, length);
                    }
                }
            }
            jsonObject.put("id", transSentenceIndex + charIndexCn);
        }else {
            int i = transSentenceText.lastIndexOf(". ");
            if ("SentenceEnd".equals(name)) {
                if(length <= lastIndexEn){
                    lastIndexEn = 0;
                }
                // SentenceEnd 事件表示服务端检测到了一句话的结束，并附带返回该句话的识别结果
                transSentenceText = transSentenceText.substring(lastIndexEn, length);
                enMap.remove(transSentenceIndex);
            } else if (enMap.get(transSentenceIndex) == null) {
                enNum = 0;
                enCount = 0;
                lastIndexEn = 0;
                charIndexEn = "";
                transSentenceText = transSentenceText.substring(lastIndexEn, length);
                enMap.put(transSentenceIndex, lastIndexEn);
            } else {
                enNum++;
                if (enNum < 30) {
                    transSentenceText = transSentenceText.substring(lastIndexEn, length);
                    //cnMap.put(transSentenceIndex,lastIndex);
                } else {
                    if (length - i < 5) {
                        enCount = enCount + 1;
                        lastIndexEn = i + 1;
                        transSentenceText = transSentenceText.substring(lastIndexEn, length);
                        charIndexEn = String.valueOf((char) (97 + enCount));
                        enMap.put(transSentenceIndex, lastIndexEn);
                        cnNum = 0;
                    } else {
                        transSentenceText = transSentenceText.substring(lastIndexEn, length);
                    }
                }
            }
            jsonObject.put("id", transSentenceIndex + charIndexEn);
        }
        jsonObject.put("text", transSentenceText);
        jsonObject.put("state", name);
        System.out.println(lan+"----------"+jsonObject);
        return jsonObject;
    }

    @Async
    public void sendMessageToWordService(String isOpen4G, String language) {
        if ("true".equals(isOpen4G)) {
            logger.info("当前线程:   " + Thread.currentThread().getName() + "  4G开启，文字传输开始..........");
            // 切换语音或者关闭语音识别时，需要结束当前线程，跳出循环
            while (Common.getIsOpenSpeech() && Common.getSoundCarAndLanguageState()) {
                try {
                    if ("CN".equals(language)) {
                        if (!chineseQueue.isEmpty()) {
                            JSONObject json = chineseQueue.take();
                            httpUrlConnection.httpPost(address.getServerWordAddress() + "cnSound", JSONObject.toJSONString(json));
                        }
                    } else {
                        if (!englishQueue.isEmpty()) {
                            JSONObject json = englishQueue.take();
                            httpUrlConnection.httpPost(address.getServerWordAddress() + "enSound", JSONObject.toJSONString(json));
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onSendWordToUser(JSONObject jsonObject, String language, String userId, String soundCarName, String text, String isOpen4G) {
        String message = jsonObject.toString();
        // String text = speechTranscriberResponse.getTransSentenceText();
        // 将文字发送给局域网的用户
        simpMessagingTemplate.convertAndSendToUser(userId, "/serverwebsoket/" + soundCarName, message);
        // 将文字发送至服务器，用于发送通过4G网络访问的用户
        if ("true".equals(isOpen4G)) {
            putMessageQueue(message, language, userId, text);
        }
    }

    public void putMessageQueue(String message, String language, String userId, String text) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("jsonString", message);
        jsonObject.put("text", text);
        if ("CN".equals(language)) {
            jsonObject.put("soundCarName", "cn");
            if (!chineseQueue.offer(jsonObject)) {
                chineseQueue.clear();
            }
        } else {
            jsonObject.put("soundCarName", "en");
            if (!englishQueue.offer(jsonObject)) {
                englishQueue.clear();
            }

        }
    }
}

