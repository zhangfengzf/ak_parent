package com.initaitor.service;

import com.alibaba.fastjson.JSONObject;
import com.initaitor.pojo.Sound;
import com.initaitor.pojo.SoundName;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import static com.initaitor.util.FileUtil.createFile;


/**
 * @ClassName: IFileGeneration
 * @Description: 文件生成，局域网访问地址
 * @Author 张锋
 * @Date 2019/8/5 15:36
 * @Version 1.0
 */
@Service
@Log
public class IFileGeneration implements FileGeneration{

    @Override
    public void jsonWebFile(SoundName soundName, Sound sound, String ip) throws Exception {
        JSONObject jo = new JSONObject();
        /*手机端访问文字的对应地址*/
        jo.put(sound.getWebText(),"http://"+ ip +":8270");
        /*手机端访问声音的对应地址*/
        for (int i = 0; i < soundName.getSoundName().size(); i++) {
            jo.put(soundName.getSoundName().get(i).get("webSound"),"http://"+ ip +":8005");
        }
        /*nginx 页面的访问地址*/
        jo.put(sound.getWebNginx(),"http://"+ ip +":8002");
        /*手机端页面文件访问地址*/
        jo.put("fileUrl",sound.getWebFile());
        /*访问本地服务端地址*/
        jo.put("serverUrl",sound.getServerUrl());
        /*登录是页面访问服务器地址*/
        jo.put("adminUrl",sound.getAdminUrl());
        /*语种对应声卡名字*/
        for (int i = 0; i <soundName.getSoundName().size() ; i++) {
            jo.put(soundName.getSoundName().get(i).get("name"),soundName.getSoundName().get(i).get("name"));
        }
        log.info(jo.toJSONString());
        createFile(sound.getJsonAddress(),jo.toJSONString());
    }

}

