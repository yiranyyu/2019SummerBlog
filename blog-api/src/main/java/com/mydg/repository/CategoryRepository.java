package com.mydg.repository;

import com.mydg.entity.Category;
import com.mydg.repository.wrapper.CategoryWrapper;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Integer>, CategoryWrapper {
}
