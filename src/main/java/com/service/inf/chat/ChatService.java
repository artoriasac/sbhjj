package com.service.inf.chat;

import javax.websocket.Session;

public interface ChatService {
    void open(Session session, String userId);

    void close(Session session, String userId);

    void message(Session session, String userId, String message)throws Exception;

    Boolean isClientWebSocket(String userId);
}
