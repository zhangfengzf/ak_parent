package com.yyq.backgroud.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 短信工具类
 *
 * @author Q00596
 * @date 2019/04/19
 */
public class SmsUtils {
    public static String smsUser = "Lanbridge";
    public static String smsKey = "KrlKBdzK9t1zQik8IPsRMORFGA3pRCyb";
    private final static char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String send(String varsJSON, String mobile) {
        String url = "http://www.sendcloud.net/smsapi/send";
        String templateType ="33166";
        // 填充参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("smsUser", smsUser);
        params.put("templateId", templateType);
        params.put("msgType", "0");
        params.put("phone", mobile);
        params.put("vars", varsJSON);
        // 对参数进行排序
        Map<String, String> sortedMap = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String arg0, String arg1) {
                // 忽略大小写
                return arg0.compareToIgnoreCase(arg1);
            }
        });
        sortedMap.putAll(params);
        // 计算签名
        StringBuilder sb = new StringBuilder();
        sb.append(smsKey).append("&");
        for (String s : sortedMap.keySet()) {
            sb.append(String.format("%s=%s&", s, sortedMap.get(s)));
        }
        sb.append(smsKey);
        String sig = DigestUtils.md5Hex(sb.toString());
        // 将所有参数和签名添加到post请求参数数组里
        List<BasicNameValuePair> postparams = new ArrayList<>();
        for (String s : sortedMap.keySet()) {
            postparams.add(new BasicNameValuePair(s, sortedMap.get(s)));
        }
        postparams.add(new BasicNameValuePair("signature", sig));
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(postparams, "utf8"));
            CloseableHttpClient httpClient;
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(100000).build();
            httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
            CloseableHttpResponse response = httpClient.execute(httpPost);
            response.getEntity();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            httpPost.releaseConnection();
        }
        return null;
    }


    private static String bytes2hex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        int t;
        for (int i = 0; i < 16; i++) {
            t = bytes[i];
            if (t < 0) {
                t += 256;
            }
            sb.append(HEX_DIGITS[(t >>> 4)]);
            sb.append(HEX_DIGITS[(t % 16)]);
        }

        return sb.toString();

    }


    public static String getMd5String(String strSrc) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return bytes2hex(md5.digest(strSrc.getBytes()));
    }

    /**
     * 随机生成6位随机验证码
     * 方法说明
     *
     * @return String
     * @Discription:扩展说明
     */
    public static String createRandomVcode() {
        //验证码
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }


    public static void main(String[] args) {
        String randomVcode = createRandomVcode();
        SmsUtils.send("{\"name\":\"赵勇\",\"code\":\"111111\"}", "13980476439");

        System.out.println(randomVcode);
    }

}
