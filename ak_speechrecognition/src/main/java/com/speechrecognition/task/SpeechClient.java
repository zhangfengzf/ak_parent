package com.speechrecognition.task;

import com.alibaba.nls.client.AccessToken;
import com.alibaba.nls.client.protocol.NlsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpeechClient {
    @Value("")
    private  String token;
    @Value("")
    private String key;
    @Value("")
    private String secret;
    private String appKeyEn;
    private String appKeyCn;

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
     *  连接阿里语音识别客户端
     * @return
     */
    public NlsClient createClient(){
        token = createToken(key,secret);
        client = new NlsClient(token);
        return client;
    }

    /**
     *  根据 key以及secret生产token
     * @return
     */
    public String createToken(String key,String secret){
        AccessToken accessToken = new AccessToken(key,secret);
        return  accessToken.getToken();
    }

    /**
     * 关闭阿里语音识别客户端连接
     */
    public void closeClient(){
        client.shutdown();
    }

}
