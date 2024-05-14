package com.example.tour.vo;

import com.example.tour.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.border.TitledBorder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePageQueryVO {
    private Long id;
    private User user; //作者
    private String title;  //题目
    private String content; //内容
    private String url;     //背景图
    private String province; //省份
    private int like;        //点赞数
    private int collection; //收藏数
    private int comment;    //评论数

}
