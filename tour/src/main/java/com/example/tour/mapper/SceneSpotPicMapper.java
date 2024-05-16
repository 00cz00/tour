package com.example.tour.mapper;


import com.example.tour.entity.ScenicSpotPic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SceneSpotPicMapper {
    //根据景点id查询对应图片及文字
    @Select("select * from tour.scenic_spot_pic where scene_spot_id=#{id}")
    List<ScenicSpotPic> getBySceneSpotId(String id);
}
