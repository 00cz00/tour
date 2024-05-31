package com.example.tour.service;

import com.example.tour.dto.AdminarticlePageQueryDTO;
import com.example.tour.dto.ArticleDTO;
import com.example.tour.dto.ArticlePageQueryDTO;
import com.example.tour.dto.CommentDTO;
import com.example.tour.entity.Article;
import com.example.tour.result.PageResult;
import com.example.tour.vo.ArticleDetialVO;
import com.example.tour.vo.ArticlePageQueryVO;

import java.util.List;

public interface ArticleService {
    //文章分页查询
    List<ArticlePageQueryVO> page(ArticlePageQueryDTO articlePageQueryDTO,String userId);

    List<Article> selectCollection(String userId);


    List<Article> selectMyArticle(String userId);

    void collectArticle(String userId, String id);

    void comment(CommentDTO commentDTO, String userId);

    //根据id查询文章具体内容
    ArticleDetialVO getById(String id,String userId);

    void ThumbsUp(String id,String userId);

    void delete(String id);

    void disThumbsUp(String id, String userId);

    //取消收藏
    void abolishCollect(String userId, String articleId);

    //查找用户关注的作者发布的文章
    List<ArticlePageQueryVO> followeeArticle(ArticlePageQueryDTO articlePageQueryDTO, String userId);

    //查询用户点赞的文章
    List<ArticlePageQueryVO> like(String id);

    //发布文章
    void publish(ArticleDTO articleDTO);

    List<Integer> selectByScenicSport(String id);

    //管理端分页查询
    PageResult adminArticlePage(AdminarticlePageQueryDTO adminarticlePageQueryDTO);
}
