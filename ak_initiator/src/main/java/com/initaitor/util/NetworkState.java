package com.initaitor.util;

import lombok.Cleanup;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName: NetworkState
 * @Description: 网络状态工具类
 * @Author 张锋
 * @Date 2019/8/5 9:46
 * @Version 1.0
 */
public class NetworkState {

    public static void interTimers(){
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                        URL url = new URL("http://baidu.com");
                        try {
                            @Cleanup InputStream in = url.openStream();
                            System.out.println("网络连接正常！");
                        } catch (IOException e) {
                            System.out.println("网络连接失败！");
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }, 1000, 3000);
    }


}
