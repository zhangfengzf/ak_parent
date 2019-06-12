package com.speechrecognition.service.impl;

import com.alibaba.nls.client.protocol.asr.SpeechTranscriberListener;
import com.alibaba.nls.client.protocol.asr.SpeechTranscriberResponse;
import com.speechrecognition.service.SpeechTranscriberListenerService;
import org.springframework.stereotype.Service;

/**
 *  语音识别实现类
 */
@Service
public class SpeechTranscriberListenerServiceImpl implements SpeechTranscriberListenerService {

    private SpeechTranscriberListener speechTranscriberListener;

    @Override
    public SpeechTranscriberListener getSpeechTranscriberListener() {
        speechTranscriberListener = new SpeechTranscriberListener() {
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


            }

            /**
             *   识别出中间结果.服务端识别出一个字或词时会返回此消息.仅当setEnableIntermediateResult(true)时,才会有此类消息返回
             */
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

