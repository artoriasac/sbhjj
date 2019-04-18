package com.model.dto.user;

import io.swagger.annotations.ApiModelProperty;

public class CourtyardUserDTO {
    @ApiModelProperty("用户id")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}
