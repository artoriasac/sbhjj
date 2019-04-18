package com.service.inf.fodder;

import com.common.model.DataInfo;
import com.model.dto.fodder.AddFodderClassifyDTO;
import com.model.dto.fodder.AddFodderInfoDTO;
import com.model.vo.fodder.FodderClassifyListVO;
import com.model.vo.fodder.FodderInfoListVO;

import java.util.List;

public interface FodderService {
    void addFodderClassify(AddFodderClassifyDTO addFodderClassifyDTO);

    void addFodderInfo(AddFodderInfoDTO addFodderInfoDTO);

    List<FodderClassifyListVO> fodderClassifyList();

    DataInfo<FodderInfoListVO> fodderInfoList(Integer classifyId, String title, String type, Integer page, Integer pageSize);
}
