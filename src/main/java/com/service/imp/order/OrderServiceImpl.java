package com.service.imp.order;

import com.common.model.DataInfo;
import com.common.model.MemberInfo;
import com.common.model.ServiceException;
import com.common.utils.MemberUtils;
import com.common.utils.PageUtils;
import com.common.utils.ProUtil;
import com.mapper.order.*;
import com.mapper.user.UserPositionMapper;
import com.model.dto.order.*;
import com.model.entity.order.*;
import com.model.entity.user.UserPosition;
import com.model.vo.order.CommentVO;
import com.model.vo.order.OrderInfoVO;
import com.model.vo.order.OrderListVO;
import com.model.vo.order.RankVO;
import com.service.inf.integration.IntegrationService;
import com.service.inf.order.OrderService;
import com.service.inf.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderInfoMapper infoMapper;
    @Autowired
    private OrderAccessoryMapper accessoryMapper;
    @Autowired
    private OrderTypeMapper typeMapper;
    @Autowired
    private OrderRankMapper rankMapper;
    @Autowired
    private OrderUserMapper orderUserMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderCommentMapper commentMapper;
    @Autowired
    private UserPositionMapper userPositionMapper;
    @Autowired
    private OrderUserPositionMapper orderUserPositionMapper;
    @Autowired
    private IntegrationService integrationService;


    @Override
    public void addOrder(AddOrderDTO addOrderDTO) {
        Date date = new Date(System.currentTimeMillis());
        Set<String> set=new HashSet<>();
        set.add("list");
        if (ProUtil.isEmpty(addOrderDTO,set)){
            return;
        }
        OrderRank orderRank = rankMapper.selectByPrimaryKey(addOrderDTO.getRank());
        if (orderRank==null){
            throw new ServiceException("等级不存在");
        }
        OrderInfo order=new OrderInfo();
        BeanUtils.copyProperties(addOrderDTO,order);
        MemberInfo memberInfo = MemberUtils.getMemberInfo();
        order.setCreateUser(memberInfo.getId());
        order.setCreateTime(date);
        if (userService.isAdmin(memberInfo.getId())){
            order.setType(2);
            order.setWeight(2);
        }else {
            order.setType(1);
            order.setWeight(1);
        }
        infoMapper.insert(order);
        List<OrderAccessoryDTO> list = addOrderDTO.getList();
        if (list==null||list.isEmpty()){
            return;
        }
        for (OrderAccessoryDTO dto:list){
            OrderAccessory accessory=new OrderAccessory();
            BeanUtils.copyProperties(dto,accessory);
            accessory.setOrderId(order.getId());
            accessoryMapper.insert(accessory);
        }
    }

    @Override
    public List<RankVO> rankList() {
        return rankMapper.rankList();
    }

    @Override
    public DataInfo<OrderListVO> submitApprovalOrderList(Integer page, Integer pageSize, String title, Integer createUser) {
        PageUtils.startPage(page,pageSize);
        List<Integer> types=new ArrayList<>();
        types.add(1);
        return PageUtils.getDataInfo(infoMapper.selectOrderList(title,createUser,types));
    }

    @Override
    public OrderInfoVO orderInfo(Integer id) {
        OrderInfoVO orderInfoVO = infoMapper.orderInfo(id);
        if (orderInfoVO==null){
            return orderInfoVO;
        }
        if (orderInfoVO.getTypeId()==1){
            if (!userService.isAdmin(MemberUtils.getMemberInfo().getId())){
                throw new ServiceException("无权限查看");
            }
        }
        orderInfoVO.setAccessoryList(accessoryMapper.selectByOrderId(orderInfoVO.getId()));
        orderInfoVO.setOrderUserList(orderUserMapper.selectByOrderId(id));
        return orderInfoVO;
    }

    @Override
    public DataInfo<OrderListVO> transitApprovalOrderList(Integer page, Integer pageSize, String title, Integer createUser) {
        PageUtils.startPage(page,pageSize);
        List<Integer> types=new ArrayList<>();
        types.add(2);
        types.add(3);
        types.add(4);
        types.add(5);
        return PageUtils.getDataInfo(infoMapper.selectOrderList(title,createUser,types));
    }

    @Override
    public void commentOrder(CommentOrderDTO commentOrderDTO) {
        Date date = new Date(System.currentTimeMillis());
        if (ProUtil.isEmpty(commentOrderDTO)){
            throw new ServiceException("sbhjj");
        }
        OrderInfo orderInfo = infoMapper.selectByPrimaryKey(commentOrderDTO.getOrderId());
        if (orderInfo==null){
            throw new ServiceException("工单不存在");
        }
        OrderComment comment=new OrderComment();
        BeanUtils.copyProperties(commentOrderDTO,comment);
        comment.setIsParent(0);
        comment.setPid(0);
        comment.setUserId(MemberUtils.getMemberInfo().getId());
        comment.setCreateTime(date);
        commentMapper.insert(comment);
    }

    @Override
    public DataInfo<CommentVO> orderCommentList(Integer page, Integer pageSize, Integer orderId) {
        if (orderId==null){
            throw new ServiceException("sbhjj");
        }
        PageUtils.startPage(page,pageSize);
        return PageUtils.getDataInfo(commentMapper.orderCommentList(orderId));
    }

    @Override
    public void drawOrder(DrawOrderDTO drawOrderDTO) {
        Date date = new Date(System.currentTimeMillis());
        if (ProUtil.isEmpty(drawOrderDTO)){
            throw new ServiceException("sbhjj");
        }
        List<Integer> positionIds = drawOrderDTO.getPositionIds();
        if (positionIds.isEmpty()){
            throw new ServiceException("没有添加职务");
        }
        OrderInfo orderInfo = infoMapper.selectByPrimaryKey(drawOrderDTO.getOrderId());
        if (orderInfo==null){
            throw new ServiceException("工单不存在");
        }
        if (orderInfo.getType()!=2){
            throw new ServiceException("工单不能领取");
        }
        OrderUser orderUser=new OrderUser();
        BeanUtils.copyProperties(drawOrderDTO,orderUser);
        orderUser.setType(0);
        orderUser.setCreateTime(date);
        orderUser.setUserId(MemberUtils.getMemberInfo().getId());
        orderUserMapper.insert(orderUser);
        orderInfo.setType(3);
        infoMapper.updateByPrimaryKey(orderInfo);

        for (Integer positionId:positionIds){
            UserPosition userPosition = userPositionMapper.selectByUserPosition(MemberUtils.getMemberInfo().getId(), positionId);
            if (userPosition==null){
                throw new ServiceException("该用户没有这个职务");
            }
            OrderUserPosition orderUserPosition=new OrderUserPosition();
            orderUserPosition.setOrderUser(orderUser.getId());
            orderUserPosition.setPosition(positionId);
            orderUserPositionMapper.insert(orderUserPosition);
        }
    }

    @Override
    public void addOrderUser(AddOrderUserListDTO addOrderUserListDTO) {
        Date date = new Date(System.currentTimeMillis());
        if (ProUtil.isEmpty(addOrderUserListDTO)){
            throw new ServiceException("sbhjj");
        }
        OrderInfo orderInfo = infoMapper.selectByPrimaryKey(addOrderUserListDTO.getOrderId());
        if (orderInfo==null){
            throw new ServiceException("工单不存在");
        }
        if (orderInfo.getType()!=3){
            throw new ServiceException("该工单不能添加成员");
        }
        OrderUser orderUser = orderUserMapper.selectByOrderIdUserId(addOrderUserListDTO.getOrderId(), MemberUtils.getMemberInfo().getId(),0);
        if (orderUser==null){
            throw new ServiceException("没有领取该工单");
        }
        List<AddOrderUserDTO> list = addOrderUserListDTO.getList();
        for (AddOrderUserDTO dto:list){
            OrderUser member = orderUserMapper.selectByOrderIdUserId(addOrderUserListDTO.getOrderId(), dto.getUserId(),null);
            if (member!=null){
                continue;
            }
            member=new OrderUser();
            member.setUserId(dto.getUserId());
            member.setCreateTime(date);
            member.setType(1);
            member.setOrderId(addOrderUserListDTO.getOrderId());
            orderUserMapper.insert(member);

            List<Integer> positionIds = dto.getPositionIds();
            if (positionIds==null||positionIds.isEmpty()){
                throw new ServiceException(dto.getUserId()+"用户没有添加职务");
            }
            for (Integer position:positionIds){
                UserPosition userPosition = userPositionMapper.selectByUserPosition(dto.getUserId(), position);
                if (userPosition==null){
                    throw new ServiceException(dto.getUserId()+"用户没有该职务");
                }
                OrderUserPosition orderUserPosition=new OrderUserPosition();
                orderUserPosition.setPosition(position);
                orderUserPosition.setOrderUser(member.getId());
                orderUserPositionMapper.insert(orderUserPosition);
            }
        }
    }

    @Override
    public DataInfo<OrderListVO> selfOrderList(Integer page, Integer pageSize) {
        PageUtils.startPage(page, pageSize);
        return PageUtils.getDataInfo(infoMapper.selfOrderList(MemberUtils.getMemberInfo().getId()));
    }

    @Override
    public void finishOrder(Integer orderId) {
        OrderUser orderUser = orderUserMapper.selectByOrderIdUserId(orderId, MemberUtils.getMemberInfo().getId(), 0);
        if (orderUser==null){
            throw new ServiceException("没有领取该工单");
        }
        OrderInfo orderInfo = infoMapper.selectByPrimaryKey(orderId);
        if (orderInfo.getType()!=3){
            throw new ServiceException("不能完成该工单");
        }
        orderInfo.setType(4);
        infoMapper.updateByPrimaryKey(orderInfo);
    }

    @Override
    public void monitorFinishOrder(Integer orderId) {
        OrderInfo orderInfo = infoMapper.selectByPrimaryKey(orderId);
        if (orderInfo==null||orderInfo.getType()!=4){
            throw new ServiceException("不能审核完成该工单");
        }
        orderInfo.setType(5);
        infoMapper.updateByPrimaryKey(orderInfo);
        integrationService.finishOrder(orderId);
    }
}
