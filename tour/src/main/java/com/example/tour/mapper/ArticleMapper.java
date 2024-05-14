package com.example.tour.mapper;

import com.example.tour.dto.ArticlePageQueryDTO;
import com.example.tour.entity.Article;
import com.example.tour.vo.ArticlePageQueryVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    //文章分页查询
    Page<Article> page(ArticlePageQueryDTO articlePageQueryDTO);
}
