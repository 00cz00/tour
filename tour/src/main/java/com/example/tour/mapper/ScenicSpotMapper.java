package com.example.tour.mapper;

import com.example.tour.dto.AdminScenicSpotPageDTO;
import com.example.tour.dto.AdminScenicSpotUpdateDTO;
import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.entity.ScenicSpot;
import com.example.tour.entity.ScenicSpotPic;
import com.example.tour.result.PageResult;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScenicSpotMapper {
     //景点分页查询

     Page<ScenicSpot> page(ScenicSpotPageQueryDTO scenicSpotPageQueryDTO);

     @Delete("delete from scenic_spot where id=#{id}")
    void deleteById(String id);

     @Update("update tour.scenic_spot set likes=(likes+1) where id=#{sceneSpotId}")
    void Like(String sceneSpotId);

    @Update("update tour.scenic_spot set likes=(likes-1) where id=#{sceneSpotId}")
    void abolishLike(String sceneSpotId);

    @Select("select * from tour.scenic_spot where province_id=#{id}")
    List<ScenicSpot> getByPId(String id);

    @Select("select * from tour.scenic_spot where id=#{id}")
    ScenicSpot getById(String id);

    @Update("update tour.scenic_spot set likes=#{likes},scene_rollcall=#{sceneRollCall},province_id=#{provinceId},url=#{url},video_url=#{videoUrl},content=#{content} where id=#{id}")
    void AdminScenicSpotUpdate(AdminScenicSpotUpdateDTO adminScenicSpotUpdateDTO);

    //添加景点
    @Insert("insert into tour.scenic_spot(scene_rollcall, province_id, likes, url, video_url, content) values(#{sceneRollCall}," +
            "#{provinceId},#{likes},#{url},#{videoUrl},#{content}) ")
    void insert(AdminScenicSpotUpdateDTO adminScenicSpotUpdateDTO);

    //管理端分页查询
    Page<ScenicSpot> adminScenicSpotPage(AdminScenicSpotPageDTO adminScenicSpotPageDTO);

    //统计全部景点
   // @Select("select count(*) from tour.scenic_spot")
    int countAll(String provinceId);
}
