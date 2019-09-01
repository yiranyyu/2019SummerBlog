package com.mydg.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mydg.BlogApiApplicationTests;
import com.mydg.vo.CategoryVO;

public class CategoryServiceTest extends BlogApiApplicationTests{

	@Autowired
	private CategoryService	categoryService;
	
	
	
	@Test
	public void findAllDetailTest() {
		
		List<CategoryVO> cs = categoryService.findAllDetail();
				
		cs.stream().forEach( c -> System.out.println(c.getCategoryname() + ":" + c.getArticles()));
		System.out.println(cs.size());
		
	}
}
