package com.initaitor.util;


import com.initaitor.datamodel.dto.SpeechMessage;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: util
 * @Description: 工具类
 * @Author 张锋
 * @Date 2018/8/23 11:15
 * @Version 1.0
 */
@Repository
public class Util {

    private static Logger logger = LoggerFactory.getLogger(Util.class);

    //将声卡名字、声卡数量、语种名字、IP地址存入实体类
    public static SpeechMessage addSpeech1Message(){
        //本机ip地址
        String ip = getIp();
        //获取声卡名字
        Map<String, String> speechCard = getSpeechCard();
        //获取声卡数量
        Integer speechNumber = getSpeechNumber();
        SpeechMessage speechMessage = new SpeechMessage(ip,speechNumber,speechCard);
        return speechMessage;
    }
    //获取本机Ip地址
    public static String getIp(){
        //获取本机的IP地址
        String str = "192.168.12.1";
        try {
            // 遍历所有的网络接口
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            str = inetAddr.toString().replace("/","");
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.getMessage();
        }
        return str;
    }
    //获取声卡
    public static Map<String,String> getSpeechCard(){
        //获取本机的服务器所有声卡名字
        Mixer.Info[] mInfo = AudioSystem.getMixerInfo();
        Map<String, String> speech = new HashMap<>();
        for (int i=0;i<mInfo.length;i++) {
            if (!mInfo[i].toString().contains("Port")) {
                String [] arr = mInfo[i].getName().split("\\s+");
                if (arr[0].equals("cn")) {
                    speech.put("chinese",mInfo[i].getName());
                }else if(arr[0].equals("en")){
                    speech.put("english",mInfo[i].getName());
                }
            }
        }
        return speech;
    }
    //判断声卡数量是否相等
    public static Integer getSpeechNumber(){
        Map<String, String> speechCard = getSpeechCard();
        int size = speechCard.size();
        return size;
    }

    //json文件传输路径工具类
    public static void writeFile(String filePath, String sets)
            throws IOException {
        FileWriter fw = new FileWriter(filePath);
        PrintWriter out = new PrintWriter(fw);
        out.write(sets);
        out.println();
        fw.close();
        out.close();
    }

    //java调用CMD窗口工具类
    public static String runCMD(String rtm,String cmdStr) throws IOException {

        StringBuilder sBout = new StringBuilder();

        Process process = Runtime.getRuntime().exec("cmd.exe /c " + cmdStr);

        BufferedReader bFouT = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        final InputStreamReader err = new InputStreamReader(
                process.getErrorStream());

        final InputStreamReader in = new InputStreamReader(
                process.getInputStream());
        String outline = "";
        new Thread() {
            public void run() {
                BufferedReader bFerR = new BufferedReader(err);
                String errLine = "";
                StringBuilder sberr = new StringBuilder();
                try {
                    while ((errLine = bFerR.readLine()) != null) {
                        sberr.append(errLine);
                        sberr.append("\n");
//                        logger.info(errLine);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        err.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        return "";
    }
    public static JSONObject udpJsonCN() throws Exception{
        JSONObject jsonUDPCN = new JSONObject();
        jsonUDPCN.put("type","cn");
        return jsonUDPCN;
    }
    public static JSONObject udpJsonEN()throws Exception{
        JSONObject jsonUDPEN = new JSONObject();
        jsonUDPEN.put("type","en");
        return jsonUDPEN;
    }

}
