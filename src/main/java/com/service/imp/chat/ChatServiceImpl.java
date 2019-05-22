package com.service.imp.chat;

import com.common.model.Message;
import com.common.model.ServiceException;
import com.common.utils.DateFormatUtil;
import com.common.utils.JSONUtils;
import com.mapper.chat.ChatMessageMapper;
import com.mapper.user.UserMapper;
import com.model.entity.chat.ChatMessage;
import com.model.entity.user.User;
import com.service.inf.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;

import javax.websocket.Session;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional(rollbackFor = Exception.class)
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMessageMapper chatMessageMapper;

    @Autowired
    private UserMapper userMapper;

    private final static Map<String,Session> sockets=new ConcurrentHashMap<>();

    private final static String WEB_SOCKET="webSocket";

    private final static Integer NOT_READ=0;
    private final static Integer READ=1;

    @Override
    public void open(Session session, String userId) {
//        User user = userMapper.selectByPrimaryKey(Integer.valueOf(userId));
//        if (user==null){
//            throw new ServiceException("用户不存在");
//        }
        sockets.put(WEB_SOCKET+userId,session);
    }

    @Override
    public void close(Session session, String userId) {
        Session socket = sockets.get(WEB_SOCKET+userId);
        if (socket!=null){
            sockets.remove(WEB_SOCKET+userId);
        }
    }


    @Override
    public void message(Session session, String userId, String message)throws Exception {
        Date date = new Date(System.currentTimeMillis());

        Message m = JSONUtils.jsonToObj(message, Message.class);
        m.setSendUser(userId);
        m.setDate(DateFormatUtil.formatDate(date,DateFormatUtil.FORMAT));

        ChatMessage chatMessage=new ChatMessage();
        chatMessage.setContent(m.getContent());
        chatMessage.setSendUser(Integer.valueOf(userId));
        chatMessage.setFromUser(Integer.valueOf(m.getFromUser()));
        chatMessage.setCreateTime(date);
        chatMessage.setReadType(NOT_READ);
        chatMessageMapper.insert(chatMessage);

        if (!isClientWebSocket(WEB_SOCKET + m.getFromUser())){
            return;
        }
        Session fromSession = sockets.get(WEB_SOCKET + m.getFromUser());
        fromSession.getBasicRemote().sendText(JSONUtils.objToJson(m));
    }

    @Override
    public Boolean isClientWebSocket(String userId) {
        if (userId==null){
            return false;
        }
       return sockets.containsKey(WEB_SOCKET+userId);

    }
}
