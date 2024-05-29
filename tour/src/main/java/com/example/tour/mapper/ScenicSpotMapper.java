package com.example.tour.mapper;

import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.entity.ScenicSpot;
import com.example.tour.entity.ScenicSpotPic;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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


}
