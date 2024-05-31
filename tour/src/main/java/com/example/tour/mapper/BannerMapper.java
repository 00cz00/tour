package com.example.tour.mapper;

import com.example.tour.entity.Banner;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BannerMapper {

    @Select("SELECT * FROM banner")
    List<Banner> bannerSelect();

    @Delete("delete from tour.banner where id=#{id}")
    void deldetById(String id);
    @Insert("insert into tour.banner(url) value (#{url})")
    void add(Banner banner);
}
