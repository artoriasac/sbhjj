package com.controller.test;

import com.annotation.Test;
import com.common.model.Result;
import com.service.inf.test.TestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@Api(tags = "sbhjj")
public class TestController {

    @Autowired
    private TestService testService;

    @Test
    @GetMapping("test")
    public Result<String> test(@RequestParam String c){

        return new Result(testService.test(c));
    }
}
