package com.model.entity.user;

import java.util.Date;

public class Department {
    private Integer id;

    private String content;

    private Integer courtyardId;

    private Integer state;

    private Date createTime;

    private Integer adminId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getCourtyardId() {
        return courtyardId;
    }

    public void setCourtyardId(Integer courtyardId) {
        this.courtyardId = courtyardId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}