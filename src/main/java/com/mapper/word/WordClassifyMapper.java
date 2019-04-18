package com.mapper.word;

import com.model.entity.word.WordClassify;
import com.model.vo.word.WordClassifyListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WordClassifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WordClassify record);

    int insertSelective(WordClassify record);

    WordClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WordClassify record);

    int updateByPrimaryKey(WordClassify record);

    List<WordClassifyListVO> wordClassifyList();
}