package com.example.tour.service;

import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.entity.ScenicSpot;
import com.example.tour.entity.ScenicSpotPic;
import com.example.tour.vo.ScenicSpotVO;


import java.util.List;

public interface ScenicSpotService {
    //景点分页查询
    List<ScenicSpotVO> page(ScenicSpotPageQueryDTO scenicSpotPageQueryDTO, String userId);

    //根据id查询景点详情
    List<ScenicSpotPic> getDetial(String id);

    //根据id删除景点
    void delete(String id);

    //根据id点赞景点
    void like(String sceneSpotId,String userId);

    //根据id取消点赞
    void abolishLike(String sceneSpotId, String userId);
    //根据省份id查询景点
    List<ScenicSpot> getByPId(String id);
}
