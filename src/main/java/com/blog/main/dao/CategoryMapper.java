package com.blog.main.dao;

import com.blog.main.model.Category;

public interface CategoryMapper {
    int insert(Category record);

    int insertSelective(Category record);
}