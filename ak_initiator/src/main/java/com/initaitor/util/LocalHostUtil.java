package com.initaitor.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @ClassName: LocalHostUtil
 * @Description: 获取本机IP工具类
 * @Author 张锋
 * @Date 2019/7/30 16:50
 * @Version 1.0
 */
public class LocalHostUtil {

    public static String getIp() {
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
                            str = inetAddr.toString().replace("/", "");
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.getMessage();
        }
        return str;
    }

}
