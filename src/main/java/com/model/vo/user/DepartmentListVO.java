package com.model.vo.user;

import io.swagger.annotations.ApiModelProperty;

public class DepartmentListVO {
    private Integer id;
    @ApiModelProperty("名称")
    private Integer content;
    @ApiModelProperty("管理员id")
    private Integer adminId;
    @ApiModelProperty("管理员名")
    private String adminName;
    @ApiModelProperty("创建时间")
    private String createTime;
    private Integer courtyardId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCourtyardId() {
        return courtyardId;
    }

    public void setCourtyardId(Integer courtyardId) {
        this.courtyardId = courtyardId;
    }
}
