package com.skh.hrm.domain;

import java.io.Serializable;

/**
 * Created by skh on 2018/8/6.
 * 职位实体类
 */
public class Job implements Serializable {
    private Integer id;
    private String name;    // 职位名称
    private String remark;  // 详细描述

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
