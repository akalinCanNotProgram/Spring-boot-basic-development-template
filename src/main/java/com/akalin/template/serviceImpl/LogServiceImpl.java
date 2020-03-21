package com.akalin.template.serviceImpl;

import com.akalin.template.dao.LogDao;
import com.akalin.template.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName LogServiceImpl
 * @Description TODO
 * @Date 2020年03月21日 17:24
 * @Version 1.0
 * @auther Akalin
 **/
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogDao logDao;

    @Override
    public LogDao getRepository() {
        return logDao;
    }
}
