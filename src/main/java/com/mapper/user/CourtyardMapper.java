package com.mapper.user;

import com.model.entity.user.Courtyard;
import com.model.vo.user.CourtyardListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourtyardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Courtyard record);

    int insertSelective(Courtyard record);

    Courtyard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Courtyard record);

    int updateByPrimaryKey(Courtyard record);

    Courtyard selectByContent(String content);

    List<CourtyardListVO> courtyardList();

    int selectByAdmin(Integer userId);
}