package com.model.vo.order;

import java.util.List;

public class OrderInfoVO {
    private Integer id;
    private String title;
    private String rank;
    private String createUser;
    private Integer createUserId;
    private String type;
    private String content;
    private List<AccessoryVO> accessoryList;
    private List<OrderUserVO> orderUserList;
    private Integer typeId;

    public List<OrderUserVO> getOrderUserList() {
        return orderUserList;
    }

    public void setOrderUserList(List<OrderUserVO> orderUserList) {
        this.orderUserList = orderUserList;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<AccessoryVO> getAccessoryList() {
        return accessoryList;
    }

    public void setAccessoryList(List<AccessoryVO> accessoryList) {
        this.accessoryList = accessoryList;
    }
}
