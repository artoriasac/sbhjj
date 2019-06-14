package com.service.inf.chat;

import com.common.model.DataInfo;
import com.model.vo.chat.ChatInfoListVO;
import com.model.vo.chat.ChatListVO;

import javax.websocket.Session;
import java.util.List;

public interface ChatService {
    void open(Session session, String userId);

    void close(Session session, String userId);

    void message(Session session, String userId, String message)throws Exception;

    Boolean isClientWebSocket(String userId);

    List<ChatListVO> chatList();

    DataInfo<ChatInfoListVO> chatInfoList(Integer userId,Integer page,Integer pageSize);
}
