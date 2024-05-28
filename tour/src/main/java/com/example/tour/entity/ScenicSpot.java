package com.example.tour.entity;


import lombok.*;

import java.security.PrivateKey;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScenicSpot {
    //id
    private Long id;
    //景点名
    private String sceneRollCall;
    //所属省份id
    private int provinceId;
    //点赞数
    private int likes;
    //图片url
    private String url;
}
