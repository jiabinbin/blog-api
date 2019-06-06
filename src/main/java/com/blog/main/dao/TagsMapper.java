package com.blog.main.dao;

import com.blog.main.model.Tags;

public interface TagsMapper {
    int insert(Tags record);

    int insertSelective(Tags record);
}