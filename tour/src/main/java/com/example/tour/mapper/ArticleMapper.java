package com.example.tour.mapper;

import com.example.tour.dto.ArticlePageQueryDTO;
import com.example.tour.entity.Article;
import com.example.tour.vo.ArticlePageQueryVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //文章分页查询

    Page<Article> page(ArticlePageQueryDTO articlePageQueryDTO);
    @Select("select * from article where id in (select article_id from collection where user_id=#{userId})")
    List<Article> selectCollection(String userId);
    @Update("update article set likes=(likes+1) where id=#{id}")
    void ThumbsUp(String id);
    @Delete("delete from article where id=#{id}")
    void delete(String id);
    @Select("select * from article where user_id=#{userId}")
    List<Article> selectMyArticle(String userId);

    @Select("select * from tour.article where id=#{id}")
    Article getBy(String id);
}
