package com.controller.news;

import com.annotation.Login;
import com.annotation.Logs;
import com.common.model.DataInfo;
import com.common.model.Result;
import com.model.dto.news.AddNewsClassifyDTO;
import com.model.dto.news.AddNewsInfoVO;
import com.model.dto.news.UpdateNewsClassifyDTO;
import com.model.vo.news.NewsClassifyListVO;
import com.model.vo.news.NewsInfoListVO;
import com.model.vo.news.NewsInfoVO;
import com.service.inf.news.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("news")
@Api(tags = "新闻")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Logs
    @Login
    @ApiOperation("添加新闻分类")
    @PostMapping("addNewsClassify")
    public Result addNewsClassify(@RequestBody AddNewsClassifyDTO addNewsClassifyDTO)throws Exception{
        newsService.addNewsClassify(addNewsClassifyDTO);
        return new Result();
    }

    @Logs
    @Login
    @ApiOperation("查询新闻分类")
    @GetMapping("getNewsClassifyList")
    public Result<NewsClassifyListVO> getNewsClassifyList()throws Exception{
        List<NewsClassifyListVO> result= newsService.getNewsClassifyList();
        return new Result(result);
    }

    @Logs
    @Login
    @ApiOperation("修改新闻分类")
    @PutMapping("updateNewsClassify")
    public Result updateNewsClassify(@RequestBody UpdateNewsClassifyDTO updateNewsClassifyDTO)throws Exception{
        newsService.updateNewsClassify(updateNewsClassifyDTO);
        return new Result();
    }

    @Logs
    @Login
    @ApiOperation("添加新闻")
    @PostMapping("addNewsInfo")
    public Result addNewsInfo(@RequestBody AddNewsInfoVO addNewsInfoVO)throws Exception{
        newsService.addNewsInfo(addNewsInfoVO);
        return new Result();
    }

    @Logs
    @Login
    @ApiOperation("查看新闻列表")
    @GetMapping("getNewInfoList")
    public Result<DataInfo<NewsInfoListVO>> getNewInfoList(@ApiParam("分类id") @RequestParam(value = "classifyId" ,required = true) Integer classifyId,
                                 @ApiParam(value = "标题") @RequestParam(value = "title" ,required = false) String title,
                                 @ApiParam(value = "当前页数") @RequestParam(value = "page" ,required = false) Integer page,
                                 @ApiParam(value = "每页条数") @RequestParam(value = "pageSize" ,required = false) Integer pageSize)throws Exception{
        DataInfo<NewsInfoListVO> result= newsService.getNewInfoList(classifyId,title,page,pageSize);
        return new Result(result);
    }

    @Logs
    @Login
    @ApiOperation("新闻详情")
    @GetMapping("getNewsInfo")
    public Result<NewsInfoVO> getNewsInfo(@RequestParam Integer id)throws Exception{
        List<NewsInfoVO> result= newsService.getNewsInfo(id);
        return new Result(result);
    }

}
