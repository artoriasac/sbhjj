package com.service.imp.integration;

import com.mapper.integration.IntegrationMapper;
import com.mapper.order.OrderInfoMapper;
import com.mapper.order.OrderUserMapper;
import com.mapper.order.OrderUserPositionMapper;
import com.model.bo.order.OrderUserWeight;
import com.model.entity.integration.Integration;
import com.model.vo.integration.IntegrationInfoVO;
import com.model.vo.integration.IntegrationListVO;
import com.service.inf.integration.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class IntegrationServiceImpl implements IntegrationService {
    @Autowired
    private OrderInfoMapper infoMapper;
    @Autowired
    private OrderUserPositionMapper userPositionMapper;
    @Autowired
    private OrderUserMapper orderUserMapper;
    @Autowired
    private IntegrationMapper integrationMapper;

    private static final String ORDER="order";

    private static final Map<String,String> TYPE_MAP;

    static {
        TYPE_MAP=new HashMap<>();
        TYPE_MAP.put(ORDER,"工单");
    }

    @Override
    public void finishOrder(Integer orderId) {
        Double sumWeight = infoMapper.orderSumWeight(orderId);
        List<OrderUserWeight> orderUserWeights = infoMapper.orderUserWeight(orderId);
        Integer orderScore = infoMapper.selectOrderScore(orderId);
        Date date = new Date(System.currentTimeMillis());
        for (OrderUserWeight userWeight:orderUserWeights){
            int userScore = new Double(Math.ceil((userWeight.getWeight() / sumWeight) * orderScore)).intValue();
            Integration integration=new Integration();
            integration.setCreateTime(date);
            integration.setGrade(userScore);
            integration.setSource(orderId);
            integration.setType(ORDER);
            integration.setUser(userWeight.getUserId());
            integrationMapper.insert(integration);
        }
    }

    @Override
    public List<IntegrationListVO> integrationList() {
        return integrationMapper.integrationList();
    }

    @Override
    public List<IntegrationInfoVO> integrationInfo(Integer userId) {
        List<IntegrationInfoVO> integrationInfoVOs = integrationMapper.integrationInfo(userId);
        if (integrationInfoVOs==null||integrationInfoVOs.isEmpty()){
            return null;
        }
       for (IntegrationInfoVO vo:integrationInfoVOs){
            vo.setType(TYPE_MAP.get(vo.getType()));
       }
       return integrationInfoVOs;
    }
}
