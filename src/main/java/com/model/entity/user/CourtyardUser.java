package com.model.entity.user;

import java.util.Date;

public class CourtyardUser {
    private Integer id;

    private Integer courtyardId;

    private Integer userId;

    private Integer type;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourtyardId() {
        return courtyardId;
    }

    public void setCourtyardId(Integer courtyardId) {
        this.courtyardId = courtyardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}