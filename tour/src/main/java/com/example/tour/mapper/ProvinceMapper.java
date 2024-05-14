package com.example.tour.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProvinceMapper {
    //根据id查询provinceName
    @Select("select province_name from tour.province where id=#{provinceId}")
    String getById(int provinceId);
}
