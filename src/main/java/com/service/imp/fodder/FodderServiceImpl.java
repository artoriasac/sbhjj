package com.service.imp.fodder;

import com.common.model.DataInfo;
import com.common.model.ServiceException;
import com.common.utils.MemberUtils;
import com.common.utils.PageUtils;
import com.common.utils.ProUtil;
import com.mapper.fodder.FodderClassifyMapper;
import com.mapper.fodder.FodderInfoMapper;
import com.model.dto.fodder.AddFodderClassifyDTO;
import com.model.dto.fodder.AddFodderInfoDTO;
import com.model.entity.fodder.FodderClassify;
import com.model.entity.fodder.FodderInfo;
import com.model.vo.fodder.FodderClassifyListVO;
import com.model.vo.fodder.FodderInfoListVO;
import com.service.inf.fodder.FodderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class FodderServiceImpl implements FodderService {
    @Autowired
    private FodderClassifyMapper classifyMapper;

    @Autowired
    private FodderInfoMapper infoMapper;

    @Override
    public void addFodderClassify(AddFodderClassifyDTO addFodderClassifyDTO) {
        if (ProUtil.isEmpty(addFodderClassifyDTO)){
            throw new ServiceException("你是傻逼吧");
        }
        FodderClassify classify=new FodderClassify();
        BeanUtils.copyProperties(addFodderClassifyDTO,classify);
        classifyMapper.insert(classify);
    }

    @Override
    public void addFodderInfo(AddFodderInfoDTO addFodderInfoDTO) {
        Date date = new Date(System.currentTimeMillis());
        Set<String> set=new HashSet();
        set.add("title");
        if (ProUtil.isEmpty(addFodderInfoDTO,set)){
            throw new ServiceException("你是傻逼吧");
        }
        FodderClassify classify = classifyMapper.selectByPrimaryKey(addFodderInfoDTO.getClassifyId());
        if (classify==null){
            throw new ServiceException("分类不存在");
        }
        FodderInfo fodderInfo=new FodderInfo();
        BeanUtils.copyProperties(addFodderInfoDTO,fodderInfo);
        fodderInfo.setUserId(MemberUtils.getMemberInfo().getId());
        fodderInfo.setCreateTime(date);
        infoMapper.insert(fodderInfo);
    }

    @Override
    public List<FodderClassifyListVO> fodderClassifyList() {
        return classifyMapper.fodderClassifyList();
    }

    @Override
    public DataInfo<FodderInfoListVO> fodderInfoList(Integer classifyId, String title, String type, Integer page, Integer pageSize) {
        if (classifyId==null){
            throw new ServiceException("你是傻逼吧");
        }
        PageUtils.startPage(page,pageSize);
        return PageUtils.getDataInfo(infoMapper.fodderInfoList(classifyId,title,type));
    }
}
