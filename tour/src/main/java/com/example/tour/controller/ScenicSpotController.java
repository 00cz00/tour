package com.example.tour.controller;

import com.example.tour.dto.ScenicSpotPageQueryDTO;
import com.example.tour.entity.ScenicSpot;
import com.example.tour.entity.ScenicSpotPic;
import com.example.tour.result.Result;
import com.example.tour.service.ScenicSpotService;
import com.example.tour.service.impl.ScenicSpotServiceimpl;
import com.example.tour.utils.JwtUtils;
import com.example.tour.vo.ArticlePageQueryVO;
import com.example.tour.vo.ScenicSpotVO;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/scenicSpot")
public class ScenicSpotController {
    @Autowired
    private ScenicSpotService scenicSpotServiceimpl;
    @Autowired
    private RedisTemplate redisTemplate;
    @PostMapping("/page")
    //景点分页查询
    public Result<List<ScenicSpotVO>> Page(@RequestBody ScenicSpotPageQueryDTO scenicSpotPageQueryDTO, ServletRequest servletRequest){

        log.info("查询的景点：{}",scenicSpotPageQueryDTO);
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String userId = "null";

        String jwt = req.getHeader("jwt");

        log.info("jwt撒大大:" + jwt);
        try {
            Claims claims = JwtUtils.parserJwt(jwt);
            userId = (String) claims.get("id");

            log.info("啊哒哒哒userid={}",userId);

        } catch (Exception e) {

        }finally {
            List<ScenicSpotVO> scenicSpotVOList=scenicSpotServiceimpl.page(scenicSpotPageQueryDTO,userId);


            return Result.success(scenicSpotVOList);

        }

    }

    //景点具体内容查询
    @GetMapping("/detail/{sceneSpotId}")
    public  Result<ScenicSpotVO> detial(@PathVariable String sceneSpotId,ServletRequest servletRequest){
        log.info("查询的景点id：{}",sceneSpotId);



        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String userId = "null";

        String jwt = req.getHeader("jwt");

        log.info("jwt撒大大:" + jwt);
        try {
            Claims claims = JwtUtils.parserJwt(jwt);
            userId = (String) claims.get("id");

            log.info("啊哒哒哒userid={}",userId);

        } catch (Exception e) {

        }finally {

            ScenicSpotVO scenicSpotVO=scenicSpotServiceimpl.getDetial(sceneSpotId,userId);

            System.out.println("asdasdas");

            System.out.println(scenicSpotVO);

            return Result.success(scenicSpotVO);
        }

    }

    //根据景点id删除景点
    @DeleteMapping("/delete")
    public Result deleteById(String id){
        log.info("删除的景点id："+id);
        scenicSpotServiceimpl.delete(id);

        return Result.success();
    }

    //根据id给景点点赞
    @PostMapping("/like/{scenicSpotId}")
    public Result like(@PathVariable String scenicSpotId, ServletRequest servletRequest){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        scenicSpotServiceimpl.like(scenicSpotId,userId);
        return Result.success("点赞成功");
    }

    //根据id取消景点点赞

    @PostMapping("/abolishLike/{scenicSpotId}")
    public Result abolishLike(@PathVariable String scenicSpotId,ServletRequest servletRequest ){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        scenicSpotServiceimpl.abolishLike(scenicSpotId,userId);
        return Result.success();
    }

    @GetMapping("/list/{id}")
    //根据省份id查景点
    public Result<List<ScenicSpot>> getByProvinceId(@PathVariable String id){
        List<ScenicSpot> scenicSpotList=scenicSpotServiceimpl.getByPId(id);
        return Result.success(scenicSpotList);

    }

  /*  //修改景点信息
    @PostMapping("/updata/{id}")
    public Result updata(@RequestBody ScenicSpot scenicSpot){



    }*/



}
