package com.akalin.template.service;

import com.akalin.template.pojo.PageData;
import com.github.pagehelper.Page;

/**
 * @InerfaceName Test
 * @Description 测试服务
 * @Date 2019年12月30日 8:55
 * @Version 1.0
 * @auther Akalin
 **/
public interface Test {

    /**
     * @Description //TODO
     * @author Akalin
     * @Date 2020/3/14 22:39
     * @return void
     * @throws
     */
    void pullData(String url,String redisKey);

    Page<PageData> test();
}
