package com.model.dto.user;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AddCourtyardUserDTO {

    List<CourtyardUserDTO> list;
    @ApiModelProperty("组id")
    private Integer courtyardId;
    public List<CourtyardUserDTO> getList() {
        return list;
    }

    public void setList(List<CourtyardUserDTO> list) {
        this.list = list;
    }

    public Integer getCourtyardId() {
        return courtyardId;
    }

    public void setCourtyardId(Integer courtyardId) {
        this.courtyardId = courtyardId;
    }
}
