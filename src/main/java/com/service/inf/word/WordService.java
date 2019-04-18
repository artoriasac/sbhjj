package com.service.inf.word;

import com.common.model.DataInfo;
import com.model.dto.word.AddWordClassifyDTO;
import com.model.dto.word.AddWordInfoDTO;
import com.model.vo.word.WordClassifyListVO;
import com.model.vo.word.WordInfoListVO;

import java.util.List;

public interface WordService {
    void addWordClassify(AddWordClassifyDTO addWordClassifyDTO);

    void addWordInfo(AddWordInfoDTO addWordInfoDTO);

    List<WordClassifyListVO> wordClassifyList();

    DataInfo<WordInfoListVO> wordInfoList(Integer classifyId, String title, String mark, Integer page, Integer pageSize);
}
