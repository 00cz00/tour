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
    private Long id;
    //景点名
    private String sceneRollCall;
    //所属省份id
    private String provinceName;
    //点赞数
    private int likes;
    //图片url
    private String url;
    //是否点赞
    private int isLiked;
}
