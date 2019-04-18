package com.model.vo.order;

import io.swagger.annotations.ApiModelProperty;

public class OrderUserVO {
    private Integer userId;
    private String nickname;
    private String headUrl;
    @ApiModelProperty("0为接工单的人，1为成员")
    private Integer type;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
