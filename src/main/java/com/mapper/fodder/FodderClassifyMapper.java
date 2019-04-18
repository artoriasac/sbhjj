package com.mapper.fodder;

import com.model.entity.fodder.FodderClassify;
import com.model.vo.fodder.FodderClassifyListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FodderClassifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FodderClassify record);

    int insertSelective(FodderClassify record);

    FodderClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FodderClassify record);

    int updateByPrimaryKey(FodderClassify record);

    List<FodderClassifyListVO> fodderClassifyList();
}