package com.initaitor.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SoundName
 * @Description: TODO
 * @Author 张锋
 * @Date 2019/7/31 19:48
 * @Version 1.0
 */
@Component
@ConfigurationProperties("sound")
@Data
public class SoundName {
    private List<Map<String,String>> soundName;
}
