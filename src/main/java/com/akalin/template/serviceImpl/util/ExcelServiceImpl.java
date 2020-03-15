package com.akalin.template.serviceImpl.util;

import com.akalin.template.dao.util.ExcelDao;
import com.akalin.template.pojo.PageData;
import com.akalin.template.service.util.ExcelService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName ExcelServiceImpl
 * @Description //TODO
 * @Author dusc
 * @Date 2019/7/5 14:46
 **/
@Service("excelService")
public class ExcelServiceImpl implements ExcelService {
//    private Log log = LogFactory.getLog(this.getClass());

    @Resource
    private ExcelDao excelDao;


    @Override
    public List<LinkedHashMap> getExcelList(PageData pd) {
        List<LinkedHashMap> excelList = excelDao.getExcelList(pd);
        return excelList;
    }

    @Override
    public Integer getExcelCount(PageData pd) {
        return excelDao.getExcelCount(pd);
    }

    @Override
    public JSONObject getJSONObject(JSONArray jsonArray) {
        JSONObject object = null;
        if (!jsonArray.isEmpty()){
            object = jsonArray.getJSONObject(0);
        }
        return object;
    }
}
