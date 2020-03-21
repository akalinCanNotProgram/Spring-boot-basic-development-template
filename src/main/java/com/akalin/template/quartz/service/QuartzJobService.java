package com.akalin.template.quartz.service;

import com.akalin.template.base.BaseService;
import com.akalin.template.quartz.entity.QuartzJob;

import java.util.List;

/**
 * 定时任务接口
 * @author Exrick
 */
public interface QuartzJobService extends BaseService<QuartzJob, String> {

    /**
     * 通过类名获取
     * @param jobClassName
     * @return
     */
    List<QuartzJob> findByJobClassName(String jobClassName);
}