package com.example.tour.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScenicSpotPic {
    private String id;
    private String sceneSpotId; //景点id
    private String url;  //图片url
    private String description; //图片对应描述
}
