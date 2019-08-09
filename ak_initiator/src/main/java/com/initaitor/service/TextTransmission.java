package com.initaitor.service;

import com.initaitor.pojo.SoundName;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TextTransmission
 * @Description: 文字传输
 * @Author 张锋
 * @Date 2019/7/31 10:22
 * @Version 1.0
 */
public interface TextTransmission {

    /*开启文字传输*/
    void startTextTransmission(String url, List<Map<String,String>> soundName, String transportType, String meetingID, String stat) throws Exception;
    /*关闭文字传输*/
    void stopTextTransmission(String url, String transportType) throws Exception;

}
