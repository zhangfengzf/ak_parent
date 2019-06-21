package com.speechrecognition.datamodel.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RequestModle {

    private String languageType; // 语种类型
    private String soundCarName; // 声卡名称
}
