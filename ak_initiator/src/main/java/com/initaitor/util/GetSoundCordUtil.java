package com.initaitor.util;

import com.initaitor.pojo.SpeechMessage;
import lombok.extern.java.Log;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.initaitor.util.LocalHostUtil.getIp;


/**
 * @ClassName: GetSoundCordUtil
 * @Description: 获取声卡信息工具类
 * @Author 张锋
 * @Date 2019/7/30 16:53
 * @Version 1.0
 */
@Log
public class GetSoundCordUtil {

    //将声卡名字、声卡数量、语种名字、IP地址存入实体类
    public static SpeechMessage addSpeechMessage(List<Map<String,String>> customSoundName) {
        //本机ip地址
        String ip = getIp();
        //获取声卡名字并得到声卡数量
        Map<String, String> soundCordMap = getSpeechCard(customSoundName);
        SpeechMessage speechMessage = new SpeechMessage(ip, soundCordMap.size(), soundCordMap);
        log.info("声卡名字+声卡数量+本机ip地址"+speechMessage.toString());
        return speechMessage;
    }

    //获取本机的服务器所有声卡名字
    private static Map<String, String> getSpeechCard(List<Map<String,String>> customSoundName) {
        Mixer.Info[] mInfo = AudioSystem.getMixerInfo();
        Map<String, String> soundCordMap = new HashMap<>();

        for (int i = 0; i < mInfo.length; i++) {
            if (!mInfo[i].toString().contains("Port")) {
                String[] arr = mInfo[i].getName().split("\\s+");
                for (int j = 0; j < customSoundName.size(); j++) {
                        if (customSoundName.get(j).get("name").equals(arr[0])){
                            soundCordMap.put(customSoundName.get(j).get("name"),mInfo[i].getName());
                        }
                }
            }
        }
        return soundCordMap;
    }

}
