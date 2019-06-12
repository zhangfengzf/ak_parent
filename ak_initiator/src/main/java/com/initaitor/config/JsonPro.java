package com.initaitor.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * @ClassName: JsonPro
 * @Description: 获取配置文件中的属性值
 * @Author 张锋
 * @Date 2018/9/19 18:32
 * @Version 1.0
 */
@Component
@PropertySource("classpath:config/yyq.properties")
@ConfigurationProperties(prefix = "yyq")
@Data
public class JsonPro {
    private String chinese;

    private String chineseKEY;

    private String english;

    private String englishKEY;

    private String other;

    private String adminUrl;

    private String asrUrl;

    private String asrUrlKEY;

    private String audioUrlCN;

    private String audioUrlEN;

    private String audioUrlKEY;

    private String webUrl;

    private String webUrlKEY;

    private String serverUrl;

    private String serverUrlKEY;

    private String adminAddress;

    private String jsonName;

    private String addressOpen;

    private String addressClose;

    private String addressJson;

    private String startNginxAddress;

    private String stopNginxAddress;

    private String startFile;

    private String stopFile;

    private String jsonNameType;

    private String jsonNameTypeChineseValue;

    private String jsonNameTypeEnglishValue;

    private String jsonNameTypeOtherValue;

    private String recordAddress;

    private String fileAddressOpen;

    private String jarAddress;

    private String jarAddressBatFileName;

    private String name;

    private String hostname;

    private Integer port;

    private String userName;

    private String userPassword;

    private String jarAddressBatFileNameStop;

    private String interpreter1;

    private String interpreter2;

    private String filePath;

    private String fileUrl;

    private String fileUrlKEY;

    private String admin;

    private String adminKEY;

    private Integer speechNumber;

    private String meetingId;

    private String Internet;

    private String ffmpeg;

    private String audio;

    private String status;

    private String InternetURL;

    private String InternetLAN;

    private String UDPPortCN;

    private String UDPPortEN;

    private String TCPPort;

    private String addressOpenLANEN;

    private String addressCloseLANEN;

    private String addressOpenLANCN;

    private String addressCloseLANCN;

    private String TCPOpenURL;

    private String TCPCloseURL;

    private String addressOpenWEBCN;

    private String addressOpenWEBEN;

    private String addressCloseWEBCN;

    private String addressCloseWEBEN;

    private String audioUrlCNKEY;

    private String audioUrlENKEY;
}
