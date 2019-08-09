package com.initaitor.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import static com.initaitor.util.RequestUtil.loadRequest;

/**
 * @ClassName: IRecord
 * @Description: 录音逻辑层
 * @Author 张锋
 * @Date 2019/8/9 11:18
 * @Version 1.0
 */
@Service
public class IRecord implements Record{
    @Override
    public void recordSound(String url,String meetingID,String transportType) throws Exception {
        JSONObject jo = new JSONObject();
        jo.put("meetingID",meetingID);
        loadRequest(url,jo,transportType);
    }
}
