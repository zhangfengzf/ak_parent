package com.initaitor.service;

/**
 * @ClassName: SpeechTransmission
 * @Description: 语音传输
 * @Author 张锋
 * @Date 2019/7/31 10:27
 * @Version 1.0
 */
public interface SpeechTransmission {

    /*开启4G语音传输*/
    void start4GSpeechTransmission(String url, String meetingId, String transportType, String soundName) throws Exception;

    /*关闭4G语音传输*/
    void stop4GSpeechTransmission(String url, String transportType) throws Exception;

    /*开启局域网语音传输*/
    void startLocalSpeechTransmission(String url, String meetingId, String transportType) throws Exception;

    /*关闭局域网语音传输*/
    void stopLocalSpeechTransmission(String url, String transportType) throws Exception;

}
