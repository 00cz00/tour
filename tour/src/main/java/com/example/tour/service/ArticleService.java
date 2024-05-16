package com.example.tour.service;

import com.example.tour.dto.ArticlePageQueryDTO;
import com.example.tour.entity.Article;
import com.example.tour.vo.ArticleDetialVO;
import com.example.tour.vo.ArticlePageQueryVO;

import java.util.List;

public interface ArticleService {
    //文章分页查询
    List<ArticlePageQueryVO> page(ArticlePageQueryDTO articlePageQueryDTO,String userId);

    List<Article> selectCollection(String userId);


    List<Article> selectMyArticle(String userId);

    void collectArticle(String userId, String id);

    void comment(String articleId, String content, String userId);

    //根据id查询文章具体内容
    ArticleDetialVO getById(String id,String userId);
}
