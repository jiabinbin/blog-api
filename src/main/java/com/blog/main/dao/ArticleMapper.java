package com.blog.main.dao;

import com.blog.main.model.Article;

public interface ArticleMapper {
    int insert(Article record);

    int insertSelective(Article record);
}