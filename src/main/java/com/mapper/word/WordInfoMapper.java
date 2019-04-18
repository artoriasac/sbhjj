package com.mapper.word;

import com.model.entity.word.WordInfo;
import com.model.vo.word.WordInfoListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WordInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WordInfo record);

    int insertSelective(WordInfo record);

    WordInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WordInfo record);

    int updateByPrimaryKey(WordInfo record);

    List<WordInfoListVO> wordInfoList(@Param("classifyId") Integer classifyId,@Param("title") String title, @Param("mark")String mark);
}