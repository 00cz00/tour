package com.example.tour.mapper;

import com.example.tour.dto.ArticleDTO;
import com.example.tour.dto.ArticlePageQueryDTO;
import com.example.tour.entity.Article;
import com.example.tour.vo.ArticlePageQueryVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

  //@Select("select * from tour.article where user_id in (select followee_id from tour.followee where user_id=#{userId}) limit #{articlePageQueryDTO.offset},#{articlePageQueryDTO.pageSize}")
  List<Article> followeeArticle(ArticlePageQueryDTO articlePageQueryDTO, String userId);

    //文章分页查询

    Page<Article> page(ArticlePageQueryDTO articlePageQueryDTO);
    @Select("select * from article where id in (select article_id from collection  where user_id=#{userId} ) order by id desc")
    List<Article> selectCollection(String userId);
    @Update("update article set likes=(likes+1) where id=#{id}")
    void ThumbsUp(String id);
    @Delete("delete from article where id=#{id}")
    void delete(String id);
    @Select("select * from article where user_id=#{userId} order by id desc")
    List<Article> selectMyArticle(String userId);

    @Select("select * from tour.article where id=#{id}")
    Article getBy(String id);
    @Update("update article set likes=(likes-1) where id=#{id}")
    void disThumbsUp(String id);

    @Select("select * from tour.article where id in (select article_id from tour.article_like where user_id=#{id})order by id desc")
  List<Article> getById(String id);
   @Insert("insert into tour.article( user_id, title, content, likes, province_id, scenic_spot_id, url,create_time) values(#{userId},#{title},#{content},#{likes}" +
        ",#{provinceId},#{scenicSpotId},#{url},#{createTime}) ")
    void add(ArticleDTO articleDTO);
}
