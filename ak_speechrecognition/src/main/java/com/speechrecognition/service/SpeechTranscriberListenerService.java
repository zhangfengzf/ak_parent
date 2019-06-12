package com.speechrecognition.service;


import com.alibaba.nls.client.protocol.asr.SpeechTranscriberListener;

public interface SpeechTranscriberService {

    public SpeechTranscriberListener getSpeechTranscriberListener();

}
