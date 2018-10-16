package com.lcy.cssm.common.web.wechat;

/**
 * @Author: lcy
 * @Date: 2018/6/25 09:49
 * @Description:
 */
public class TemplateParam {


    private String name;

    private String value;

    private String color;

    public TemplateParam(String name,String value,String color){
        this.name=name;
        this.value=value;
        this.color=color;
    }

    public TemplateParam(String name,String value){
        this.name=name;
        this.value=value;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
