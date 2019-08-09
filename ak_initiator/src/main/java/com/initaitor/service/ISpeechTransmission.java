package com.initaitor.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import static com.initaitor.util.RequestUtil.loadRequest;


/**
 * @ClassName: ISpeechTransmission
 * @Description: 语音传输逻辑层
 * @Author 张锋
 * @Date 2019/7/31 10:43
 * @Version 1.0
 */
@Service
@Log
public class ISpeechTransmission implements SpeechTransmission {

    @Override
    public void start4GSpeechTransmission(String url, String meetingId, String transportType, String soundName) throws Exception {
        JSONObject jo = new JSONObject();
        jo.put("soundName", soundName);
        jo.put("meetingID", meetingId);
        log.info("语音传输:" + jo);
        loadRequest(url, jo, transportType);
    }

    @Override
    public void stop4GSpeechTransmission(String url, String transportType) throws Exception {
        loadRequest(url, null ,transportType);
    }

    @Override
    public void startLocalSpeechTransmission(String url, String meetingId, String transportType) throws Exception {

    }

    @Override
    public void stopLocalSpeechTransmission(String url, String transportType) throws Exception {

    }
}
