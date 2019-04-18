package com.controller.word;

import com.annotation.Login;
import com.annotation.Logs;
import com.common.model.DataInfo;
import com.common.model.Result;
import com.model.dto.word.AddWordClassifyDTO;
import com.model.dto.word.AddWordInfoDTO;
import com.model.vo.word.WordClassifyListVO;
import com.model.vo.word.WordInfoListVO;
import com.service.inf.word.WordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("word")
@Api(tags = "文档")
public class WordController {

    @Autowired
    private WordService wordService;

    @Logs
    @Login
    @PostMapping("addWordClassify")
    @ApiOperation("添加文档分类")
    public Result addWordClassify(@RequestBody AddWordClassifyDTO addWordClassifyDTO){
        wordService.addWordClassify(addWordClassifyDTO);
        return new Result();
    }

    @Logs
    @Login
    @PostMapping("addWordInfo")
    @ApiOperation("添加文档")
    public Result addWordInfo(@RequestBody AddWordInfoDTO addWordInfoDTO){
        wordService.addWordInfo(addWordInfoDTO);
        return new Result();
    }

    @Logs
    @Login
    @GetMapping("wordClassifyList")
    @ApiOperation("文档分类列表")
    public Result<List<WordClassifyListVO>> wordClassifyList(){
        List<WordClassifyListVO> result=wordService.wordClassifyList();
        return new Result(result);
    }

    @Logs
    @Login
    @GetMapping("wordInfoList")
    @ApiOperation("文档列表")
    public Result<DataInfo<WordInfoListVO>> wordInfoList(@ApiParam("分类id") @RequestParam Integer classifyId,
                                                         @ApiParam("标题") @RequestParam(required = false) String title,
                                                         @ApiParam("备注") @RequestParam(required = false) String mark,
                                                         @ApiParam("当前页数") @RequestParam Integer page,
                                                         @ApiParam("每页条数") @RequestParam Integer pageSize){
        DataInfo<WordInfoListVO> result=wordService.wordInfoList(classifyId,title,mark,page,pageSize);
        return new Result(result);
    }
}
