package com.example.tour.vo;

import com.example.tour.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetialVO {
    private Long id;
    private User user; //作者
    private String title;  //题目
    private String content; //内容
    private String url;     //背景图
    private String province; //省份
    private String createTime;//发布时间
    private int likes;        //点赞数
    private int collection; //收藏数
    private int comment;    //评论数
    private int isLiked;      //是否点赞该文章
    private int isCollected; //是否收藏该文章
    private int isFollowed; //收藏关注该文章作者
    private List commentContent;  //评论详细内容
}
