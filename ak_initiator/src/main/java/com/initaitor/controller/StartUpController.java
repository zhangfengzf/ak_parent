package com.initaitor.controller;


import com.alibaba.fastjson.JSONObject;
import com.initaitor.config.JsonPro;
import com.initaitor.constant.AkStateConstant;
import com.initaitor.datamodel.request.MeetingIdAndStatus;
import com.initaitor.datamodel.vo.ResponseEntity;
import com.initaitor.service.BatFileService;
import com.initaitor.service.JsonService;
import com.initaitor.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @ClassName: StartUpController
 * @Description: 所有操作控制
 * @Author 张锋
 * @Date 2019/1/22 17:54
 * @Version 1.0
 */
@RequestMapping("/config")
@RestController
@CrossOrigin
public class StartUpController {

    @Autowired
    private JsonPro jsonPro;
    @Autowired
    private JsonService jsonServiceImpl;
    @Autowired
    private BatFileService batFileServiceImpl;
    //判断会议是否一致
    private String ifMeetingId;
    //会场录音文件和文字识别json
    private org.json.JSONObject json;
    //日志
    private static Logger logger = LoggerFactory.getLogger(StartUpController.class);


    //判断声卡状态
    private boolean checkAudioState(){
        //判断声卡是否插好
        int speechNumber = Util.getSpeechNumber();
        if (speechNumber != jsonPro.getSpeechNumber()) {
            return false;
        }
        return true;
    }
    //初始化设置，提交表单，获取前端属性值，开启程序
    @PostMapping(value = "/alter")
    public ResponseEntity alterConfig(@RequestBody MeetingIdAndStatus meetingIdAndStatus) {
        try {
            //如果用户开启时声卡线没有接好，再次开启时需要等待系统获取声卡信息
            for (int i = 0; i <10 && !this.checkAudioState();i++) {
                Thread.sleep(500);
                if(i>=9){
                    return new ResponseEntity(0, "声卡未连接正确");
                }
            }
            //关闭语音识别
            if (AkStateConstant.speechRecognitionBool) {
                jsonServiceImpl.load(jsonPro.getAddressClose(), null);
                AkStateConstant.speechRecognitionBool = false;
                //睡眠，防止语音识别未关闭成功
                Thread.sleep(3000);
            }

            //保存会议id
            jsonPro.setMeetingId(meetingIdAndStatus.getId());
            jsonPro.setStatus(meetingIdAndStatus.getStatus());

            //生成录音和语音识别保存文件，调用接口
            json = jsonServiceImpl.getFileJson(meetingIdAndStatus.getId() + "----", meetingIdAndStatus.getId(), meetingIdAndStatus.getStatus());
            String filePath = jsonServiceImpl.load(jsonPro.getFileAddressOpen(), json);
            jsonPro.setFilePath(filePath);

            //文件生成,语音文字识别启动
            json = jsonServiceImpl.getJson(meetingIdAndStatus.getId(), meetingIdAndStatus.getStatus());
            String str = jsonServiceImpl.load(jsonPro.getAddressOpen(), json);
            if (!(str.equals("启动成功") || str.equals("已启动"))) {
                new ResponseEntity(1, "启动失败，请重启");
            } else {
                AkStateConstant.speechRecognitionBool = true;
            }
            //生成本地文件，记录地址等信息
            batFileServiceImpl.createJsonBatFile();
            if("true".equals(meetingIdAndStatus.getStatus())){
                //开启4gwebsocket语音传输
                jsonServiceImpl.load(jsonPro.getAddressCloseWEBCN(), null);
                jsonServiceImpl.load(jsonPro.getAddressOpenWEBCN(), Util.udpJsonCN());
                jsonServiceImpl.load(jsonPro.getAddressCloseWEBEN(), null);
                jsonServiceImpl.load(jsonPro.getAddressOpenWEBEN(), Util.udpJsonEN());
            }else{
                //开启局域网websocket语音传输
                jsonServiceImpl.load(jsonPro.getAddressCloseLANCN(), null);
                jsonServiceImpl.load(jsonPro.getAddressCloseLANEN(), null);
                jsonServiceImpl.load(jsonPro.getAddressOpenLANCN(), Util.udpJsonCN());
                jsonServiceImpl.load(jsonPro.getAddressOpenLANEN(), Util.udpJsonEN());
            }
            AkStateConstant.isOpenBool = true;
            //赋值会议id，用于判断对应会议开启状态
            ifMeetingId = meetingIdAndStatus.getId();
            return new ResponseEntity(2, "启动成功");
        } catch (Exception e) {
            logger.info(e.getMessage());
            return new ResponseEntity(1, "启动失败，请重启");
        }
    }

    //关闭程序
    @PostMapping(value = "/close")
    public String close() {
        try {
            //关闭语音识别
            if (AkStateConstant.speechRecognitionBool) {
                jsonServiceImpl.load(jsonPro.getAddressClose(), null);
                AkStateConstant.speechRecognitionBool = false;
            }
            //关闭4Gwebsocket语音传输
            jsonServiceImpl.load(jsonPro.getAddressCloseWEBCN(), null);
            jsonServiceImpl.load(jsonPro.getAddressCloseWEBEN(), null);
            //关闭局域网websocket语音传输
            jsonServiceImpl.load(jsonPro.getAddressCloseLANEN(), null);
            jsonServiceImpl.load(jsonPro.getAddressCloseLANCN(), null);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        AkStateConstant.isOpenBool = false;
        ifMeetingId = "1";
        return "true";
    }

    //判断系统开启的状态
    @PostMapping(value = "/disabled")
    public Boolean disabled(@RequestBody String id) {
        //.bat文件生成,开启服务器
        JSONObject jsonObject = JSONObject.parseObject(id);
        Integer statusId = (Integer) jsonObject.get("id");
        System.out.println(statusId);
        try {
            batFileServiceImpl.createJsonBatFile();
        } catch (Exception e) {
            e.getMessage();
        }
        if (ifMeetingId.equals(statusId.toString())) {
            AkStateConstant.isOpenBool = true;
        } else {
            AkStateConstant.isOpenBool = false;
        }
        return  AkStateConstant.isOpenBool;
    }

}
