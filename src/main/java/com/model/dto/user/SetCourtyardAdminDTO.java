package com.model.dto.user;

public class SetCourtyardAdminDTO {
    private Integer userId;
    private Integer courtyardId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourtyardId() {
        return courtyardId;
    }

    public void setCourtyardId(Integer courtyardId) {
        this.courtyardId = courtyardId;
    }
}
