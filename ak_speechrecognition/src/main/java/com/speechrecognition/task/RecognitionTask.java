package com.speechrecognition.task;

import com.alibaba.nls.client.protocol.NlsClient;
import com.alibaba.nls.client.protocol.asr.SpeechTranscriber;
import com.speechrecognition.service.impl.SpeechTranscriberListenerServiceImpl;
import com.speechrecognition.util.AudioSystemTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.sound.sampled.*;

@Component
public class RecognitionTask {
    @Autowired
    private SpeechTranscriberListenerServiceImpl speechTranscriberListenerService;
    @Autowired
    private SpeechClient speechClient;
    private SpeechTranscriber speechTranscriber;
    @Autowired
    private AudioSystemTask audioSystemTask;


    @Async(value = "AsyncTaskExecutor")
    public void openSpeechTask(NlsClient client, String isOpen4G, String userId, String lan, String soundCarName) {
        boolean flag = true;
        TargetDataLine targetDataLine = null;
        byte voiceByte[] = new byte[1024];
        try {
            AudioFormat audioFormat = getAudioFormat();
            Mixer mixer = getSysSoundName(soundCarName);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
            targetDataLine = (TargetDataLine) mixer.getLine(info);
            targetDataLine.start();
            targetDataLine.open();
            speechTranscriber = new SpeechTranscriber(client, speechTranscriberListenerService.getSpeechTranscriberListener(isOpen4G, userId, soundCarName, lan));
            speechTranscriberListenerService.sendMessageToWordService(isOpen4G, lan,userId);
            speechTranscriber.setAppKey(speechClient.getKey());
            //是否返回中间识别结果
            speechTranscriber.setEnableIntermediateResult(false);
            //是否生成并返回标点符号
            speechTranscriber.setEnablePunctuation(true);
            //是否将返回结果规整化,比如将一百返回为100
            speechTranscriber.setEnableITN(false);
            speechTranscriber.start();
            do {
                targetDataLine.read(voiceByte, 0, 1024);
                try {
                    speechTranscriber.send(voiceByte);
                } catch (Exception e) {
                    flag = false;
                    targetDataLine.close();
                    speechTranscriber.stop();
                }
            } while (flag);
            openSpeechTask(client, isOpen4G, userId, lan, soundCarName);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            targetDataLine.close();

        } catch ( Exception e) {
            e.printStackTrace();
            targetDataLine.close();
        }


    }
    // 获取音频采样参数
    public  AudioFormat getAudioFormat() {
        AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
        float rate = 24000f;
        int sampleSize = 16;
        boolean bigEndian = false;
        int channels = 1;
        return new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate, bigEndian);
    }
   // 获取系统声卡信息
    public   Mixer getSysSoundName(String soundCarName) {
        Mixer mixer = null;
        Mixer.Info mixerInfoArray[] = AudioSystem.getMixerInfo();
        for (Mixer.Info mixerInfo : mixerInfoArray) {
            if (!mixerInfo.getName().contains("Port")) {
                String mixerName[] = mixerInfo.getName().split("\\s+");
                if (soundCarName.equals(mixerName[0])) {
                    mixer = AudioSystem.getMixer(mixerInfo);
                }
            }
        }
        return mixer;
    }
}




