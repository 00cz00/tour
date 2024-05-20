package com.example.tour.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SceneSpotLikeMapper {
    @Insert("insert into tour.scene_spot_like(user_id, scene_spot_id) values(#{userId},#{sceneSpotId})")
    void insert(String sceneSpotId, String userId);

    //根据景点id删除对应全部点赞数据
    @Delete("delete from tour.scene_spot_like where scene_spot_id=#{id}")
    public void deleteBySceneSpotId(String id) ;
}
