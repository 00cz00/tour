package com.example.tour.mapper;

import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.entity.ScenicSpot;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScenicSpotMapper {
     //景点分页查询

     Page<ScenicSpot> page(ScenicSpotPageQueryDTO scenicSpotPageQueryDTO);
}
