package com.mapper.order;

import com.model.entity.order.OrderType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderType record);

    int insertSelective(OrderType record);

    OrderType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderType record);

    int updateByPrimaryKey(OrderType record);
}