package com.model.dto.word;

import io.swagger.annotations.ApiModelProperty;

public class AddWordInfoDTO {
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("url")
    private String url;
    @ApiModelProperty("备注")
    private String mark;
    @ApiModelProperty("分类id")
    private Integer classifyId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }
}
