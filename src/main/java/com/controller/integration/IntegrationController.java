package com.controller.integration;

import com.annotation.Login;
import com.annotation.Logs;
import com.common.model.Result;
import com.model.vo.integration.IntegrationInfoVO;
import com.model.vo.integration.IntegrationListVO;
import com.service.inf.integration.IntegrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("integration")
@Api(tags = "积分")
public class IntegrationController {

    @Autowired
    private IntegrationService integrationService;

    @Logs
    @Login
    @GetMapping("integrationList")
    @ApiOperation("积分列表")
    public Result<List<IntegrationListVO>> integrationList(){
        List<IntegrationListVO> result=integrationService.integrationList();
        return new Result(result);
    }

    @Logs
    @Login
    @GetMapping("integrationInfo")
    @ApiOperation("积分详情")
    public Result<List<IntegrationInfoVO>> integrationInfo(@RequestParam Integer userId){
        List<IntegrationInfoVO> result=integrationService.integrationInfo(userId);
        return new Result(result);
    }

}
