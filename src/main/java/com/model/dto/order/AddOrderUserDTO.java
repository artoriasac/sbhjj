package com.model.dto.order;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AddOrderUserDTO {
    private Integer userId;
    @ApiModelProperty("职务id")
    private List<Integer> positionIds;

    public List<Integer> getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(List<Integer> positionIds) {
        this.positionIds = positionIds;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
