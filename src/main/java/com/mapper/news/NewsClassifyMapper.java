package com.mapper.news;

import com.model.entity.news.NewsClassify;
import com.model.vo.news.NewsClassifyListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsClassifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsClassify record);

    int insertSelective(NewsClassify record);

    NewsClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsClassify record);

    int updateByPrimaryKey(NewsClassify record);

    NewsClassify selectByContent(String content);

    List<NewsClassifyListVO> getNewsClassifyList();

    List<NewsClassify> test(String test);
}