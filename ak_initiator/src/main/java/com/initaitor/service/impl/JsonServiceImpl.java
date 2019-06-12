package com.initaitor.service.impl;


import com.initaitor.config.JsonPro;
import com.initaitor.service.JsonService;
import com.initaitor.util.Util;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


/**
 * @ClassName: JsonServiceImpl
 * @Description: 接口调用
 * @Author 张锋
 * @Date 2018/9/21 9:36
 * @Version 1.0
 */
@Service
public class JsonServiceImpl implements JsonService {

    //注入jsonPro对象
    @Autowired
    private JsonPro jsonPro;

    private static Logger logger = LoggerFactory.getLogger(JsonServiceImpl.class);

    //请求接口工具类
    @Override
    public  String load(String url,JSONObject query) throws Exception {
        URL restURL = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();

        conn.setRequestMethod("POST");

        //设置是否从httpUrlConnection读入
        conn.setDoOutput(true);

        conn.setAllowUserInteraction(false);
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        PrintStream ps = new PrintStream(conn.getOutputStream());
        if (query!=null){
            ps.print(query);
            ps.close();
        }
        BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line,resultStr="";

        while(null != (line=bReader.readLine()))
        {
            resultStr += line;
        }
        bReader.close();
        return resultStr;
    }
    //识别接口,传输的Json值
    @Override
    public  JSONObject getJson(String id,String status) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        int i = 0;
        //获取声卡名字
        Map<String, String> speechCard = Util.getSpeechCard();
        for (Map.Entry<String,String> entry : speechCard.entrySet()) {
            if (entry.getKey().equals("chinese")){
                JSONObject json1 = new JSONObject();
                json1.put(jsonPro.getJsonName(),jsonPro.getChinese());
                json1.put(jsonPro.getJsonNameType(),jsonPro.getJsonNameTypeChineseValue());
                jsonArray.put(i,json1);
            }else if(entry.getKey().equals("english")){
                JSONObject json2 = new JSONObject();
                json2.put(jsonPro.getJsonName(),jsonPro.getEnglish());
                json2.put(jsonPro.getJsonNameType(),jsonPro.getJsonNameTypeEnglishValue());
                jsonArray.put(i,json2);
            }else if(entry.getKey().equals("other")){
                JSONObject json3 = new JSONObject();
                json3.put(jsonPro.getJsonName(),jsonPro.getOther());
                json3.put(jsonPro.getJsonNameType(),jsonPro.getJsonNameTypeOtherValue());
                jsonArray.put(i,json3);
            }
            i++;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("requestList", jsonArray);
        jsonObject.put("userId",id);
        jsonObject.put("isOpen4G",status);
        logger.info("传输给语音识别的json"+jsonObject.toString());
        return jsonObject;

    }

    //文件生成接口,传输的Json值
    @Override
    public JSONObject getFileJson(String fileName,String meetingId,String status) throws JSONException {

        JSONObject json1 = new JSONObject();
        json1.put("textPath",jsonPro.getRecordAddress()+"/"+fileName);
        json1.put("textCount",9);
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(0,jsonPro.getEnglish());
        jsonArray.put(1,jsonPro.getChinese());
        json1.put("langFile",jsonArray);
        JSONObject json = new JSONObject();
        json.put("configModel",json1);
        json.put("userId",Integer.parseInt(meetingId));
        json.put("isOpen4G",status);
        logger.info("文件生成接口传输的Json值"+json.toString());

        return json;
    }
}
