package com.example.tour.mapper;

import com.example.tour.entity.ScenicSpot;
import com.example.tour.entity.ScenicSpotLike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ScenicSpotLikeMapper {
    @Insert("insert into tour.scenic_spot_like(user_id, scenic_spot_id) values(#{userId},#{scenicSpotId})")
    void insert(String scenicSpotId, String userId);

    //根据景点id删除对应全部点赞数据
    @Delete("delete from tour.scenic_spot_like where scenic_spot_id=#{id}")
    public void deleteBySceneSpotId(String id) ;

    @Delete("delete from tour.scenic_spot_like where scenic_spot_id=#{scenicSpotId} and user_id=#{userId}")
    void deleteBySIdAndUId(String scenicSpotId, String userId);
   @Select("select * from tour.scenic_spot_like where user_id=#{userId} and scenic_spot_id=#{id}")
    ScenicSpotLike getByBoth(String id, String userId);
}
