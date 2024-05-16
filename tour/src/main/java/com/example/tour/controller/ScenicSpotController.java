package com.example.tour.controller;

import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.entity.ScenicSpot;
import com.example.tour.entity.ScenicSpotPic;
import com.example.tour.result.Result;
import com.example.tour.service.impl.ScenicSpotServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/scenicspot")
public class ScenicSpotController {
    @Autowired
    private ScenicSpotServiceimpl scenicSpotServiceimpl;

    @PostMapping("/page")
    //景点分页查询
    public Result<List<ScenicSpot>> Page(@RequestBody ScenicSpotPageQueryDTO scenicSpotPageQueryDTO){
        log.info("查询的景点：{}",scenicSpotPageQueryDTO);

        List<ScenicSpot> scenicSpotList=scenicSpotServiceimpl.page(scenicSpotPageQueryDTO);


        return Result.success(scenicSpotList);
    }
    //景点具体内容查询
    @PostMapping("detial")
    public  Result<List<ScenicSpotPic>> detial(String sceneSoptId){
        log.info("查询的景点id：{}",sceneSoptId);
        List<ScenicSpotPic> scenicSpotPicList=scenicSpotServiceimpl.getDetial(sceneSoptId);

        return Result.success(scenicSpotPicList);
    }



}
