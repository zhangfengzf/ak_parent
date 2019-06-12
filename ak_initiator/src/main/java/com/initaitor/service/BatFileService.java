package com.initaitor.service;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName: BatFileService
 * @Description: TODO
 * @Author 张锋
 * @Date 2018/9/21 9:49
 * @Version 1.0
 */
public interface BatFileService {
    //生成与前端交互的Json文件
    void createJsonBatFile() throws JSONException, IOException;
    //生成前端交互的Json文件内容
    JSONObject getJson(String ipAddress) throws JSONException, UnsupportedEncodingException;
}
