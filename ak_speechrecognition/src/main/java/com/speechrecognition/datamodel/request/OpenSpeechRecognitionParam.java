package com.speechrecognition.datamodel.request;

import lombok.Data;

import java.util.List;

@Data
public class OpenSpeechRecognitionParam {
    private List<RequestModle> requestList;
    private String userId;
    private String isOpen4G;
    private String type;    // 是否交换
}
