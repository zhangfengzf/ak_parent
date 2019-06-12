package com.initaitor.service;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ClassName: JsonService
 * @Description: 对json的处理
 * @Author 张锋
 * @Date 2018/9/21 9:34
 * @Version 1.0
 */
public interface JsonService {
    //请求接口工具类
    String load(String url, JSONObject query) throws Exception;
    //文字识别接口传输的Json值
    JSONObject getJson(String id, String status) throws JSONException;
    //文件创建接口传输的JSON值
    JSONObject getFileJson(String name, String id, String status) throws JSONException;
}
