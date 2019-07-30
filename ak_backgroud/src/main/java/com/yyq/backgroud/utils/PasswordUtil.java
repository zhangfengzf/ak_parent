package com.yyq.backgroud.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * 1. 6-20位
 * 2. 字母或字母+数字+字符
 * 3. 不可为纯数字，可为纯字母
 * 4. 不可相同或连贯的3个字符，比如abc，123，aaa，111
 */
public class PasswordUtil {
    private static final int maxInt = 20;
    private static final int minInt = 6;

    public static Random random = new Random();

    /**
     * 随机生成一个 6 到 20 位的密码   字母 + 数字
     * * @return
     */
    public static String getRandomPassword(){
        int randInt = random.nextInt(maxInt - minInt + 1) + minInt;
        String randPasswrod = RandomStringUtils.randomAlphanumeric(randInt);
        return  randPasswrod;
    }


}
