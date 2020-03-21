package com.akalin.template.dao;

import com.akalin.template.base.BaseDao;
import com.akalin.template.pojo.po.Log;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InerfaceName LogDao
 * @Description TODO
 * @Date 2020年03月21日 17:29
 * @Version 1.0
 * @auther Akalin
 **/
@Mapper
public interface LogDao extends BaseDao<Log, ID> {
}
