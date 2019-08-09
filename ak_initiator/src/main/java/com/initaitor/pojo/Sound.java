package com.initaitor.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @ClassName: Sound
 * @Description: TODO
 * @Author 张锋
 * @Date 2019/7/31 13:58
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties("sound")
public class Sound implements Serializable {

    private Integer soundNumber;
    private String textTransmissionStartURL;
    private String textTransmissionStopURL;
    private String webText;
    private String webNginx;
    private String webFile;
    private String jsonAddress;
    private String serverUrl;
    private String adminUrl;
    private String meetingID;
    private String speechTransmissionChangeURLCN;
    private String speechTransmissionChangeURLEN;
    private String textTransmissionChangeURL;
    private String record;

}
