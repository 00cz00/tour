package com.example.tour.service;

import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.entity.ScenicSpot;


import java.util.List;

public interface ScenicSpotService {
    //景点分页查询
    List<ScenicSpot> page(ScenicSpotPageQueryDTO scenicSpotPageQueryDTO);
}
