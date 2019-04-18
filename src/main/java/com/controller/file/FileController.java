package com.controller.file;

import com.annotation.Authority;
import com.annotation.Logs;
import com.common.model.Result;
import com.model.vo.file.TokenVO;
import com.service.inf.file.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("file")
@Api(tags = "文件")
public class FileController {

    @Autowired
    private FileService fileService;

    @Logs
    @ApiOperation("获取token")
    @GetMapping("getToken")
    public Result<TokenVO> getToken(){
        TokenVO token = fileService.getToken();
        return new Result(token);
    }

    @Logs
    @ApiOperation("二维码")
    @GetMapping("getQrCode")
    public Result<String> getQrCode(@ApiParam("需要转化的字符串") @RequestParam String qrCode){
        String url=fileService.getQrCode(qrCode);
        return new Result(url);
    }

    @Logs
    @ApiOperation("条形码")
    @GetMapping("getBarcode")
    public Result<String> getBarcode(@ApiParam("需要转化的字符串") @RequestParam String barcode){
        String url=fileService.getBarcode(barcode);
        return new Result(url);
    }

}
