package com.service.inf.order;

import com.common.model.DataInfo;
import com.model.dto.order.AddOrderDTO;
import com.model.dto.order.AddOrderUserListDTO;
import com.model.dto.order.CommentOrderDTO;
import com.model.dto.order.DrawOrderDTO;
import com.model.vo.order.CommentVO;
import com.model.vo.order.OrderInfoVO;
import com.model.vo.order.OrderListVO;
import com.model.vo.order.RankVO;

import java.util.List;

public interface OrderService {
    void addOrder(AddOrderDTO addOrderDTO);

    List<RankVO> rankList();

    DataInfo<OrderListVO> submitApprovalOrderList(Integer page, Integer pageSize, String title, Integer createUser);

    OrderInfoVO orderInfo(Integer id);

    DataInfo<OrderListVO> transitApprovalOrderList(Integer page, Integer pageSize, String title, Integer createUser);

    void commentOrder(CommentOrderDTO commentOrderDTO);

    DataInfo<CommentVO> orderCommentList(Integer page, Integer pageSize, Integer orderId);

    void drawOrder(DrawOrderDTO drawOrderDTO);

    void addOrderUser(AddOrderUserListDTO addOrderUserListDTO);

    DataInfo<OrderListVO> selfOrderList(Integer page, Integer pageSize);

    void finishOrder(Integer orderId);

    void monitorFinishOrder(Integer orderId);
}
