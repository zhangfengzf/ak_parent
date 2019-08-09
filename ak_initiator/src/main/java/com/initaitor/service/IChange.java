package com.initaitor.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import static com.initaitor.util.RequestUtil.loadRequest;

/**
 * @ClassName: IChange
 * @Description: 语种切换逻辑层
 * @Author 张锋
 * @Date 2019/8/8 16:45
 * @Version 1.0
 */
@Service
public class IChange implements Change{

    @Override
    public void textChange(String url, String type, String transportType) throws Exception {
        JSONObject jo = new JSONObject();
        jo.put("type",type);
        loadRequest(url,jo,transportType);
    }

    @Override
    public void speechChange(String urlCn,String urlEn,String cn,String en, String transportType) throws Exception {
        JSONObject joCn = new JSONObject();
        joCn.put("soundName",cn);
        JSONObject joEn = new JSONObject();
        joEn.put("soundName",en);
        loadRequest(urlCn,joCn,transportType);
        loadRequest(urlEn,joEn,transportType);
    }

}
