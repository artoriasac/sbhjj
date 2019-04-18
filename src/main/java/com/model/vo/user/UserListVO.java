package com.model.vo.user;

import io.swagger.annotations.ApiModelProperty;

public class UserListVO {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("简介")
    private String mark;
    @ApiModelProperty("头像url")
    private String headUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
