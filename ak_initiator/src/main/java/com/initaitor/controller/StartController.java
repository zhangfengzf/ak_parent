package com.initaitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.initaitor.pojo.*;
import com.initaitor.service.FileGeneration;
import com.initaitor.service.SpeechTransmission;
import com.initaitor.service.TextTransmission;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.initaitor.util.GetSoundCordUtil.addSpeechMessage;
import static com.initaitor.util.NetworkState.interTimers;


/**
 * @ClassName: StartController
 * @Description: 启动控制层
 * @Author 张锋
 * @Date 2019/7/30 16:27
 * @Version 1.0
 */
@RestController
@RequestMapping("config")
@CrossOrigin
@Log
public class StartController {

    @Autowired
    private Sound sound;
    @Autowired
    private SpeechTransmission speechTransmission;
    @Autowired
    private TextTransmission textTransmission;
    @Autowired
    private SoundName soundName;
    @Autowired
    private FileGeneration fileGeneration;
    /*声卡连接后，程序需要一段时间才能获取到声卡信息*/
    private Boolean soundError = true;
    /*while 循环次数，当大于6次说明声卡还是没有正确连接*/
    private int numberCirculation = 0;
    /*通过会议id来判断当前开启的会议*/
    private String startID = "0";
    /*开启的状态，true为4G，false为局域网*/
    private Boolean stat = true;

    @PostMapping(value = "alter")
    public ResponseEntity alterConfig(@RequestBody MeetingIdAndStatus mis) {

        /*对应的会议id*/
        String id = mis.getId();
        /*开启的状态true为4G，false为局域网*/
        stat = Boolean.valueOf(mis.getStatus());
        /*声卡信息判断*/
        SpeechMessage sm = addSpeechMessage(soundName.getSoundName());

        if (!startID.equals("0")){
            return new ResponseEntity(0, "启动失败，同一台设备不能开启两场会议！ 请关闭已开启会议，在开启该会议");
        }
        /*用户声卡连接错误后第二次连入*/
        while (!soundError){
            if (sound.getSoundNumber().equals(sm.getSoundNumber())){
                soundError = true;
                break;
            }else {
                if (numberCirculation<=6){
                    try {
                        ++numberCirculation;
                        Thread.sleep(3000);
                    }catch (Exception e){
                        e.getMessage();
                        return new ResponseEntity(0, "启动失败，睡眠出现异常！");
                    }
                }else {
                    return new ResponseEntity(0, "启动失败，声卡连接错误！"+ "当前连接的声卡数量是：" + sm.getSoundNumber());
                }
            }
        }
        if (!sound.getSoundNumber().equals(sm.getSoundNumber())) {
            soundError = false;
            return new ResponseEntity(0, "启动失败，声卡连接错误！");
        }
        /*判断网络状况*/
        interTimers();
        /*语音传输*/
        try {
            if (stat) {
                /*4G*/
                for (int i = 0; i <soundName.getSoundName().size() ; i++) {
                    speechTransmission.start4GSpeechTransmission(soundName.getSoundName().get(i).get("speechTransmission4GStartURL"),
                            id,
                            "post",
                            soundName.getSoundName().get(i).get("name"));
                }
            } else {
                /*局域网*/
                /*生成局域网地址文件*/
                fileGeneration.jsonWebFile(soundName,sound,sm.getIp());
                /*语音传输启动*/
                for (int i = 0; i <soundName.getSoundName().size() ; i++) {
                    speechTransmission.startLocalSpeechTransmission(soundName.getSoundName().get(i).get("speechTransmission4GStartURL"),
                            id,
                            "post");
                }
            }
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity(1, "启动失败，语音传输启动失败，请检查网络是否连接正确！");
        }
        /*文字传输*/
        try {
            textTransmission.startTextTransmission("","post");
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity(1, "启动失败，文字传输启动失败！");
        }
        /*记录会议ID，判断该会议的状态*/
        startID = mis.getId();
        /*启动成功*/
        return new ResponseEntity(2, "启动成功！");
    }

    //关闭程序
    @PostMapping(value = "/close")
    public String close() {

        try {
            for (int i = 0; i <soundName.getSoundName().size() ; i++) {
                if (stat){
                    /*关闭4G语音传输*/
                    speechTransmission.stop4GSpeechTransmission(soundName.getSoundName().get(i).get("speechTransmission4GStopURL"),
                            "post");
                }else {
                    /*关闭局域网语音传输*/
                    speechTransmission.stopLocalSpeechTransmission(soundName.getSoundName().get(i).get("speechTransmissionLocalStopURL"),
                            "post");
                }
            }
            /*关闭文字传输*/
            textTransmission.stopTextTransmission("","post");
        }catch (Exception e){
            e.getMessage();
            return "false";
        }
        /*关闭成功后需要把判断是否开启的startID还原*/
        startID = "0";
        return "true";
    }

    //判断系统开启的状态
    @PostMapping(value = "/disabled")
    public Boolean disabled(@RequestBody String id) {

        JSONObject jo = JSONObject.parseObject(id);
        String meetingID = (String) jo.get("id");
        return startID.equals(meetingID);

    }
}
