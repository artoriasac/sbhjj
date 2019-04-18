package com.model.dto.order;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class DrawOrderDTO {
    private Integer orderId;
    @ApiModelProperty("职位列表")
    private List<Integer> positionIds;

    public List<Integer> getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(List<Integer> positionIds) {
        this.positionIds = positionIds;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
