package com.example.tour.service;

import com.example.tour.entity.Banner;

import java.util.List;

public interface BannerService {
    List<Banner> bannerSelect();

    //删除轮播图
    void deleteById(String id);

    //添加
    void add(Banner banner);
}
