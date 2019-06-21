package com.speechrecognition.service;
import com.alibaba.nls.client.protocol.asr.SpeechTranscriberListener;

public interface SpeechTranscriberListenerService {

     SpeechTranscriberListener getSpeechTranscriberListener(String isOpen4G,String userId,String lan,String soundCarName);

     void sendMessageToWordService(String isOpen4G,String language);

}
