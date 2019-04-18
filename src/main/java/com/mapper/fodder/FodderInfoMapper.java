package com.mapper.fodder;

import com.model.entity.fodder.FodderInfo;
import com.model.vo.fodder.FodderInfoListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FodderInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FodderInfo record);

    int insertSelective(FodderInfo record);

    FodderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FodderInfo record);

    int updateByPrimaryKey(FodderInfo record);

    List<FodderInfoListVO> fodderInfoList(@Param("classifyId") Integer classifyId, @Param("title") String title, @Param("type") String type);
}