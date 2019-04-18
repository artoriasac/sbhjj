package com.mapper.integration;

import com.model.entity.integration.Integration;
import com.model.vo.integration.IntegrationInfoVO;
import com.model.vo.integration.IntegrationListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IntegrationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Integration record);

    int insertSelective(Integration record);

    Integration selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Integration record);

    int updateByPrimaryKey(Integration record);

    List<IntegrationListVO> integrationList();

    List<IntegrationInfoVO> integrationInfo(Integer userId);
}