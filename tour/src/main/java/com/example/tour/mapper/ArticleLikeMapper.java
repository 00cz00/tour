package com.example.tour.mapper;

import com.example.tour.entity.ArticleLike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleLikeMapper {
    @Select("select * from tour.article_like where user_id=#{userId} and article_id=#{id} order by id desc")
    ArticleLike isLike(String userId, long id);

    @Insert("insert into tour.article_like(user_id, article_id) values (#{userId},#{id})")
    void insert(String id, String userId);
    @Delete("delete from tour.article_like where user_id=#{userId} and article_id=#{id}")
    void disThumbsUp(String id, String userId);

    @Delete("delete from tour.article_like where article_id=#{id}")
    void deleteByArticleId(String id);
    @Delete("delete from tour.article_like where user_id=#{id} ")
    void deleteByUser(String id);
}
