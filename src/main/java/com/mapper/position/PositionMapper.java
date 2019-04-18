package com.mapper.position;

import com.model.vo.position.PositionListVO;
import com.model.entity.position.Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    Position selectByContentRankClassify(@Param("content") String content,@Param("rank") Integer rank,@Param("classify") Integer classify);

    List<PositionListVO> positionList(@Param("classify")Integer classify,@Param("content") String content,@Param("rank") Integer rank);
}