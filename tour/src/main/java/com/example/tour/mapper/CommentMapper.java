package com.example.tour.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentMapper {
    //根据文章id查询其喜欢数
    @Select("select count(*) from tour.comment where article_id=#{id}")
    int countComment(long id);
}
