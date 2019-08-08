package com.speechrecognition.task;

import com.alibaba.nls.client.AccessToken;
import com.alibaba.nls.client.protocol.NlsClient;
import com.speechrecognition.datamodel.request.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@ConfigurationProperties(prefix = "alibaba")
@EnableScheduling
public class SpeechClient {
    private  String token;
    private String key;
    private String secret;
    private String appKeyEn;
    private String appKeyCn;
    private static final Logger logger = LoggerFactory.getLogger(SpeechClient.class);

    public String getAppKeyEn() {
        return appKeyEn;
    }

    public void setAppKeyEn(String appKeyEn) {
        this.appKeyEn = appKeyEn;
    }

    public String getAppKeyCn() {
        return appKeyCn;
    }

    public void setAppKeyCn(String appKeyCn) {
        this.appKeyCn = appKeyCn;
    }

    private static NlsClient client;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     *  根据生成的token，连接阿里语音识别客户端
     *
     * @return
     */
    public NlsClient createClient(){
        token = createToken(key,secret);
        client = new NlsClient(token);
        return client;
    }
    /**
     *  定时任务，修改定时任务状态
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduledState(){
        Common.setScheduleTaskstate(false);
    }
    /**
     *  根据 key以及secret生产token
     * @return
     */
    public String createToken(String key,String secret){
        AccessToken accessToken = new AccessToken(key,secret);
        try {
            logger.info("正在生成token.......");
            accessToken.apply();
        }catch (IOException e){
            e.printStackTrace();
        }
        return  accessToken.getToken();
    }

    /**
     * 关闭阿里语音识别客户端连接
     */
    public void closeClient(){
        client.shutdown();
    }

}
