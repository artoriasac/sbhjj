package com.service.inf.position;

import com.model.dto.position.AddPositionClassifyDTO;
import com.model.dto.position.AddPositionDTO;
import com.model.vo.position.PositionListVO;
import com.model.vo.position.PositionClassifyListVO;
import com.model.vo.position.PositionRankVO;

import java.util.List;

public interface PositionService {
    List<PositionRankVO> rankList();

    void addPosition(AddPositionDTO addPositionDTO);

    void addPositionClassify(AddPositionClassifyDTO addPositionClassifyDTO);

    List<PositionClassifyListVO> positionClassifyList();

    List<PositionListVO> positionList(Integer classify, String content, Integer rank);
}
