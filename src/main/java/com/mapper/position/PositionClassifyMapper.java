package com.mapper.position;

import com.model.entity.position.PositionClassify;
import com.model.vo.position.PositionClassifyListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PositionClassifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PositionClassify record);

    int insertSelective(PositionClassify record);

    PositionClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PositionClassify record);

    int updateByPrimaryKey(PositionClassify record);

    PositionClassify selectByContent(String content);

    List<PositionClassifyListVO> positionClassifyList();
}