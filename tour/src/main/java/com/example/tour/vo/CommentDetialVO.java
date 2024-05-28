package com.example.tour.vo;

import com.example.tour.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDetialVO {

    private User user;//评论的用户
    private String content; //评论的详细内容
    private LocalDateTime createTime;//发布时间
}
