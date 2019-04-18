package com.mapper.user;

import com.model.entity.user.UserPosition;
import com.model.vo.position.PositionListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserPositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPosition record);

    int insertSelective(UserPosition record);

    UserPosition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPosition record);

    int updateByPrimaryKey(UserPosition record);

    UserPosition selectByUserPosition(@Param("user") Integer user,@Param("position") Integer position);

    List<PositionListVO> userPositionList(Integer userId);
}