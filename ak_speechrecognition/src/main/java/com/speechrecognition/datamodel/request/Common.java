package com.speechrecognition.datamodel.request;

import org.springframework.stereotype.Component;

@Component
public class Common {

    // 语音识别开启状态
    private static volatile Boolean isOpenSpeech = true;
   // 切换状态，只要切换就设为false
    private static volatile Boolean  soundCarAndLanguageState = true;
    //  声卡类型和说话语音类型是否对应， 默认对应“normal”  中文声卡说中文语音，英文声卡说英文语音，不对应为“change”
    private static volatile String soundCarAndLanguageType="normal";
    // 定时任务状态
    private static volatile Boolean scheduleTaskstate =true;

    public static Boolean getScheduleTaskstate() {
        return scheduleTaskstate;
    }

    public static void setScheduleTaskstate(Boolean scheduleTaskstate) {
        Common.scheduleTaskstate = scheduleTaskstate;
    }

    public static String getSoundCarAndLanguageType() {
        return soundCarAndLanguageType;
    }

    public static void setSoundCarAndLanguageType(String soundCarAndLanguageType) {
        Common.soundCarAndLanguageType = soundCarAndLanguageType;
    }

    public static Boolean getSoundCarAndLanguageState() {
        return soundCarAndLanguageState;
    }

    public static void setSoundCarAndLanguageState(Boolean soundCarAndLanguageState) {
        Common.soundCarAndLanguageState = soundCarAndLanguageState;
    }

    public static Boolean getIsOpenSpeech() {
        return isOpenSpeech;
    }

    public static void setIsOpenSpeech(Boolean isOpenSpeech) {
        Common.isOpenSpeech = isOpenSpeech;
    }


}
