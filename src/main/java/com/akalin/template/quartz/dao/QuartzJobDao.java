package com.akalin.template.quartz.dao;

import com.akalin.template.base.BaseDao;
import com.akalin.template.quartz.entity.QuartzJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 定时任务数据处理层
 * @author Exrick
 */
@Mapper
public interface QuartzJobDao extends BaseDao<QuartzJob, String> {

    /**
     * 通过类名获取
     * @param jobClassName
     * @return
     */
    @Select("select * from t_quartz_job where job_class_name = #{jobClassName}")
    List<QuartzJob> findByJobClassName(String jobClassName);
}