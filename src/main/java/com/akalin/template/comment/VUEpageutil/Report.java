package com.akalin.template.comment.VUEpageutil;


import com.akalin.template.comment.VUEpageutil.model.VueRequest;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**LZQ 2019年8月30日15:01:07
 * VUE前端增删改查对应接口，Controller继承后类名上打@RequestMapping注解配置基础路径，方法名上勿打@RequestMapping注解
 * * @Author LZQ
 * * @param <T> 传入操作对象 影响增删改操作
 */

public interface Report<T> {

    /**
     * 前端页面初始化请求接口,实现后无需重写路径
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param empid 工号
     * @return 页面所需数据
     * @throws Exception
     */
    @ApiOperation("前端页面初始化请求接口")
    @GetMapping()
    VueRequest findall(@RequestParam(value = "pagenum") int pageNum,
                       @RequestParam(value = "pagesize") int pageSize, @RequestParam(value = "empid") String empid) throws Exception;


    /**\
     * 前端搜索和翻页调用的接口,实现后无需重写路径
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param selectitem 查询条件,与findall()中传输的条件一致
     * @return 分页数据
     * @throws Exception
     */
    @ApiOperation("前端搜索和翻页调用的接口")
    @PostMapping("/item")
    PageInfo findbyitem(@RequestParam(value = "pagenum") int pageNum, @RequestParam(value = "pagesize") int pageSize, @RequestBody Map selectitem) throws Exception;

    /**
     * 前端提交修改调用的接口,实现后无需重写路径
     * @param object 操作对象
     * @return 是否成功，返回1或0
     * @throws Exception
     */
    @ApiOperation("前端提交新增调用的接口")
    @PutMapping()
    int update (@RequestBody T object) throws Exception;

    /**
     * 前端提交新增调用的接口,实现后无需重写路径
     * @param object 操作对象
     * @return 是否成功，返回1或0
     * @throws Exception
     */
    @ApiOperation("前端提交新增调用的接口")
    @PostMapping()
    int  insert (@RequestBody T object) throws Exception;

    /**
     * 前端提交删除调用的接口,实现后无需重写路径
     * @param object 操作对象
     * @return 是否成功，返回1或0
     * @throws Exception
     */
    @ApiOperation("前端提交删除调用的接口")
    @DeleteMapping()
    int delete (@RequestBody T object) throws Exception;

    /**
     * 导出Excel方法
     * @param response
     */
    @ApiOperation("导出Excel接口")
    @GetMapping("/excel")
    void toExcel(HttpServletResponse response);

}
