package com.akalin.template.serviceImpl;

import cn.hutool.http.HttpUtil;
import com.akalin.template.service.Test;
import com.akalin.template.utils.RedisUtils;
import com.akalin.template.utils.StringUtils;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName getDataFromPLCImpl
 * @Description TODO
 * @Date 2019年12月30日 8:56
 * @Version 1.0
 * @auther Akalin
 **/
@Slf4j
@Service
public class TestImpl implements Test {

    @Autowired
    RedisUtils redisUtils;

    /**
     * @Description //定时任务实现类
     * @author Akalin
     * @Date 2019/12/30 9:57
     * @param
     * @return java.lang.String
     * @throws
     */
    @Override
    public void pullData(String url,String redisKey){
        try {
            //do something
        }catch (JSONException e){
            log.error("键值为："+redisKey+"数据解析失败！"+e.getMessage());
        }catch (Exception e){
            log.error("键值为："+redisKey+"连接请求出错！"+e.getMessage());
        }
        //do something
    }

}
