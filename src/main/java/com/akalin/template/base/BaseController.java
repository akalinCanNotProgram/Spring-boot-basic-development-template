package com.akalin.template.base;


import cn.hutool.core.util.PageUtil;
import com.akalin.template.comment.utils.ResultUtil;
import com.akalin.template.comment.vo.PageVo;
import com.akalin.template.comment.vo.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
/**
 * @author Exrickx
 */
public abstract class BaseController<E, ID extends Serializable> {

    /**
     * 获取service
     * @return
     */
    @Autowired
    public abstract BaseService<E,ID> getService();

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "通过id获取")
    public Result<E> get(@PathVariable ID id){

        E entity = getService().get(id);
        return new ResultUtil<E>().setData(entity);
    }


//    @RequestMapping(value = "/getByPage",method = RequestMethod.GET)
//    @ResponseBody
//    @ApiOperation(value = "分页获取")
//    public Result<PageInfo> getByPage(PageVo page){
//
//        PageHelper.startPage(page.getPageNumber(), page.getPageSize(),page.getOrder());
//        Page<E> data = getService().getAll();
//        PageInfo pageInfo = new PageInfo(data);
//        return new ResultUtil<PageInfo>().setData(pageInfo);
//    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存数据")
    public Result<E> save(E entity){

        int e = getService().save(entity);
        return new ResultUtil<E>().setData(entity,"保存成功！");
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新数据")
    public Result<E> update(E entity){

        int e = getService().update(entity);
        return new ResultUtil<E>().setData(entity,"更新成功！");
    }

    @RequestMapping(value = "/delByIds/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delAllByIds(@PathVariable ID[] ids){

        for(ID id:ids){
            getService().delete(id);
        }
        return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
    }
}
