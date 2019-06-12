package com.initaitor.service.impl;


import com.initaitor.config.JsonPro;
import com.initaitor.service.BatFileService;
import com.initaitor.util.Util;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * @ClassName: BatFileServiceImpl
 * @Description: TODO
 * @Author 张锋
 * @Date 2018/9/21 9:50
 * @Version 1.0
 */
@Service
public class BatFileServiceImpl implements BatFileService {

    @Autowired
    private JsonPro jsonPro;

    private static Logger logger = LoggerFactory.getLogger(BatFileServiceImpl.class);

    //生成与前端交互的Json文件
    @Override
    public void createJsonBatFile() throws JSONException, IOException {

        String ip = Util.getIp();
        JSONObject jsonObject = getJson(ip);
        String addressJson = jsonPro.getAddressJson();
        Util.writeFile(addressJson+"/setting.json", "var jsonData="+jsonObject.toString());
    }
    //生成前端交互的Json文件内容
    @Override
    public JSONObject getJson(String ipAddress) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(jsonPro.getAdminKEY(), jsonPro.getAdmin());
        jsonObject.put(jsonPro.getFileUrlKEY(),jsonPro.getFileUrl());
        jsonObject.put(jsonPro.getAsrUrlKEY(), "ws://"+ipAddress+":"+jsonPro.getAsrUrl());
        jsonObject.put(jsonPro.getAudioUrlCNKEY(),"ws://"+ipAddress+":"+jsonPro.getAudioUrlCN());
        jsonObject.put(jsonPro.getAudioUrlENKEY(),"ws://"+ipAddress+":"+jsonPro.getAudioUrlEN());
        jsonObject.put(jsonPro.getWebUrlKEY(), "http://"+ipAddress+":"+jsonPro.getWebUrl());
        jsonObject.put(jsonPro.getServerUrlKEY(),"http://"+ipAddress+":"+jsonPro.getServerUrl());
        jsonObject.put(jsonPro.getChineseKEY(), jsonPro.getChinese());
        jsonObject.put(jsonPro.getEnglishKEY(), jsonPro.getEnglish());
        logger.info(jsonObject.toString());
        return jsonObject;
    }

}
