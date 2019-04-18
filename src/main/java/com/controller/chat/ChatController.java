package com.controller.chat;

import com.annotation.Login;
import com.annotation.Logs;
import com.common.model.Result;
import com.service.inf.chat.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("chat")
@Api(tags = "聊天")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Logs
    @Login
    @ApiOperation("是否连接了webSocket")
    @GetMapping("isClientWebSocket")
    public Result<Boolean> isClientWebSocket(@ApiParam("用户id") @RequestParam String userId){
        Boolean result=chatService.isClientWebSocket(userId);
        return new Result(result);
    }
}
