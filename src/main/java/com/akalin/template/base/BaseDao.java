package com.akalin.template.base;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

/**
 * @author Exrickx
 */
// 自定义接口 不会创建接口的实例 必须加此注解
public interface BaseDao<E, ID extends Serializable> extends BaseMapper<E> {
}
