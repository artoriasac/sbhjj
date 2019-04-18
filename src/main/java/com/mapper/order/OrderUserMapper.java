package com.mapper.order;

import com.model.entity.order.OrderUser;
import com.model.vo.order.OrderUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderUser record);

    int insertSelective(OrderUser record);

    OrderUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderUser record);

    int updateByPrimaryKey(OrderUser record);

    List<OrderUserVO> selectByOrderId(Integer orderId);

    OrderUser selectByOrderIdUserId(@Param("orderId") Integer orderId,@Param("userId") Integer userId,@Param("type")Integer type);
}