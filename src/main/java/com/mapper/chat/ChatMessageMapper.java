package com.mapper.chat;

import com.model.entity.chat.ChatMessage;
import com.model.vo.chat.ChatInfoListVO;
import com.model.vo.chat.ChatListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChatMessage record);

    int insertSelective(ChatMessage record);

    ChatMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChatMessage record);

    int updateByPrimaryKey(ChatMessage record);

    List<ChatListVO> chatList(@Param("id") Integer id);

    List<ChatInfoListVO> chatInfoList(@Param("thisUserId") Integer thisUserId,@Param("otherUserId") Integer otherUserId);
}