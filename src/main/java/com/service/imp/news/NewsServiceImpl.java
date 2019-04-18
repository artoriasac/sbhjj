package com.service.imp.news;

import com.common.model.DataInfo;
import com.common.model.ServiceException;
import com.common.utils.MemberUtils;
import com.common.utils.PageUtils;
import com.common.utils.ProUtil;
import com.mapper.news.NewsClassifyMapper;
import com.mapper.news.NewsInfoMapper;
import com.model.dto.news.AddNewsClassifyDTO;
import com.model.dto.news.AddNewsInfoVO;
import com.model.dto.news.UpdateNewsClassifyDTO;
import com.model.entity.news.NewsClassify;
import com.model.entity.news.NewsInfo;
import com.model.vo.news.NewsClassifyListVO;
import com.model.vo.news.NewsInfoListVO;
import com.model.vo.news.NewsInfoVO;
import com.service.inf.news.NewsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsClassifyMapper classifyMapper;

    @Autowired
    private NewsInfoMapper newsInfoMapper;

    @Override
    public void addNewsClassify(AddNewsClassifyDTO addNewsClassifyDTO)throws Exception{
        if (ProUtil.isEmpty(addNewsClassifyDTO)){
            throw new ServiceException("标题不能为空");
        }
        NewsClassify newsClassify = classifyMapper.selectByContent(addNewsClassifyDTO.getContent());
        if (newsClassify!=null){
            throw new ServiceException("标题已存在");
        }
        NewsClassify result=new NewsClassify();
        BeanUtils.copyProperties(addNewsClassifyDTO,result);
        classifyMapper.insert(result);
    }

    @Override
    public List<NewsClassifyListVO> getNewsClassifyList() {
        return classifyMapper.getNewsClassifyList();
    }

    @Override
    public void updateNewsClassify(UpdateNewsClassifyDTO updateNewsClassifyDTO) {
        if (ProUtil.isEmpty(updateNewsClassifyDTO)){
            throw new ServiceException("你是傻逼吧");
        }
        NewsClassify newsClassifyById = classifyMapper.selectByPrimaryKey(updateNewsClassifyDTO.getId());
        if (newsClassifyById==null){
            throw new ServiceException("该分类不存在");
        }
        NewsClassify newsClassifyByContent = classifyMapper.selectByContent(updateNewsClassifyDTO.getContent());
        if (newsClassifyByContent!=null&&!newsClassifyByContent.getContent().equals(newsClassifyById.getContent())){
            throw new ServiceException("标题已存在");
        }
        BeanUtils.copyProperties(updateNewsClassifyDTO,newsClassifyByContent);
        classifyMapper.updateByPrimaryKey(newsClassifyByContent);
    }

    @Override
    public void addNewsInfo(AddNewsInfoVO addNewsInfoVO) throws Exception {
        Date date = new Date(System.currentTimeMillis());
        if (addNewsInfoVO==null){
            throw new ServiceException("你是傻逼吧");
        }
        if (addNewsInfoVO.getTitle()==null||addNewsInfoVO.getTitle().isEmpty()){
            throw new ServiceException("标题不能空");
        }
        if (addNewsInfoVO.getClassifyId()==null){
            throw new ServiceException("分类不能空");
        }
        if (addNewsInfoVO.getContent()==null||addNewsInfoVO.getContent().isEmpty()){
            throw new ServiceException("内容不能空");
        }
        if (addNewsInfoVO.getDigest()==null||addNewsInfoVO.getDigest().isEmpty()){
            throw new ServiceException("简介不能空");
        }
        NewsClassify newsClassify = classifyMapper.selectByPrimaryKey(addNewsInfoVO.getClassifyId());
        if (newsClassify==null){
            throw new ServiceException("分类不存在");
        }
        NewsInfo newsInfo=new NewsInfo();
        BeanUtils.copyProperties(addNewsInfoVO,newsInfo);
        newsInfo.setCreateTime(date);
        newsInfo.setModifyTime(date);
        newsInfo.setUserId(MemberUtils.getMemberInfo().getId());
        newsInfoMapper.insert(newsInfo);
    }

    @Override
    public DataInfo<NewsInfoListVO> getNewInfoList(Integer classifyId, String title, Integer page, Integer pageSize)throws Exception {
        PageUtils.startPage(page,pageSize);
        return PageUtils.getDataInfo(newsInfoMapper.getNewInfoList(classifyId,title));
    }

    @Override
    public List<NewsInfoVO> getNewsInfo(Integer id) {
        return null;
    }

    @Override
    public void test() {
        PageUtils.startPage(0,0);
        DataInfo<NewsClassify> dataInfo = PageUtils.getDataInfo(classifyMapper.test(null));
        List<NewsClassify> list = dataInfo.getList();
    }
}
