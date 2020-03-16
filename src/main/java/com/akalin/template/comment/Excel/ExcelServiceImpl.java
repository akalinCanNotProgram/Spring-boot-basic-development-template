package com.akalin.template.comment.Excel;

import com.akalin.template.comment.Excel.ExcelDao;
import com.akalin.template.pojo.PageData;
import com.akalin.template.comment.Excel.ExcelService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName ExcelServiceImpl
 * @Description //TODO
 * @Author akalin
 * @Date 2019/7/5 14:46
 **/
@Slf4j
@Service("excelService")
public class ExcelServiceImpl implements ExcelService {
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
