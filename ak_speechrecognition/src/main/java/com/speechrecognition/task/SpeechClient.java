package com.speechrecognition.task;

import com.alibaba.nls.client.AccessToken;
import com.alibaba.nls.client.protocol.NlsClient;
import org.springframework.beans.factory.annotation.Value;

public class SpeechClient {
    @Value("")
    private  String token;
    @Value("")
    private String key;
    @Value("")
    private String secret;

    private static NlsClient client;

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
