package com.akalin.template.dao;

import com.akalin.template.base.BaseDao;
import com.akalin.template.pojo.PageData;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @InerfaceName TestDao
 * @Description TODO
 * @Date 2020年03月16日 22:07
 * @Version 1.0
 * @auther Akalin
 **/
@Mapper
public interface TestDao extends BaseDao<PageData,Integer> {

    @Select("select * from t_user")
    Page<PageData> getIpData();
}
