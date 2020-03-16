package com.akalin.template.comment.VUEpageutil.model;

/**
 * 查询下拉框集合
 */
public class SelectHelp {
    private String key; //数据库对应的id字段  如 1
    private String lable; //数据库对应的字段显示名 如 开发组

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public SelectHelp() {
    }

    public SelectHelp(String key, String lable) {
        this.key = key;
        this.lable = lable;
    }
}
