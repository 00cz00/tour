package com.example.tour.vo;

import com.example.tour.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDetialVO {

    private User user;//评论的用户
    private String content; //评论的详细内容
}
