package com.example.tour.controller;

import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.entity.ScenicSpot;
import com.example.tour.entity.ScenicSpotPic;
import com.example.tour.result.Result;
import com.example.tour.service.ScenicSpotService;
import com.example.tour.service.impl.ScenicSpotServiceimpl;
import com.example.tour.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/scenicspot")
public class ScenicSpotController {
    @Autowired
    private ScenicSpotService scenicSpotServiceimpl;

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

    //根据景点id删除景点
    @DeleteMapping("/delete")
    public Result deleteById(String id){
        log.info("删除的景点id："+id);
        scenicSpotServiceimpl.delete(id);

        return Result.success();
    }

    //根据id给景点点赞
    @PutMapping("/like")
    public Result like(String sceneSpotId, ServletRequest servletRequest){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        scenicSpotServiceimpl.like(sceneSpotId,userId);
        return Result.success("点赞成功");
    }

    //根据id取消景点点赞

    @PostMapping("/abolishlike")
    public Result abolishLike(String sceneSpotId,ServletRequest servletRequest ){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        scenicSpotServiceimpl.abolishLike(sceneSpotId,userId);
        return Result.success();
    }


}
