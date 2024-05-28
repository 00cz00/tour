package com.example.tour.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ArticleDTO {
    private String title;//题目
    private String userId;//
    private String url;//封面

    private String content;//内容
    private int likes;
    private int provinceId;//省份id

    private int scenicSpotId;//景点id
    private LocalDateTime createTime;//时间
}
