package com.lcy.cssm.common.base.po;

import java.io.Serializable;
import java.util.Date;


/**
 * 数据库基础属性
 *
 * @author lcy
 * @create 2017-04-21 23:36
 **/
public class BasePO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date createTime;
    private Date updateTime;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
