package com.mapper.user;

import com.model.entity.user.CourtyardUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourtyardUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourtyardUser record);

    int insertSelective(CourtyardUser record);

    CourtyardUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourtyardUser record);

    int updateByPrimaryKey(CourtyardUser record);
}