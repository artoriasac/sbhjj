package com.mapper.order;

import com.model.entity.order.OrderAccessory;
import com.model.vo.order.AccessoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderAccessoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderAccessory record);

    int insertSelective(OrderAccessory record);

    OrderAccessory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderAccessory record);

    int updateByPrimaryKey(OrderAccessory record);

    List<AccessoryVO> selectByOrderId(Integer orderId);
}