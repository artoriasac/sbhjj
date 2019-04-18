package com.service.inf.integration;

import com.model.vo.integration.IntegrationInfoVO;
import com.model.vo.integration.IntegrationListVO;

import java.util.List;

public interface IntegrationService {
    void finishOrder(Integer orderId);

    List<IntegrationListVO> integrationList();

    List<IntegrationInfoVO> integrationInfo(Integer userId);
}
