package com.controller.user;

import com.annotation.Authority;
import com.annotation.Login;
import com.annotation.Logs;
import com.common.model.MemberInfo;
import com.common.model.Result;
import com.model.dto.user.*;
import com.model.vo.position.PositionListVO;
import com.model.vo.user.CourtyardListVO;
import com.model.vo.user.IdCardCheckVO;
import com.model.vo.user.UserListVO;
import com.service.inf.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@Api(tags = "用户")
public class UserController {
    @Autowired
    private UserService userService;

    @Logs
    @Login
    @ApiOperation("身份证，姓名识别(大概还能试用几次)")
    @GetMapping("idCardCheck")
    public Result<IdCardCheckVO> idCardCheck(@ApiParam("真实姓名") @RequestParam String realName,
                                       @ApiParam("身份证号")@RequestParam String idCard){
        IdCardCheckVO result=userService.idCardCheck(realName,idCard);
        return new Result(result);
    }

    @Logs
    @Login
    @ApiOperation("注册")
    @PostMapping("signUp")
    public Result signUp(@RequestBody SignUpDTO signUpDTO)throws Exception{
       userService.signUp(signUpDTO);
        return new Result();
    }

    @Logs
    @Login
    @ApiOperation("登录")
    @PostMapping("signIn")
    public Result<MemberInfo> signIn(@RequestBody SignInDTO signInDTO)throws Exception{
        MemberInfo result=userService.signIn(signInDTO);
        return new Result(result);
    }

    @Logs
    @Login
    @ApiOperation("修改")
    @PostMapping("updateUser")
    public Result<MemberInfo> updateUser(@RequestBody UpdateUserDTO updateUserDTO)throws Exception{
        userService.updateUser(updateUserDTO);
        return new Result();
    }

    @Logs
    @Login
    @Authority(1)
    @ApiOperation("创建院")
    @PostMapping("addCourtyard")
    public Result addCourtyard(@RequestBody AddCourtyardDTO addCourtyardDTO)throws Exception{
        userService.addCourtyard(addCourtyardDTO);
        return new Result();
    }

    @Logs
    @Login
    @Authority(1)
    @ApiOperation("设置院长")
    @PostMapping("setCourtyardAdmin")
    public Result setCourtyardAdmin(@RequestBody SetCourtyardAdminDTO setCourtyardAdminDTO)throws Exception{
        userService.setCourtyardAdmin(setCourtyardAdminDTO);
        return new Result();
    }

    @Logs
    @Login
    @ApiOperation("院列表")
    @GetMapping("courtyardList")
    public Result<List<CourtyardListVO>> courtyardList()throws Exception{
        List<CourtyardListVO> result=userService.courtyardList();
        return new Result(result);
    }

    @Logs
    @Login
    @ApiOperation("添加院成员")
    @PostMapping("addCourtyardUser")
    public Result addCourtyardUser(@RequestBody AddCourtyardUserDTO addCourtyardUserDTO)throws Exception{
        userService.addCourtyardUser(addCourtyardUserDTO);
        return new Result();
    }

    @Logs
    @Login
    @ApiOperation("员工列表")
    @GetMapping("userList")
    public Result<List<UserListVO>> userList(@ApiParam("昵称")@RequestParam(required = false) String nickname)throws Exception{
        List<UserListVO> result=userService.userList(nickname);
        return new Result(result);
    }

    @Logs
    @Login
    @ApiOperation("添加职位")
    @PostMapping("addPosition")
    public Result addPosition(@RequestBody AddPositionListDTO addpositionListDTO)throws Exception{
        userService.addPosition(addpositionListDTO);
        return new Result();
    }

    @Logs
    @Login
    @ApiOperation("查看用户职位")
    @GetMapping("userPositionList")
    public Result<List<PositionListVO>> userPositionList(@RequestParam Integer userId)throws Exception{
        List<PositionListVO> result=userService.userPositionList(userId);
        return new Result(result);
    }
}
