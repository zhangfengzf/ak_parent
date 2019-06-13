package com.speechrecognition.datamodel.request;

import org.springframework.stereotype.Component;

@Component
public class Common {
    // 是否开启4G文字传输，默认开启
    private static String isOpen4G = "open";
    // 是否第一次开启语音识别，区别第一次开启和重启（语音切换需要重启）
    private static Boolean isFirstOpenSpeech = true;

    private static Boolean isCloseSpeech = false;

    public static Boolean getIsCloseSpeech() {
        return isCloseSpeech;
    }

    public static void setIsCloseSpeech(Boolean isCloseSpeech) {
        Common.isCloseSpeech = isCloseSpeech;
    }

    public static String getIsOpen4G() {
        return isOpen4G;
    }

    public static void setIsOpen4G(String isOpen4G) {
        Common.isOpen4G = isOpen4G;
    }

    public static Boolean getIsFirstOpenSpeech() {
        return isFirstOpenSpeech;
    }

    public static void setIsFirstOpenSpeech(Boolean isFirstOpenSpeech) {
        Common.isFirstOpenSpeech = isFirstOpenSpeech;
    }
}
