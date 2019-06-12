package com.initaitor.datamodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: JsonPro
 * @Description: 开启时前端传输的json对象
 * @Author 张锋
 * @Date 2018/9/19 18:32
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingIdAndStatus implements Serializable {
    private String id;//会议id
    private String status;//4g或者局域网状态
}
