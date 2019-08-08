package com.initaitor.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName: RequestUtil
 * @Description: 请求工具类
 * @Author 张锋
 * @Date 2019/7/30 16:38
 * @Version 1.0
 */
public class RequestUtil {

    private static String line;
    private static String resultStr;

    public static String loadRequest(String url, JSONObject query, String transportType) throws Exception {
        URL restURL = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();

        conn.setRequestMethod(transportType);

        //设置是否从httpUrlConnection读入
        conn.setDoOutput(true);

        conn.setAllowUserInteraction(false);
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        PrintStream ps = new PrintStream(conn.getOutputStream());
        if (query != null) {
            ps.print(query);
            ps.close();
        }
        BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        while ((line = bReader.readLine()) != null) {
            resultStr += line;
        }
        bReader.close();
        return resultStr;
    }

}
