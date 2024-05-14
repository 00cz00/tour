package com.example.tour.dto;

import com.example.tour.service.impl.ScenicSpotServiceimpl;
import lombok.Data;

@Data
public class ScenicSpotPageQueryDTO {

    //页码
    private int page;

    //每页记录数
    private int pageSize;

    //查询条件
    private String condition;
    //最新或者最热
    private String searchBy;

}
