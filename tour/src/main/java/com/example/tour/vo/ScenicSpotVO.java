package com.example.tour.vo;

import com.example.tour.entity.ScenicSpot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScenicSpotVO {

    //id
    private String id;
    //景点名
    private String sceneRollCall;
    //所属省份id

    //
    private int provinceId;

    private String provinceName;
    //点赞数
    private int likes;
    //图片url
    private String url;
    //视频url
    private String videoUrl;
    //描述
    private String content;
    //是否点赞
    private int isLiked;
}
