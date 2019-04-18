package com.model.dto.fodder;

import io.swagger.annotations.ApiModelProperty;

public class AddFodderInfoDTO {
    @ApiModelProperty("分类id")
    private Integer classifyId;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("url")
    private String url;
    @ApiModelProperty("类型（文件后缀，全部转化为小写在传，不然打断你的屌）")
    private String type;

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
