package com.example.tour.mapper;

import com.example.tour.entity.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProvinceMapper {
    //根据id查询provinceName
    @Select("select province_name from tour.province where id=#{provinceId}")
    String getById(int provinceId);

    @Select("select * from tour.province ")
    List<Province> getAll();

    @Select("select id from tour.province where province_name=#{provinceName}")
    int getByProvinceName(String provinceName);
}
