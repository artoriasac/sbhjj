package com.controller.chat;

import com.service.inf.chat.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@RestController
@Api(tags = "聊天webSocket部分")
@ServerEndpoint(value = "/chatWebSocket/{userId}")
@Order
public class ChatWebSocket  {

    public static void setApplicationContext(ApplicationContext applicationContext)  {
        ChatWebSocket.applicationContext=applicationContext;
    }

    private static ApplicationContext applicationContext;
    private ChatService chatService;


    @ApiOperation("连接")
    @OnOpen
    public void open(@ApiParam("用户id") @PathParam("userId") String userId, Session session){
        chatService=applicationContext.getBean(ChatService.class);
        if (session==null||userId==null){
            return;
        }
        chatService.open(session,userId);
    }

    @ApiOperation("关闭连接")
    @OnClose
    public void close(@ApiParam("用户id") @PathParam("userId") String userId,Session session){
        if (session==null||userId==null){
            return;
        }
        chatService.close(session,userId);
    }

    @ApiOperation("发送消息")
    @OnMessage
    public void message(@ApiParam("用户id") @PathParam("userId") String userId,Session session,String message)throws Exception{
        if (session==null||userId==null||message==null){
            return;
        }
        chatService.message(session,userId,message);
    }
}
