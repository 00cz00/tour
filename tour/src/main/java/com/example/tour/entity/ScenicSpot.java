package com.example.tour.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
    private String sceneRollcall;
    //所属省份id
    private int provinceId;
    //点赞数
    private int like;
}
