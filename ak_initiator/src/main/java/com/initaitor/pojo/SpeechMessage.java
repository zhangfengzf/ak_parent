package com.initaitor.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName: Speech1
 * @Description: TODO
 * @Author 张锋
 * @Date 2019/1/25 10:06
 * @Version 1.0
 */
@Data
public class SpeechMessage implements Serializable {

    /*服务器ip地址*/
    private String Ip;
    /*声卡名字和语种*/
    private Integer soundNumber;
    /*声卡数量*/
    private Map<String,String> soundCardAndNames;

    public SpeechMessage() {}

    public SpeechMessage(String ip, Integer soundNumber, Map<String, String> soundCardAndNames) {
        Ip = ip;
        this.soundNumber = soundNumber;
        this.soundCardAndNames = soundCardAndNames;
    }

}
