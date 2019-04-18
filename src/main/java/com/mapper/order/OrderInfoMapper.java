package com.mapper.order;

import com.model.bo.order.OrderUserWeight;
import com.model.entity.order.OrderInfo;
import com.model.vo.order.OrderInfoVO;
import com.model.vo.order.OrderListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKeyWithBLOBs(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    List<OrderListVO> selectOrderList(@Param("title") String title, @Param("createUser") Integer createUser, @Param("type") List<Integer> type);

    OrderInfoVO orderInfo(Integer id);

    List<OrderListVO> selfOrderList(Integer userId);

    Double orderSumWeight(Integer orderId);

    List<OrderUserWeight> orderUserWeight(Integer orderId);

    Integer selectOrderScore(Integer orderId);
}