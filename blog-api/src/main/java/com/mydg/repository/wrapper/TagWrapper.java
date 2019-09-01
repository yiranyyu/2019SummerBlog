package com.mydg.repository.wrapper;

import com.mydg.vo.TagVO;

import java.util.List;


public interface TagWrapper {

    List<TagVO> findAllDetail();

    TagVO getTagDetail(Integer tagId);
}
