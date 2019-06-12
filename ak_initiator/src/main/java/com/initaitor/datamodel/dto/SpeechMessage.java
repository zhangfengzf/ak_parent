package com.initaitor.datamodel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class SpeechMessage implements Serializable {
    /*
    * Ip:服务器ip地址
    * soundCardAndNames:声卡名字和语种
    * soundNumber:声卡数量
    * */
    private String Ip;
    private Integer soundNumber;
    private Map<String,String> soundCardAndNames;

}
