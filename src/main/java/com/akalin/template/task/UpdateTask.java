package com.akalin.template.task;


import com.akalin.template.service.Test;
import com.akalin.template.comment.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName UpdateTask
 * @Description TODO
 * @Date 2019年12月18日 8:56
 * @Version 1.0
 * @auther Akalin
 **/
@Slf4j
@PropertySource("classpath:url.properties")
@EnableScheduling  //开启定时任务
@EnableAsync   //开启异步任务
@Component
public class UpdateTask {

    @Value("${com.url1}")
    private String url1;

    @Value("${com.url2}")
    private String url2;

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    Test test;

    /**
     * @Description //定时任务 2秒执行一次
     * @author Akalin
     * @Date 2019/12/18 9:05
     * @return void
     */
    @Async
    @Scheduled(cron = "0/2 * * * * ?")
    public void mattress2floorUpData(){
        test.pullData(url1,"url1");
    }

}
