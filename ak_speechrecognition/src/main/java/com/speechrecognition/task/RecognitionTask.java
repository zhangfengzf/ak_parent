package com.speechrecognition.task;

import com.alibaba.nls.client.protocol.NlsClient;
import com.alibaba.nls.client.protocol.asr.SpeechTranscriber;
import com.speechrecognition.datamodel.request.Common;
import com.speechrecognition.service.SpeechTranscriberListenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.sound.sampled.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class RecognitionTask {
    @Autowired
    private SpeechTranscriberListenerService speechTranscriberListenerService;
    @Autowired
    private SpeechClient speechClient;
    private SpeechTranscriber speechTranscriber;
    private static final Logger logger = LoggerFactory.getLogger(RecognitionTask.class);
    private volatile Map<Thread,Boolean>  map = new HashMap<>();

    /**
     *
     * @param client     阿里语音识别client
     * @param isOpen4G  是否进行开启4G传输到服务器  “open” 开启
     * @param userId    本场会议的id
     * @param language  所说语种
     * @param soundCarName   声卡名称
     */
    @Async(value = "asyncTaskExecutor")
    public void openSpeechTask(NlsClient client, String isOpen4G, String userId, String language, String soundCarName) {
        // 判断调用阿里语音识别状态
        boolean flag = true;
        //map.put(Thread.currentThread(),true);
        TargetDataLine targetDataLine = null;
        byte voiceByte[] = new byte[6400];
        try {
            AudioFormat audioFormat = getAudioFormat();
            Mixer mixer = getSysSoundName(soundCarName);
            // 读取声卡音频流
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
            targetDataLine = (TargetDataLine) mixer.getLine(info);
            targetDataLine.open();
            targetDataLine.start();
            speechTranscriber = new SpeechTranscriber(client, speechTranscriberListenerService.getSpeechTranscriberListener(isOpen4G, userId, language, soundCarName));
            speechTranscriberListenerService.sendMessageToWordService(isOpen4G, language);
            //根据语种类型 选择对应的AppKey
            speechTranscriber.setAppKey("CN".equals(language) ? speechClient.getAppKeyCn() : speechClient.getAppKeyEn());
            //是否返回中间识别结果
            speechTranscriber.setEnableIntermediateResult(true);
            //是否生成并返回标点符号
            speechTranscriber.setEnablePunctuation(true);
            //是否将返回结果规整化,比如将一百返回为100
            speechTranscriber.setEnableITN(false);
            speechTranscriber.start();
            logger.info("当前语种 ：" + language + "......，" + "当前声卡：" + soundCarName);
            do {
                targetDataLine.read(voiceByte, 0, 6400);
                try {
                    speechTranscriber.send(voiceByte);
                } catch (Exception e) {
                    logger.info("长时间没有发送数据到阿里服务端.......");
                    flag = false;
                }
            } while (flag && Common.getSoundCarAndLanguageState() && Common.getIsOpenSpeech() && Common.getScheduleTaskstate());
            //map.put(Thread.currentThread(),false);
            speechTranscriber.stop();
            targetDataLine.close();
            // Common.getIsOpenSpeech() : true  并不是停止了语音识别，因为某些原因跳出循环
            if (Common.getIsOpenSpeech()) {
                // 1.判断跳出循环是否因为定时任务需要生成token，token有效期为1天
                if (!Common.getScheduleTaskstate()) {
                    logger.info("token过期，定时任务重新生成token，重启语音识别........");
                    client = speechClient.createClient();
                  /*  //检查所有为false 继续
                    synchronized (this){
                        for(Boolean b:map.values()){
                            if(b.equals(true)){
                                this.wait();
                                break;
                            }
                        }
                        this.notifyAll();
                    }*/
                    Thread.sleep(500); // 休眠0.5s，防止另一线程未跳出循环。
                    Common.setScheduleTaskstate(true);
                    Common.setSoundCarAndLanguageState(true);

                }
                // 2.判断跳出循环是否因为切换语音
                if ("normal".equals(Common.getSoundCarAndLanguageType())) {
                    logger.info("声卡与语音类型处于“normal”（正常状态），重启中.........");
                    openSpeechTask(client, isOpen4G, userId, language, soundCarName);
                } else {
                    logger.info("声卡与语音类型处于“change” (切换状态)， 重启中.........");
                    language = "CN".equals(language) ? "EN" : "CN";
                    openSpeechTask(client, isOpen4G, userId, language, soundCarName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            targetDataLine.close();
            logger.info("重启语音识别..............");
            openSpeechTask(client, isOpen4G, userId, language, soundCarName);
        }
    }


    // 获取音频采样参数
    public AudioFormat getAudioFormat() {
        AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
        float rate = 16000f;
        int sampleSize = 16;
        boolean bigEndian = false;
        int channels = 1;
        return new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate, bigEndian);
    }

    // 获取系统声卡信息
    public Mixer getSysSoundName(String soundCarName) {
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




