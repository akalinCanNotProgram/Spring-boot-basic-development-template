package com.akalin.template.controller;

import com.akalin.template.utils.RedisUtils;
import com.akalin.template.utils.vo.Result;
import com.akalin.template.utils.vo.ResultUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * @ClassName TestController
 * @Description 测试类
 * @Date 2019年12月17日 13:51
 * @Version 1.0
 * @auther Akalin
 **/
@Slf4j
@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    RedisUtils redisUtils;

    @ApiOperation("Get请求")
    @GetMapping
    public Result get(@RequestParam(value = "param1",required = false,defaultValue = "2") int totleRate,
                                             @RequestParam(value = "param2",required = false,defaultValue = "2") int singleRate){

        try{

                //do something

        }catch (Exception e){
            e.printStackTrace();
            return new ResultUtil().setErrorMsg("Get请求出现异常！！异常类型："+e.getMessage());
        }
        return new ResultUtil().setData(null,"get请求成功！！");
    }

    @ApiOperation("Post请求")
    @PostMapping
    public Result post(@RequestParam(value = "totleRate",required = false,defaultValue = "2") int totleRate,
                                             @RequestParam(value = "singleRate",required = false,defaultValue = "2") int singleRate) {
        try{

            //do something

        }catch (Exception e){
            e.printStackTrace();
            return new ResultUtil().setErrorMsg("Post请求出现异常！！异常类型："+e.getMessage());
        }
        return new ResultUtil().setData(null,"get请求成功！！");
    }
    @ApiOperation("Delete请求")
    @DeleteMapping
    public Result delete(@RequestParam(value = "totleRate",required = false,defaultValue = "2") int totleRate,
                         @RequestParam(value = "singleRate",required = false,defaultValue = "2") int singleRate){
        //do something
        return new ResultUtil().setErrorMsg("Delete请求失败！！");
    }

    @ApiOperation("put请求")
    @PutMapping
    public Result put(@RequestParam(value = "totleRate",required = false,defaultValue = "2") int totleRate,
                         @RequestParam(value = "singleRate",required = false,defaultValue = "2") int singleRate){
        //do something
        return new ResultUtil().setErrorMsg(200,"put请求失败！！");
    }
}
