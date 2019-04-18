package com.mapper.order;

import com.model.entity.order.OrderUserPosition;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderUserPositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderUserPosition record);

    int insertSelective(OrderUserPosition record);

    OrderUserPosition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderUserPosition record);

    int updateByPrimaryKey(OrderUserPosition record);
}