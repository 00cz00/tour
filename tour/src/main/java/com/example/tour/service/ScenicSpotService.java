package com.example.tour.service;

import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.result.PageResult;

public interface ScenicSpotService {
    //景点分页查询
    PageResult page(ScenicSpotPageQueryDTO scenicSpotPageQueryDTO);
}
