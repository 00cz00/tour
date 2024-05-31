package com.example.tour.dto;

import lombok.Data;

@Data

public class AdminScenicSpotPageDTO {
    private String condition;
    //省份id
    private String provinceId;
    //页码
    private int page;

    //每页记录数
    private int pageSize;

    //private int offset;
}
