package com.mydg.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mydg.BlogApiApplicationTests;
import com.mydg.repository.CategoryRepository;

public class CategoryRepositoryTest extends BlogApiApplicationTests{

	@Autowired
	private CategoryRepository	categoryRepository;

	@Test
	public void test() {
	}
}
