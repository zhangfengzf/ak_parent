package com.speechrecognition.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * httpurlconnection工具类
 */
@Component
public class HttpUrlConnectionUtil {

    public String httpPost(String url,String o){
        String msg="";
        try{
            URL httpUrl = new URL(url);
            HttpURLConnection  connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setConnectTimeout(2000);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);  // 设置缓存
            connection.setDoInput(true);   // 允许输入
            connection.setDoOutput(true);   // 允许输出
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            connection.setRequestProperty("Charset", "UTF-8");
            connection.connect();
            // 向请求的路径传入数据
            DataOutputStream dos=new DataOutputStream(connection.getOutputStream());
            dos.writeBytes(o);
            dos.flush();
            dos.close();
            // 返回的状态码
            int code=connection.getResponseCode();
            if(code == 200){
                BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line ="";
                while((line =bufferedReader.readLine())!=null){
                    msg +=line;
                }
                bufferedReader.close();
            }
        connection.disconnect();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
           e.printStackTrace();
        }

        return msg;
    }
}
