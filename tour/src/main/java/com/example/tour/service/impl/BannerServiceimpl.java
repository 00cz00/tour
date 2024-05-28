package com.example.tour.service.impl;

import com.example.tour.entity.Banner;
import com.example.tour.mapper.BannerMapper;
import com.example.tour.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceimpl implements BannerService {
    @Autowired
    BannerMapper bannerMapper;
    @Override
    public List<Banner> bannerSelect() {
      return   bannerMapper.bannerSelect();

    }
}
