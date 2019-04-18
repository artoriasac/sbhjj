package com.model.vo.word;

import io.swagger.annotations.ApiModelProperty;

public class WordClassifyListVO {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("标题")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
