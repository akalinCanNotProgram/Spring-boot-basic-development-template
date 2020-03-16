package com.akalin.template.comment.VUEpageutil.model;

public class Header {
    private String title; //中文名
    private String slot; //对应数据库字段名


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public Header( String slot,String title) {
        this.title = title;
        this.slot = slot;
    }
}
