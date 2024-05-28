package com.example.tour.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String id;
    private String userId;  //用户id
    private String articleId; //文章id
    private String content;   //评论内容
    private LocalDateTime createTime;//发布时间
}
