package com.skh.hrm.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by skh on 2018/8/6.
 * 通知实体类
 */
public class Notice implements Serializable {
    private Integer id;
    private String title;         // 标题
    private String content;       // 内容
    private Date createDate;      // 创建日期
    private User user;            // 发布者

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
