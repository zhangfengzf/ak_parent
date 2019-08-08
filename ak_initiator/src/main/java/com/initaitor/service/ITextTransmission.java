package com.initaitor.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import static com.initaitor.util.RequestUtil.loadRequest;

/**
 * @ClassName: ITextTransmission
 * @Description: 文字传输逻辑层
 * @Author 张锋
 * @Date 2019/7/31 10:44
 * @Version 1.0
 */
@Service
public class ITextTransmission implements TextTransmission{

    @Override
    public void startTextTransmission(String url, String transportType) throws Exception {
        JSONObject jo = new JSONObject();
        loadRequest(url,jo,transportType);
    }

    @Override
    public void stopTextTransmission(String url, String transportType) throws Exception {
        loadRequest(url,null,transportType);
    }

}
