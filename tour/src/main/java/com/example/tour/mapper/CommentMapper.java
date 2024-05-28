package com.example.tour.mapper;

import com.example.tour.dto.CommentDTO;
import com.example.tour.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    //根据文章id查询其喜欢数
    @Select("select count(*) from tour.comment where article_id=#{id}")
    int countComment(long id);

    @Insert("insert into comment(article_id,content,user_id,create_time) values (#{commentDTO.articleId},#{commentDTO.content},#{userId},#{commentDTO.createTime})")
    void comment(CommentDTO commentDTO, String userId);

    //根据文章id查出其全部评论
    @Select("select * from tour.comment where article_id=#{articleId} order by create_time desc")
    List<Comment> getByArticleId(String articleId);

    @Delete("delete from tour.comment where article_id=#{id}")
    void deleteByArticleId(String id);
}
