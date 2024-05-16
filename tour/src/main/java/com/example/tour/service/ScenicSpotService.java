package com.example.tour.service;

import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.entity.ScenicSpot;
import com.example.tour.entity.ScenicSpotPic;


import java.util.List;

public interface ScenicSpotService {
    //景点分页查询
    List<ScenicSpot> page(ScenicSpotPageQueryDTO scenicSpotPageQueryDTO);

    //根据id查询景点详情
    List<ScenicSpotPic> getDetial(String id);
}
