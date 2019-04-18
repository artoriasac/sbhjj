package com.mapper.position;

import com.model.entity.position.PositionRank;
import com.model.vo.position.PositionRankVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PositionRankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PositionRank record);

    int insertSelective(PositionRank record);

    PositionRank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PositionRank record);

    int updateByPrimaryKey(PositionRank record);

    List<PositionRankVO> rankList();
}