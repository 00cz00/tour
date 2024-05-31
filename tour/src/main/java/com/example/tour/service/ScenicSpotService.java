package com.example.tour.service;

import com.example.tour.dto.AdminScenicSpotPageDTO;
import com.example.tour.dto.AdminScenicSpotUpdateDTO;
import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.entity.ScenicSpot;
import com.example.tour.entity.ScenicSpotPic;
import com.example.tour.result.PageResult;
import com.example.tour.vo.ScenicSpotVO;


import java.util.List;

public interface ScenicSpotService {
    //景点分页查询
    List<ScenicSpotVO> page(ScenicSpotPageQueryDTO scenicSpotPageQueryDTO, String userId);

    //根据id查询景点详情
    ScenicSpotVO getDetial(String id,String userId);

    //根据id删除景点
    void delete(String id);

    //根据id点赞景点
    void like(String sceneSpotId,String userId);

    //根据id取消点赞
    void abolishLike(String sceneSpotId, String userId);
    //根据省份id查询景点
    List<ScenicSpot> getByPId(String id);

    void AdminScenicSpotUpdate(AdminScenicSpotUpdateDTO adminScenicSpotUpdateDTO);

    //增加景点
    void add(AdminScenicSpotUpdateDTO adminScenicSpotUpdateDTO);

    //管理端景点分页查询
    PageResult adminScenicSpotPage(AdminScenicSpotPageDTO adminScenicSpotPageDTO);

    //统计全部景点
    int countAll(String provinceId);
}
