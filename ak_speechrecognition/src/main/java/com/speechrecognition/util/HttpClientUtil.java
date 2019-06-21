package com.speechrecognition.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public void doPost(String url,String message){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //HttpClients.createDefault();
        CloseableHttpResponse response = null;
        // 设置连接超时时间
        RequestConfig config = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(2000).build();
        HttpPost httpPost = new HttpPost(url);
        try{

            httpPost.setConfig(config);
            httpPost.addHeader("Content-Type","application/json");
            StringEntity entity = new StringEntity(message,"UTF-8");
            entity.setContentType("text/json");
            entity.setContentEncoding(new BasicHeader("Content-Type","application/json"));
            httpPost.setEntity(entity);
            response =  httpClient.execute(httpPost);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug("httpClient 发送.............");

         }finally {
            try{
                if(httpClient !=null){
                    httpClient.close();
                }
                if(response != null) {
                    response.close();
                }
            }catch (IOException e){
               e.printStackTrace();
            }
        }
        }

    }

