package com.akalin.template.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName StringUtils
 * @Description
 * @Date 2019年12月18日 14:26
 * @Version 1.0
 * @auther Akalin
 **/
public class StringUtils {
    /**
     * @Description //去除字符串中的以上字符
     *   \n 回车(\u000a)
     *   \t 水平制表符(\u0009)
     *   \s 空格(\u0008)
     *   \r 换行(\u000d)
     * @author Akalin
     * @Date 2019/12/18 14:29
     * @param str
     * @return java.lang.String
     * @throws
     */
    public static String replaceAllBlank(String str){
         String s = "";
         if (str!=null) {
             Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
             s = m.replaceAll("");
         }
         return s;
    }
    /**
     * @Description //剔除前后冗余数据
     * @author Akalin
     * @Date 2019/12/20 17:22
     * @param str
     * @return java.lang.String
     * @throws 
     */
    public static String getJsonString(String str){
        String s = "";
        String regex = "(?<=\\{).*(?=\\})";
        Pattern pattern = Pattern.compile (regex);
        Matcher matcher = pattern.matcher (str);
        if (matcher.find ())
        {
            return "{"+matcher.group()+"}";
        }
        return s;
    }
}
