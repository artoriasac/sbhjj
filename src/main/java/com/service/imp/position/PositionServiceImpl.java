package com.service.imp.position;

import com.common.model.ServiceException;
import com.common.utils.ProUtil;
import com.mapper.position.PositionClassifyMapper;
import com.mapper.position.PositionMapper;
import com.mapper.position.PositionRankMapper;
import com.model.dto.position.AddPositionClassifyDTO;
import com.model.dto.position.AddPositionDTO;
import com.model.vo.position.PositionListVO;
import com.model.entity.position.Position;
import com.model.entity.position.PositionClassify;
import com.model.entity.position.PositionRank;
import com.model.vo.position.PositionClassifyListVO;
import com.model.vo.position.PositionRankVO;
import com.service.inf.position.PositionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private PositionRankMapper rankMapper;
    @Autowired
    private PositionClassifyMapper classifyMapper;

    @Override
    public List<PositionRankVO> rankList() {
        return rankMapper.rankList();
    }

    @Override
    public void addPosition(AddPositionDTO addPositionDTO) {
        if (ProUtil.isEmpty(addPositionDTO)){
            throw new ServiceException("sbhjj");
        }
        PositionRank positionRank = rankMapper.selectByPrimaryKey(addPositionDTO.getRank());
        if (positionRank==null){
            throw new ServiceException("等级不存在");
        }
        PositionClassify positionClassify = classifyMapper.selectByPrimaryKey(addPositionDTO.getClassify());
        if (positionClassify==null){
            throw new ServiceException("分类不存在");
        }
        Position position = positionMapper.selectByContentRankClassify(addPositionDTO.getContent(), addPositionDTO.getRank(),addPositionDTO.getClassify());
        if (position!=null){
            throw new ServiceException("已存在");
        }
        position =new Position();
        BeanUtils.copyProperties(addPositionDTO,position);
        positionMapper.insert(position);
    }

    @Override
    public void addPositionClassify(AddPositionClassifyDTO addPositionClassifyDTO) {
        if (ProUtil.isEmpty(addPositionClassifyDTO)){
            throw new ServiceException("sbhjj");
        }
        PositionClassify positionClassify = classifyMapper.selectByContent(addPositionClassifyDTO.getContent());
        if (positionClassify!=null){
            throw new ServiceException("分类以存在");
        }
        positionClassify =new PositionClassify();
        BeanUtils.copyProperties(addPositionClassifyDTO,positionClassify);
        classifyMapper.insert(positionClassify);
    }

    @Override
    public List<PositionClassifyListVO> positionClassifyList() {
        return classifyMapper.positionClassifyList();
    }

    @Override
    public List<PositionListVO> positionList(Integer classify, String content, Integer rank) {
        if (classify==null){
            throw new ServiceException("sbhjj");
        }
        return  positionMapper.positionList(classify,content,rank);
    }
}
