package com.initaitor.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: JsonPro
 * @Description: 开启时前端传输的json对象
 * @Author 张锋
 * @Date 2018/9/19 18:32
 * @Version 1.0
 */
@Data
public class MeetingIdAndStatus implements Serializable {

    /*会议id*/
    private String id;
    /*判断开启会议类型（true:4G false:局域网）*/
    private String status;

    public MeetingIdAndStatus() {}

    public MeetingIdAndStatus(String id, String status) {
        this.id = id;
        this.status = status;
    }

}
