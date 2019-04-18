package com.service.imp.word;

import com.common.model.DataInfo;
import com.common.model.ServiceException;
import com.common.utils.MemberUtils;
import com.common.utils.PageUtils;
import com.common.utils.ProUtil;
import com.mapper.word.WordClassifyMapper;
import com.mapper.word.WordInfoMapper;
import com.model.dto.word.AddWordClassifyDTO;
import com.model.dto.word.AddWordInfoDTO;
import com.model.entity.word.WordClassify;
import com.model.entity.word.WordInfo;
import com.model.vo.word.WordClassifyListVO;
import com.model.vo.word.WordInfoListVO;
import com.service.inf.word.WordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class WordServiceImpl implements WordService {
    @Autowired
    private WordInfoMapper infoMapper;

    @Autowired
    private WordClassifyMapper classifyMapper;

    @Override
    public void addWordClassify(AddWordClassifyDTO addWordClassifyDTO) {
        if (ProUtil.isEmpty(addWordClassifyDTO)){
            throw new ServiceException("你是傻逼吧");
        }
        WordClassify classify=new WordClassify();
        BeanUtils.copyProperties(addWordClassifyDTO,classify);
        classifyMapper.insert(classify);
    }

    @Override
    public void addWordInfo(AddWordInfoDTO addWordInfoDTO) {
        Date date = new Date(System.currentTimeMillis());
        if (addWordInfoDTO==null||addWordInfoDTO.getUrl()==null||addWordInfoDTO.getUrl().isEmpty()||addWordInfoDTO.getClassifyId()==null){
            throw new ServiceException("你是傻逼吧");
        }
        WordClassify classify = classifyMapper.selectByPrimaryKey(addWordInfoDTO.getClassifyId());
        if (classify==null){
            throw new ServiceException("分类不存在");
        }
        WordInfo info=new WordInfo();
        BeanUtils.copyProperties(addWordInfoDTO,info);
        info.setUserId(MemberUtils.getMemberInfo().getId());
        info.setCreateTime(date);
        infoMapper.insert(info);
    }

    @Override
    public List<WordClassifyListVO> wordClassifyList() {
        return classifyMapper.wordClassifyList();
    }

    @Override
    public DataInfo<WordInfoListVO> wordInfoList(Integer classifyId, String title, String mark, Integer page, Integer pageSize) {
        if (classifyId==null){
            throw new ServiceException("你是傻逼吧");
        }
        PageUtils.startPage(page,pageSize);
       return PageUtils.getDataInfo(infoMapper.wordInfoList(classifyId,title,mark));
    }
}
