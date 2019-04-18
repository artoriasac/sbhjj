package com.model.dto.news;

import io.swagger.annotations.ApiModelProperty;

public class AddNewsInfoVO {
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("简介")
    private String digest;
    @ApiModelProperty("分类")
    private Integer classifyId;
    @ApiModelProperty("缩略图")
    private String thumbnail;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("多媒体")

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
