package com.initaitor.controller;

import com.initaitor.pojo.Sound;
import com.initaitor.service.Change;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ChangeController
 * @Description: 语种切换控制层
 * @Author 张锋
 * @Date 2019/7/30 16:28
 * @Version 1.0
 */
@RestController
@RequestMapping("change")
@CrossOrigin
public class ChangeController {

    @Autowired
    private Sound sound;
    @Autowired
    private Change change;

    @GetMapping("cn_en")
    public Boolean change() {
        try {
            change.textChange(sound.getTextTransmissionChangeURL(),"change","POST");
            change.speechChange(sound.getSpeechTransmissionChangeURLCN(),sound.getSpeechTransmissionChangeURLEN(),"en","cn","POST");
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @GetMapping("cn_cn")
    public Boolean change1() {
        try {
            change.textChange(sound.getTextTransmissionChangeURL(),"normal","POST");
            change.speechChange(sound.getSpeechTransmissionChangeURLCN(),sound.getSpeechTransmissionChangeURLEN(),"cn","en","POST");
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
