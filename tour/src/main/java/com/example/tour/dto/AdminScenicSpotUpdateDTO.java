package com.example.tour.dto;

import lombok.Data;

@Data
public class AdminScenicSpotUpdateDTO {
    //id
    private String id;
    //景点名
    private String sceneRollCall;
    //所属省份id
    private int provinceId;
    //点赞数
    private int likes;
    //图片url
    private String url;
    //视频url
    private String videoUrl;
    //描述
    private String content;
}
