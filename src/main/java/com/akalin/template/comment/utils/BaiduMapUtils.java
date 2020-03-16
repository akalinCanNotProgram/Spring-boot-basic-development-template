package com.akalin.template.comment.utils;

import com.akalin.template.pojo.PageData;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ClassName BaiduMapUtils
 * @Description 百度地图相关api
 * @Author akalin
 * @Date 2019/9/4 19:37
 * @Param
 * @return
 **/
public class BaiduMapUtils {
    private static Log log = LogFactory.getLog(BaiduMapUtils.class);
    private static final String AK = "B3Af2GgzGoAHLiVodiU7HEAAS6qbgO3U";//百度地图密钥

    /**
     * 根据地址调用百度API获取经纬度
     * @param address
     * @return
     */
    public static PageData getCoordinate(String address) {
        PageData result = new PageData();
        if (address != null && !"".equals(address)) {
            address = address.replaceAll("\\s*", "").replace("#", "栋");
            //超过这个长度会查询不到信息
            if (address.length() >= 28){
                address = address.substring(0, 28);
            }
            String url = "http://api.map.baidu.com/geocoding/v3/?address=" + address + "&output=json&ak=" + AK + "&callback=showLocation";
            String json = loadJSON(url);
            int start = json.indexOf("(");
            int end = json.indexOf(")");
            if (json != null && !"".equals(json)) {
                JSONObject obj = JSONObject.parseObject(json.substring(start + 1, end));
                if ("0".equals(obj.getString("status"))) {
                    double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng"); // 经度
                    double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat"); // 纬度
                    log.info("地址 = " + address + " 经度 = " + lng + " 纬度 = " + lat);
                    result.put("lng", lng + "");
                    result.put("lat", lat + "");
                    return result;
                }
            }
        }
        return null;
    }

    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return json.toString();
    }
}
