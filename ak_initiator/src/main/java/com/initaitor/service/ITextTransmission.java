package com.initaitor.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

import static com.initaitor.util.RequestUtil.loadRequest;

/**
 * @ClassName: ITextTransmission
 * @Description: 文字传输逻辑层
 * @Author 张锋
 * @Date 2019/7/31 10:44
 * @Version 1.0
 */
@Service
@Log
public class ITextTransmission implements TextTransmission{

    @Override
    public void startTextTransmission(String url, List<Map<String,String>> soundName, String transportType , String meetingID, String stat) throws Exception {
        JSONArray jsonArray = new JSONArray();
        System.out.println(soundName);
        for (int i = 0; i < soundName.size(); i++) {
            JSONObject jo = new JSONObject();
            System.out.println(soundName.get(i));
            jo.put("languageType",soundName.get(i).get("type"));
            jo.put("soundCarName",soundName.get(i).get("name"));
            jsonArray.add(i,jo);
        }
        JSONObject jo = new JSONObject();
        jo.put("requestList", jsonArray);
        jo.put("userId",meetingID);
        jo.put("isOpen4G",stat);
        log.info("传输给语音识别的json"+jo);
        loadRequest(url,jo,transportType);
    }

    @Override
    public void stopTextTransmission(String url, String transportType) throws Exception {
        loadRequest(url,null,transportType);
    }

}
