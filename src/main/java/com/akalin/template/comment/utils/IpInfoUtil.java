package com.akalin.template.comment.utils;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @Description //获取IP信息工具
 * @author Akalin
 * @Date 2020/3/21 16:49
 * @return
 * @throws
 */
@Slf4j
@Component
public class IpInfoUtil {

//    @Value("${xboot.qqlbs.key}")
    private String key = "akalin";

    @Autowired
    private AsyncUtil asyncUtil;

    /**
     * 获取客户端IP地址
     * @param request 请求
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        if("0:0:0:0:0:0:0:1".equals(ip)){
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     * 获取IP返回地理信息
     * @param
     * @return
     */
    public String getIpCity(HttpServletRequest request){

        String url = "https://apis.map.qq.com/ws/location/v1/ip?key="+ key +"&ip=" + getIpAddr(request);
        String result = "未知";
        try {

            String json = HttpUtil.get(url, 3000);
            JSONObject jsonObject = JSONObject.parseObject(json);
            String status = jsonObject.getString("status");
            if("0".equals(status)){
                JSONObject adInfo = jsonObject.getJSONObject("result").getJSONObject("ad_info");
                String nation = adInfo.getString("nation");
                String province = adInfo.getString("province");
                String city = adInfo.getString("city");
                String district = adInfo.getString("district");
                if(StrUtil.isNotBlank(nation)&& StrUtil.isBlank(province)){
                    result = nation;
                } else {
                    result = province;
                    if(StrUtil.isNotBlank(city)){
                        result += " " + city;
                    }
                    if(StrUtil.isNotBlank(district)){
                        result += " " + district;
                    }
                }
            }
        } catch (Exception e) {
            log.info("获取IP地理信息失败");
        }
        return result;
    }

    public void getUrl(HttpServletRequest request){

        try {
            String url = request.getRequestURL().toString();
            if(url.contains("127.0.0.1")||url.contains("localhost")){
                return;
            }
            asyncUtil.getUrl(url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getInfo(HttpServletRequest request, String p){
        try {
            String url = request.getRequestURL().toString();
            if(url.contains("127.0.0.1")||url.contains("localhost")){
                return;
            }
            asyncUtil.getInfo(url, p);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
