package com.mydg.repository.wrapper;

import com.mydg.vo.CategoryVO;

import java.util.List;


public interface CategoryWrapper {

    List<CategoryVO> findAllDetail();

    CategoryVO getCategoryDetail(Integer categoryId);
}
