package com.controller.order;

import com.annotation.Authority;
import com.annotation.Login;
import com.annotation.Logs;
import com.common.model.DataInfo;
import com.common.model.Result;
import com.model.dto.order.AddOrderDTO;
import com.model.dto.order.AddOrderUserListDTO;
import com.model.dto.order.CommentOrderDTO;
import com.model.dto.order.DrawOrderDTO;
import com.model.vo.order.CommentVO;
import com.model.vo.order.OrderInfoVO;
import com.model.vo.order.OrderListVO;
import com.model.vo.order.RankVO;
import com.service.inf.order.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@Api(tags = "工单")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Logs
    @Login
    @GetMapping("rankList")
    @ApiOperation("等级列表")
    public Result<List<RankVO>> rankList(){
        List<RankVO> result=orderService.rankList();
        return new Result(result);
    }

    @Logs
    @Login
    @PostMapping("addOrder")
    @ApiOperation("提交工单")
    public Result addOrder(@RequestBody AddOrderDTO addOrderDTO){
        orderService.addOrder(addOrderDTO);
        return new Result();
    }

    @Logs
    @Login
    @PostMapping("commentOrder")
    @ApiOperation("评论工单")
    public Result commentOrder(@RequestBody CommentOrderDTO commentOrderDTO){
        orderService.commentOrder(commentOrderDTO);
        return new Result();
    }

    @Logs
    @Login
    @PostMapping("drawOrder")
    @ApiOperation("领取工单")
    public Result drawOrder(@RequestBody DrawOrderDTO drawOrderDTO){
        orderService.drawOrder(drawOrderDTO);
        return new Result();
    }

    @Logs
    @Login
    @PostMapping("addOrderUser")
    @ApiOperation("添加工单成员")
    public Result addOrderUser(@RequestBody AddOrderUserListDTO addOrderUserListDTO){
        orderService.addOrderUser(addOrderUserListDTO);
        return new Result();
    }

    @Logs
    @Login
    @Authority(1)
    @GetMapping("submitApprovalOrderList")
    @ApiOperation("提交的待审批工单列表")
    public Result<DataInfo<OrderListVO>> submitApprovalOrderList(@RequestParam Integer page,
                                          @RequestParam Integer pageSize,
                                          @RequestParam(required = false) String title,
                                          @RequestParam(required = false) Integer createUser){
        DataInfo<OrderListVO> result=orderService.submitApprovalOrderList(page,pageSize,title,createUser);
        return new Result(result);
    }

    @Logs
    @Login
    @GetMapping("orderInfo")
    @ApiOperation("工单详情")
    public Result<OrderInfoVO> orderInfo(@RequestParam Integer id){
        OrderInfoVO result=orderService.orderInfo(id);
        return new Result(result);
    }

    @Logs
    @Login
    @GetMapping("transitApprovalOrderList")
    @ApiOperation("通过审批的工单列表")
    public Result<DataInfo<OrderListVO>> transitApprovalOrderList(@RequestParam Integer page,
                                          @RequestParam Integer pageSize,
                                          @RequestParam(required = false) String title,
                                          @RequestParam(required = false) Integer createUser){
        DataInfo<OrderListVO> result=orderService.transitApprovalOrderList(page,pageSize,title,createUser);
        return new Result(result);
    }

    @Logs
    @Login
    @GetMapping("orderCommentList")
    @ApiOperation("工单评论列表")
    public Result<DataInfo<CommentVO>> orderCommentList(@RequestParam Integer page,
                                                        @RequestParam Integer pageSize,
                                                        @RequestParam Integer orderId){
        DataInfo<CommentVO> result=orderService.orderCommentList(page,pageSize,orderId);
        return new Result(result);
    }

    @Logs
    @Login
    @GetMapping("selfOrderList")
    @ApiOperation("自己领取的工单列表")
    public Result<DataInfo<OrderListVO>> selfOrderList(@RequestParam Integer page,
                                                       @RequestParam Integer pageSize){
        DataInfo<OrderListVO> result=orderService.selfOrderList(page,pageSize);
        return new Result(result);
    }

    @Logs
    @Login
    @PostMapping("finishOrder")
    @ApiOperation("完成工单")
    public Result finishOrder(@RequestParam Integer orderId){
        orderService.finishOrder(orderId);
        return new Result();
    }

    @Logs
    @Login
    @Authority(1)
    @PostMapping("monitorFinishOrder")
    @ApiOperation("审核完成工单，结算积分")
    public Result monitorFinishOrder(@RequestParam Integer orderId){
        orderService.monitorFinishOrder(orderId);
        return new Result();
    }
}
