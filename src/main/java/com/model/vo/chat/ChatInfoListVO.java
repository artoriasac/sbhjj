package com.model.vo.chat;

import lombok.Data;

@Data
public class ChatInfoListVO {
    private Integer id;
    private Integer sendUser;
    private Integer fromUser;
    private String content;
    private String createTime;
    private String sendNickname;
}
