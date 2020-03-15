package com.akalin.template.service.util;

import com.akalin.template.pojo.PageData;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName ExcelService
 * @Description
 * @Author dusc
 * @Date 2019/7/5 14:45
 **/
public interface ExcelService {
    //根据sql动态获取结果集
    List<LinkedHashMap> getExcelList(PageData pd);
    //获取总记录数
    Integer getExcelCount(PageData pd);
    //获取前台传过来的json对象
    JSONObject getJSONObject(JSONArray jsonArray);
}
