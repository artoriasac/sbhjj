package com.model.dto.order;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AddOrderDTO {
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("需求")
    private String content;
    @ApiModelProperty("等级")
    private Integer rank;
    @ApiModelProperty("预计花费时间（天）")
    private Integer scheduledTime;
    @ApiModelProperty("附件")
    private List<OrderAccessoryDTO> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Integer scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public List<OrderAccessoryDTO> getList() {
        return list;
    }

    public void setList(List<OrderAccessoryDTO> list) {
        this.list = list;
    }
}
