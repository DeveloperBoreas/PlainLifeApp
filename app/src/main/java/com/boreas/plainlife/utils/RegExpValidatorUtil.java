package com.boreas.plainlife.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpValidatorUtil {
    /**
     * 校验ip端口 192.168.1.1：8888
     * @param str
     * @return
     */
    public static boolean regExpIpAndPort(String str){
        String reg = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))):\\d+";
        return match(reg,str);
    }
    /**
     * 校验ip 192.168.1.1
     * @param str
     * @return
     */
    public static boolean regExpIp(String str){
        String reg = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";
        return match(reg,str);
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
