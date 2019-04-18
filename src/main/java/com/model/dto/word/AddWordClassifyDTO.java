package com.model.dto.word;

import io.swagger.annotations.ApiModelProperty;

public class AddWordClassifyDTO {
    @ApiModelProperty("标题")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
