package com.controller.position;

import com.annotation.Authority;
import com.annotation.Login;
import com.annotation.Logs;
import com.common.model.Result;
import com.model.dto.position.AddPositionClassifyDTO;
import com.model.dto.position.AddPositionDTO;
import com.model.vo.position.PositionClassifyListVO;
import com.model.vo.position.PositionListVO;
import com.model.vo.position.PositionRankVO;
import com.service.inf.position.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "职务")
@RequestMapping("position")
public class PositionController {
    @Autowired
    private PositionService positionService;
    
    @Logs
    @Login
    @GetMapping("rankList")
    @ApiOperation("熟练等级列表")
    public Result<List<PositionRankVO>> rankList(){
        List<PositionRankVO> result=positionService.rankList();
        return new Result(result);
    }

    @Logs
    @Login
    @Authority(1)
    @PostMapping("addPositionClassify")
    @ApiOperation("添加职务分类")
    public Result addPositionClassify(@RequestBody AddPositionClassifyDTO addPositionClassifyDTO){
        positionService.addPositionClassify(addPositionClassifyDTO);
        return new Result();
    }

    @Logs
    @Login
    @Authority(1)
    @GetMapping("positionClassifyList")
    @ApiOperation("职务分类列表")
    public Result<List<PositionClassifyListVO>> positionClassifyList(){
        List<PositionClassifyListVO> result=positionService.positionClassifyList();
        return new Result(result);
    }

    @Logs
    @Login
    @Authority(1)
    @PostMapping("addPosition")
    @ApiOperation("添加职务")
    public Result addPosition(@RequestBody AddPositionDTO addPositionDTO){
        positionService.addPosition(addPositionDTO);
        return new Result();
    }

    @Logs
    @Login
    @GetMapping("positionList")
    @ApiOperation("职务列表")
    public Result<List<PositionListVO>> positionList(@ApiParam("分类id") @RequestParam Integer classify,
                                                     @ApiParam("职务名") @RequestParam(required = false) String content,
                                                     @ApiParam("熟练等级") @RequestParam(required = false) Integer rank){
        List<PositionListVO> result=positionService.positionList(classify,content,rank);
        return new Result(result);
    }
}
