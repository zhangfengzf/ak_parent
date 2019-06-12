package com.example.initiator.pojo;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName: Speech1
 * @Description: TODO
 * @Author 张锋
 * @Date 2019/1/25 10:06
 * @Version 1.0
 */
public class SpeechMessage implements Serializable {
    /*
    * Ip:服务器ip地址
    * soundCardAndNames:声卡名字和语种
    * soundNumber:声卡数量
    * */
    private String Ip;
    private Integer soundNumber;
    private Map<String,String> soundCardAndNames;

    public SpeechMessage(String ip, Integer soundNumber, Map<String, String> soundCardAndNames) {
        Ip = ip;
        this.soundNumber = soundNumber;
        this.soundCardAndNames = soundCardAndNames;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public Integer getSoundNumber() {
        return soundNumber;
    }

    public void setSoundNumber(Integer soundNumber) {
        this.soundNumber = soundNumber;
    }

    public Map<String, String> getSoundCardAndNames() {
        return soundCardAndNames;
    }

    public void setSoundCardAndNames(Map<String, String> soundCardAndNames) {
        this.soundCardAndNames = soundCardAndNames;
    }

    @Override
    public String toString() {
        return "SpeechMessage{" +
                "Ip='" + Ip + '\'' +
                ", soundNumber=" + soundNumber +
                ", soundCardAndNames=" + soundCardAndNames +
                '}';
    }
}
