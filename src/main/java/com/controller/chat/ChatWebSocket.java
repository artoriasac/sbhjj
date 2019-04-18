package com.controller.chat;

import com.common.configuration.MyEndpointConfigure;
import com.service.inf.chat.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@RestController
@Api(tags = "聊天webSocket部分")
@ServerEndpoint(value = "/chatWebSocket/{userId}",configurator = MyEndpointConfigure.class)
public class ChatWebSocket {
    @Autowired
    private ChatService chatService;

    @ApiOperation("连接")
    @OnOpen
    public void open(@ApiParam("用户id") @PathParam("userId") String userId, Session session){
//        if (request!=null){
//            Object member_info = request.getSession().getAttribute("MEMBER_INFO");
//            System.out.println(member_info.toString());
//        }
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
