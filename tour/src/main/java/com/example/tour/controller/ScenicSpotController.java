package com.example.tour.controller;

import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.result.PageResult;
import com.example.tour.result.Result;
import com.example.tour.service.impl.ScenicSpotServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/scenicspot")
public class ScenicSpotController {
    @Autowired
    private ScenicSpotServiceimpl scenicSpotServiceimpl;

    @GetMapping("/page")
    //景点分页查询
    public Result<PageResult> Page(ScenicSpotPageQueryDTO scenicSpotPageQueryDTO){
        log.info("查询的景点：{}",scenicSpotPageQueryDTO);
        PageResult pageResult=scenicSpotServiceimpl.page(scenicSpotPageQueryDTO);



        return Result.success(pageResult);
    }


}
