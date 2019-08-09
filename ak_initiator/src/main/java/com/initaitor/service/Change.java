package com.initaitor.service;

/**
 * @ClassName: Change
 * @Description: 语种切换器
 * @Author 张锋
 * @Date 2019/8/8 16:44
 * @Version 1.0
 */
public interface Change {
    void textChange(String url, String type, String transportType) throws Exception;
    void speechChange(String urlCn,String urlEn,String cn,String en, String transportType) throws Exception;
}
