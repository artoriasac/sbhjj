package com.controller.fodder;

import com.annotation.Login;
import com.annotation.Logs;
import com.common.model.DataInfo;
import com.common.model.Result;
import com.model.dto.fodder.AddFodderClassifyDTO;
import com.model.dto.fodder.AddFodderInfoDTO;
import com.model.vo.fodder.FodderClassifyListVO;
import com.model.vo.fodder.FodderInfoListVO;
import com.service.inf.fodder.FodderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fodder")
@Api(tags = "素材")
public class FodderController {
    @Autowired
    private FodderService fodderService;

    @Logs
    @Login
    @ApiOperation("添加素材分类")
    @PostMapping("addFodderClassify")
    public Result addFodderClassify(@RequestBody AddFodderClassifyDTO addFodderClassifyDTO){
        fodderService.addFodderClassify(addFodderClassifyDTO);
        return new Result<>();
    }

    @Logs
    @Login
    @ApiOperation("添加素材")
    @PostMapping("addFodderInfo")
    public Result addFodderInfo(@RequestBody AddFodderInfoDTO addFodderInfoDTO){
        fodderService.addFodderInfo(addFodderInfoDTO);
        return new Result<>();
    }

    @Logs
    @Login
    @ApiOperation("素材分类列表")
    @GetMapping("fodderClassifyList")
    public Result<List<FodderClassifyListVO>> fodderClassifyList(){
        List<FodderClassifyListVO> result=fodderService.fodderClassifyList();
        return new Result<>(result);
    }

    @Logs
    @Login
    @ApiOperation("素材列表")
    @GetMapping("fodderInfoList")
    public Result<DataInfo<FodderInfoListVO>> fodderInfoList(@ApiParam("分类id") @RequestParam Integer classifyId,
                                                             @ApiParam("标题") @RequestParam(required = false) String title,
                                                             @ApiParam("类型") @RequestParam(required = false) String type,
                                                             @ApiParam("当前页数") @RequestParam Integer page,
                                                             @ApiParam("每页条数") @RequestParam Integer pageSize){
        DataInfo<FodderInfoListVO> result=fodderService.fodderInfoList(classifyId,title,type,page,pageSize);
        return new Result<>(result);
    }
}
