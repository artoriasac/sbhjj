package com.model.dto.user;

import io.swagger.annotations.ApiModelProperty;

public class AddCourtyardDTO {
    @ApiModelProperty("院名称")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
