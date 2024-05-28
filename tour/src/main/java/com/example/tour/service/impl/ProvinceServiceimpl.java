package com.example.tour.service.impl;

import com.example.tour.entity.Province;
import com.example.tour.mapper.ProvinceMapper;
import com.example.tour.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceimpl implements ProvinceService {
    @Autowired
    private ProvinceMapper provinceMapper;
    @Override
    public List<Province> getAll() {
        List<Province> provinceList=provinceMapper.getAll();
        return provinceList;
    }
}
