package com.speechrecognition.datamodel.request;

import lombok.Data;

@Data
public class OpenSpeechRecognitionParam {
    private String requestList;
    private String userId;
    private String isOpen4G;
}
