package com.initaitor.service;

/**
 * @ClassName: Switcher
 * @Description: 语种切换器
 * @Author 张锋
 * @Date 2019/7/31 10:33
 * @Version 1.0
 */
public interface Switcher {

    /*soundCordCN对应中文，EN对应英文*/
    void switcherSoundCordCNTOCN() throws Exception;
    /*soundCordCN对应英文，EN对应中文*/
    void switcherSoundCordCNTOEN() throws Exception;

}
