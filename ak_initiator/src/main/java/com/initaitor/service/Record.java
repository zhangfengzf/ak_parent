package com.initaitor.service;

/**
 * @ClassName: Record
 * @Description: 录音逻辑层
 * @Author 张锋
 * @Date 2019/8/9 11:16
 * @Version 1.0
 */
public interface Record {
    void recordSound(String url,String meetingID,String transportType) throws Exception;
}
