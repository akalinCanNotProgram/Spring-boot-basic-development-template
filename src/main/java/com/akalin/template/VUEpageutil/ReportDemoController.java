package com.akalin.template.VUEpageutil;

import com.akalin.template.VUEpageutil.model.Header;
import com.akalin.template.VUEpageutil.model.SelectHelp;
import com.akalin.template.VUEpageutil.model.Selectinfo;
import com.akalin.template.VUEpageutil.model.VueRequest;
import com.akalin.template.VUEpageutil.pojo.Task;
import com.akalin.template.VUEpageutil.service.TaskService;
import com.akalin.template.utils.ExcelUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用报表测试
 * LZQ 2019年8月30日15:00:48
 */
@RestController
@RequestMapping("/reportdemo")
@CrossOrigin(origins = "*")
public class ReportDemoController implements Report<Task> {


//    @Autowired
//    private TaskService taskService;

    @Autowired
    private ExcelUtils excelUtils;


    @Override
    public VueRequest findall(int pageNum, int pageSize, String empid) throws Exception {
        try {
            Map map = new HashMap();
            map.put("empid",empid);
            VueRequest vueRequest = new VueRequest("任务单下达报表");
            vueRequest.setPageInfo(findbyitem(pageNum,pageSize,map));
           vueRequest.openAllpower();
            /**
             * 构造表头
             */
            vueRequest.addHeader(new Header("taskid", "任务编号"));
            vueRequest.addHeader(new Header("tasktarget", "任务目标"));
            vueRequest.addHeader(new Header("taskmanid", "任务负责人编号"));
            vueRequest.addHeader(new Header("applicantid", "申请人编号"));
            /**
             * 组装查询数据，本次两个输入框查询条件，一个下拉框查询条件，一个日期框查询条件5
             */
            //输入框查询
            vueRequest.addSelectinfos(new Selectinfo("taskid", "测试输入框", 1));

            vueRequest.addSelectinfos(new Selectinfo("tasktarget", "测试输入框2", 1));
            //下拉框查询
            Selectinfo selectinfo = new Selectinfo("test", "测试下拉框", 2);
            selectinfo.addSelectHelps(new SelectHelp("111", "下拉条件1"));
            selectinfo.addSelectHelps(new SelectHelp("222", "下拉条件2"));
            vueRequest.addSelectinfos(selectinfo);
            //日期框查询
            Selectinfo selectinfo2 = new Selectinfo("testdate", "测试日期框", 3);
            vueRequest.addSelectinfos(selectinfo2);
            /**
             * 构造修改添加框
             */
            vueRequest.setInputinfo(vueRequest.getSelectinfos());
            return vueRequest;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PageInfo findbyitem(int pageNum, int pageSize, Map selectitem) throws Exception {
        try {
            //组装分页数据
            System.out.println(selectitem.toString());
            PageHelper.startPage(pageNum, pageSize);
//            Page<Task> tasklist = taskService.findAll();
            Page<Task> tasklist = null;
            PageInfo pageInfo = new PageInfo(tasklist);
            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int update(Task task) throws Exception {

        return 1;
    }


    @Override
    public int insert(Task task) throws Exception {
        System.out.println(task.getTaskid()+"添加");
        return 0;
    }

    @Override
    public int delete(Task task) throws Exception {
        System.out.println(task.getTaskid());
        return 0;
    }

    @Override
    public void toExcel(HttpServletResponse response) {
        String excelname = "";
        String sql = "";
        excelname = "Excel名";
        sql = "select * from uf_mescode";
        excelUtils.exportExcel(sql, excelname, response);
    }
}
