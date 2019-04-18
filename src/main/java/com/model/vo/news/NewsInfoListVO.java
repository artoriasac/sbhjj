package com.model.vo.news;

import io.swagger.annotations.ApiModelProperty;

public class NewsInfoListVO {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("简介")
    private String digest;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("缩略图")
    private String thumbnail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
