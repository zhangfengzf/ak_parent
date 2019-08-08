package com.initaitor.service;


import com.initaitor.pojo.Sound;
import com.initaitor.pojo.SoundName;

/**
 * @ClassName: FileGeneration
 * @Description: 文件生成，局域网访问地址
 * @Author 张锋
 * @Date 2019/8/5 15:35
 * @Version 1.0
 */
public interface FileGeneration {
    /*生成局域网页面语音文字访问地址*/
    void jsonWebFile(SoundName soundName, Sound sound, String ip) throws Exception;
}
