package com.akalin.template.quartz.serviceimpl;


import com.akalin.template.quartz.dao.QuartzJobDao;
import com.akalin.template.quartz.entity.QuartzJob;
import com.akalin.template.quartz.service.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务接口实现
 * @author Exrick
 */
@Slf4j
@Service
//@Transactional
public class QuartzJobServiceImpl implements QuartzJobService {

    @Resource
    private QuartzJobDao quartzJobDao;

    @Override
    public QuartzJobDao getRepository() {
        return quartzJobDao;
    }

    @Override
    public List<QuartzJob> findByJobClassName(String jobClassName) {

        return quartzJobDao.findByJobClassName(jobClassName);
    }
}