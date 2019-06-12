package com.speechrecognition.task;

import com.alibaba.nls.client.protocol.NlsClient;
import com.alibaba.nls.client.protocol.asr.SpeechTranscriber;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RecognitionTask {

    @Async(value = "AsyncTaskExecutor")
    public void openSpeechTask(NlsClient client){
        //SpeechTranscriber speechTranscriber = new SpeechTranscriber(client,);

    }

}
