package com.model.dto.order;

import java.util.List;

public class AddOrderUserListDTO {
    private Integer orderId;
    private List<AddOrderUserDTO> list;

    public List<AddOrderUserDTO> getList() {
        return list;
    }

    public void setList(List<AddOrderUserDTO> list) {
        this.list = list;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
