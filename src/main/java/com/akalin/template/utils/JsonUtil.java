package com.akalin.template.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName JsonUtil
 * @Description TODO
 * @Date 2019年12月01日 10:42
 * @Version 1.0
 * @auther Akalin
 **/
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LogManager
            .getLogger(JsonUtil.class);

    /**
     * 将对象转成json
     *
     * @param object 被转的对象
     * @return 返回json
     */
    public static String objToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("对象转json失败", e);
        }
        return null;
    }

    /**
     * 将json转成对象
     *
     * @param jsonString  被转的json
     * @param objectClass 转换后对象的class
     * @param <T>         泛型
     * @return 返回object
     */
    public static <T> T jsonToObject(String jsonString, Class<T> objectClass) {
        try {
            return objectMapper.readValue(jsonString, objectClass);
        } catch (Exception e) {
            logger.error("json转对象失败", e);
        }
        return null;
    }

    /**
     * 将json转成map
     *
     * @param jsonString 被转的json
     * @return 返回map
     */
//    public static Map<String, T> jsonToMap(String jsonString) {
//        try {
//            return objectMapper.readValue(jsonString, Map.class);
//        } catch (Exception e) {
//            logger.error("json转map失败", e);
//        }
//        return null;
//    }

    /**
     * @Description //获取字符串的数字
     * @author Akalin
     * @Date 2019/12/3 10:37
     * @param string
     * @return java.lang.String
     * @throws
     */
    public static String getNum( String string){
        if(string ==null || "".equals(string)){
            return string;
        }
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(string);
        return m.replaceAll("").trim();
    }
}
