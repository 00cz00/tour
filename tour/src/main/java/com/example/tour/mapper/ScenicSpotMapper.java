package com.example.tour.mapper;

import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.entity.ScenicSpot;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ScenicSpotMapper {
     //景点分页查询

     Page<ScenicSpot> page(ScenicSpotPageQueryDTO scenicSpotPageQueryDTO);

     @Delete("delete from tour.scenic_spot where id=#{id}")
    void deleteById(String id);

     @Update("update tour.scenic_spot set likes=#{likes+1} where id=#{sceneSpotId}")
    void Like(String sceneSpotId);

    @Update("update tour.scenic_spot set likes=#{likes-1} where id=#{sceneSpotId}")
    void abolishLike(String sceneSpotId);
}
