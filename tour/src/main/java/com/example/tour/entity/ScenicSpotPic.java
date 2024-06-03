package com.example.tour.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScenicSpotPic implements Serializable {
    private String id;
    private String sceneSpotId; //景点id
    private String url;  //图片url
    private String description; //图片对应描述
}
