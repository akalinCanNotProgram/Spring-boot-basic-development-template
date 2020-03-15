package com.akalin.template.VUEpageutil.service;

import com.akalin.template.VUEpageutil.pojo.Task;
import com.github.pagehelper.Page;

/**
 * @InerfaceName TaskService
 * @Description TODO
 * @Date 2020年03月15日 0:26
 * @Version 1.0
 * @auther Akalin
 **/
public interface TaskService {
    Page<Task> findAll() throws Exception;
}
