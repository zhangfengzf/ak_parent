package com.initaitor.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
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
public class Sound implements Serializable {

    @Value("${sound.soundNumber}")
    private Integer soundNumber;
    @Value("${sound.textTransmissionStartURL}")
    private String textTransmissionStartURL;
    @Value("${sound.textTransmissionStopURL}")
    private String textTransmissionStopURL;
    @Value("${sound.webText}")
    private String webText;
    @Value("${sound.webNginx}")
    private String webNginx;
    @Value("${sound.webFile}")
    private String webFile;
    @Value("${sound.jsonAddress}")
    private String jsonAddress;
    @Value("${sound.serverUrl}")
    private String serverUrl;
    @Value("${sound.adminUrl}")
    private String adminUrl;

}
