package com.example.tour.mapper;

import com.example.tour.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BannerMapper {

    @Select("SELECT * FROM banner")
    List<Banner> bannerSelect();
}
