package com.akalin.template.comment.Excel;

import com.akalin.template.pojo.PageData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName ExcelDao
 * @Description excel相关操作接口
 * @Author akalin
 * @Date 2019/7/5 10:20
 **/
@Mapper
public interface ExcelDao {

    //根据sql动态获取结果集
    @Select("${sql}")
    List<LinkedHashMap> getExcelList(PageData pd);
    //获取总记录数
    @Select("${countsql}")
    Integer getExcelCount(PageData pd);
    //插入
    @Insert("insert into sapinfo (id, sell_code, phone_num) values (#{id}, #{store_code}, #{state})")
    Integer insertExcel(PageData pd);
}
