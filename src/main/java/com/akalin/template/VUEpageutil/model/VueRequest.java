package com.akalin.template.VUEpageutil.model;

import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

public class VueRequest<T> {

    private String title; //表格名，显示在前端的标题
    private PageInfo PageInfo;  //分页的数据集合
    private List<Header> header = new ArrayList<>();  //表头集合
    private List<Selectinfo> selectinfos = new ArrayList<>();  //查询条件集合 为null则为不生成查询框


    //以下暂未使用 2019年8月30日13:59:28
    //已开放 2019年8月31日09:28:26
    private List<Selectinfo> inputinfos = new ArrayList<>(); //新增和修改对话框中，每个字段对应的输入框类型，如开启添加或修改操作，则此必填，结构参考‘查询条件集合’
    private Boolean canInsert = false; //是否需要新增操作，默认不生成，如果需要则重写insert方法
    private Boolean canDelete = false;//是否需要删除操作，默认不生成，如果需要则重写delete方法
    private Boolean canUpdate = false;//是否需要修改操作，默认不生成，如果需要则重写update方法
    private Boolean canExcel = false; //是否需要导出Excel操作，默认不生成，如果需要则重写toExcel方法


    public PageInfo getPageInfo() {
        return PageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        PageInfo = pageInfo;
    }

    public List<Selectinfo> getSelectinfos() {
        return selectinfos;
    }

    public void addSelectinfos(Selectinfo selectinfo) {
        this.selectinfos.add(selectinfo);
    }

    public void addInputinfo(Selectinfo inputinfo) {
        this.inputinfos.add(inputinfo);
    }

    public void addHeader(Header header) {
        this.header.add(header);
    }

    public void setSelectinfos(List<Selectinfo> selectinfos) {
        this.selectinfos = selectinfos;
    }

    public List<Header> getHeader() {
        return header;
    }

    public void setHeader(List<Header> header) {
        this.header = header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Selectinfo> getInputinfos() {
        return inputinfos;
    }

    public void setInputinfos(List<Selectinfo> inputinfos) {
        this.inputinfos = inputinfos;
    }

    public Boolean getCanInsert() {
        return canInsert;
    }

    public void setCanInsert(Boolean canInsert) {
        this.canInsert = canInsert;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }

    public Boolean getCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(Boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public VueRequest(String title) {
        this.title = title;
    }

    public List<Selectinfo> getInputinfo() {
        return inputinfos;
    }

    public void setInputinfo(List<Selectinfo> inputinfo) {
        this.inputinfos = inputinfo;
    }

    public Boolean getCanExcel() {
        return canExcel;
    }

    public void setCanExcel(Boolean canExcel) {
        this.canExcel = canExcel;
    }
    public void openAllpower() {
        this.canUpdate = true;
        this.canInsert = true;
        this.canDelete = true;
        this.canExcel = true;
    }

    public VueRequest() {
    }
}
