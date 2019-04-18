package com.mapper.order;

import com.model.entity.order.OrderRank;
import com.model.vo.order.RankVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderRankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRank record);

    int insertSelective(OrderRank record);

    OrderRank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRank record);

    int updateByPrimaryKey(OrderRank record);

    List<RankVO> rankList();
}