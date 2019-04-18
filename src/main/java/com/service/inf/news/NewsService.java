package com.service.inf.news;

import com.common.model.DataInfo;
import com.model.dto.news.AddNewsClassifyDTO;
import com.model.dto.news.AddNewsInfoVO;
import com.model.dto.news.UpdateNewsClassifyDTO;
import com.model.vo.news.NewsClassifyListVO;
import com.model.vo.news.NewsInfoListVO;
import com.model.vo.news.NewsInfoVO;

import java.util.List;

public interface NewsService {
    void addNewsClassify(AddNewsClassifyDTO addNewsClassifyDTO)throws Exception;

    List<NewsClassifyListVO> getNewsClassifyList();

    void updateNewsClassify(UpdateNewsClassifyDTO updateNewsClassifyDTO);

    void addNewsInfo(AddNewsInfoVO addNewsInfoVO) throws Exception;

    DataInfo<NewsInfoListVO> getNewInfoList(Integer classifyId, String title, Integer page, Integer pageSize)throws Exception;

    List<NewsInfoVO> getNewsInfo(Integer id);

    void test();
}
