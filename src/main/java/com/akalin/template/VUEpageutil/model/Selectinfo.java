package com.akalin.template.VUEpageutil.model;

import java.util.ArrayList;
import java.util.List;

public class Selectinfo {
    private String name;  //查询条件数据库字段名
    private String cname;  //查询条件字段中文名
    private int type;  //1为输入框,2为下拉框，3为日期框
    private List<SelectHelp> selectHelps = new ArrayList<>(); //查询条件的列表，如type传2，则需传


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private String model; //前端使用，不需要传


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }



    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Selectinfo(String name, String cname, int type) {
        this.name = name;
        this.cname = cname;
        this.type = type;
    }

    public List<SelectHelp> getSelectHelps() {
        return selectHelps;
    }

    public void setSelectHelps(List<SelectHelp> selectHelps) {
        this.selectHelps = selectHelps;
    }

    public void addSelectHelps(SelectHelp selectHelps) {
        this.selectHelps .add(selectHelps) ;
    }

    public Selectinfo() {
    }
}
