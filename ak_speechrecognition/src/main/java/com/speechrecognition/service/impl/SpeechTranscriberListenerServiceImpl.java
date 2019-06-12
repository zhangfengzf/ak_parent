package com.speechrecognition.service.impl;

import com.alibaba.nls.client.protocol.asr.SpeechTranscriberListener;
import com.alibaba.nls.client.protocol.asr.SpeechTranscriberResponse;
import com.speechrecognition.service.SpeechTranscriberListenerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  语音识别实现类
 */
public class SpeechTranscriberServiceImpl implements SpeechTranscriberListenerService {
    @Autowired
    private SpeechTranscriberListener speechTranscriberListener;

    @Override
    public SpeechTranscriberListener getSpeechTranscriberListener() {
        speechTranscriberListener = new SpeechTranscriberListener() {

            @Override
            public void onTranscriberStart(SpeechTranscriberResponse speechTranscriberResponse) {

            }

            @Override
            public void onSentenceBegin(SpeechTranscriberResponse speechTranscriberResponse) {

            }

            @Override
            public void onSentenceEnd(SpeechTranscriberResponse speechTranscriberResponse) {

            }

            @Override
            public void onTranscriptionResultChange(SpeechTranscriberResponse speechTranscriberResponse) {

            }

            @Override
            public void onTranscriptionComplete(SpeechTranscriberResponse speechTranscriberResponse) {

            }

            @Override
            public void onFail(SpeechTranscriberResponse speechTranscriberResponse) {

            }

        };
        return speechTranscriberListener;
    }
}

