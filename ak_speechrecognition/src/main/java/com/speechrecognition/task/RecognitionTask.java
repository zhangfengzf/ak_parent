package com.speechrecognition.task;

import com.alibaba.nls.client.protocol.NlsClient;
import com.alibaba.nls.client.protocol.asr.SpeechTranscriber;
import com.speechrecognition.service.SpeechTranscriberListenerService;
import com.speechrecognition.util.AudioSystemTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.sound.sampled.TargetDataLine;

@Component
public class RecognitionTask {
    @Autowired
    private SpeechTranscriberListenerService speechTranscriberListenerService;
    @Autowired
    private SpeechClient speechClient;
    private SpeechTranscriber speechTranscriber;
    private TargetDataLine targetDataLine;
    @Autowired
    private AudioSystemTask audioSystemTask;


    @Async(value = "AsyncTaskExecutor")
    public void openSpeechTask(NlsClient client,String isOpen4G,String userId,String lan){
        try {
            targetDataLine = audioSystemTask.getSystemAudioMix(lan);
            // targetDataLine = new TargetDataLine();
            speechTranscriber = new SpeechTranscriber(client,speechTranscriberListenerService.getSpeechTranscriberListener());
            speechTranscriber.setAppKey(speechClient.getKey());
            //是否返回中间识别结果
            speechTranscriber.setEnableIntermediateResult(false);
            //是否生成并返回标点符号
            speechTranscriber.setEnablePunctuation(true);
            //是否将返回结果规整化,比如将一百返回为100
            speechTranscriber.setEnableITN(false);
        }catch (Exception e){

        }


    }



    }


