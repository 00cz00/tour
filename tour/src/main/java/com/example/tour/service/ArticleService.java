package com.example.tour.service;

import com.example.tour.dto.ArticlePageQueryDTO;
import com.example.tour.result.PageResult;

public interface ArticleService {
    //文章分页查询
    PageResult page(ArticlePageQueryDTO articlePageQueryDTO);
}
