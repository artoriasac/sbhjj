package com.model.vo.chat;

import lombok.Data;

@Data
public class ChatListVO {
    private Integer  otherUserId;
    private String otherUserNickname;
    private  String content;
    private String createTime;
}
