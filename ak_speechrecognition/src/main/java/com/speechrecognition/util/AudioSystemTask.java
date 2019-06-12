package com.speechrecognition.util;


import org.springframework.stereotype.Component;

import javax.sound.sampled.*;
@Component
public class AudioSystemTask {

    private static TargetDataLine targetDataLine;
    private static AudioFormat audioFormat;
    private static DataLine.Info info;


    public TargetDataLine getSystemAudioMix(String soundCarName) {
        AudioFormat audioFormat = getAudioFormat();
        Mixer mixer = getSysSoundName(soundCarName);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
        try {
            targetDataLine = (TargetDataLine) mixer.getLine(info);
        }catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        return  targetDataLine;
    }

    public  TargetDataLine getTargetDataLine() throws LineUnavailableException {
        if (targetDataLine != null) {
            return targetDataLine;
        } else {
            // 1.获取音频流数据
            // af为AudioFormat也就是音频格式
            audioFormat = getAudioFormat();
            info = new DataLine.Info(TargetDataLine.class, audioFormat);
            // 这里的td实际上是
            targetDataLine = (TargetDataLine) (AudioSystem.getLine(info));
            // 打开具有指定格式的行，这样可使行获得所有所需的系统资源并变得可操作。
            targetDataLine.open(audioFormat);
            // 允许某一数据行执行数据 I/O
            targetDataLine.start();
            return targetDataLine;
        }

    }

    public  AudioFormat getAudioFormat() {
        AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
        float rate = 24000f;
        int sampleSize = 16;
        String signedString = "signed";
        boolean bigEndian = false;
        int channels = 1;
        return new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate, bigEndian);
    }

    // 获取系统声卡
    public  Mixer getSysSoundName(String soundCarName) {
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
