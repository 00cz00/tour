package com.example.tour.controller;

import com.example.tour.dto.ArticlePageQueryDTO;
import com.example.tour.entity.Article;
import com.example.tour.result.Result;
import com.example.tour.service.ArticleService;
import com.example.tour.service.impl.ArticleServiceimpl;
import com.example.tour.service.impl.UserServiceimpl;
import com.example.tour.utils.JwtUtils;
import com.example.tour.vo.ArticleDetialVO;
import com.example.tour.vo.ArticlePageQueryVO;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleService articleServiceimpl;



    //文章分页查询
    @PostMapping("/page")
    public Result<List<ArticlePageQueryVO>> page(@RequestBody ArticlePageQueryDTO articlePageQueryDTO, ServletRequest servletRequest) {
        log.info("要查询的文章信息：{}", articlePageQueryDTO);
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
                List<ArticlePageQueryVO> articlePageQueryVOList = articleServiceimpl.page(articlePageQueryDTO, userId);

                return Result.success(articlePageQueryVOList);
            }

        }







    //文章点赞
    @PostMapping("/ThumbsUp")
    public Result ThumbsUp(String id,ServletRequest servletRequest) {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        articleServiceimpl.ThumbsUp(id,userId);
        return Result.success("点赞成功");
    }



    //删除文章
    @PostMapping("/delete")
    public Result delete(String id){
        articleServiceimpl.delete(id);
        return Result.success("删除成功");
    }

    //根据文章id查询文章详细内容
    @PostMapping("/detial")
    public Result<ArticleDetialVO> getArticleDetial(String id,ServletRequest servletRequest){
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
            ArticleDetialVO articleDetialVO=articleServiceimpl.getById(id,userId);
            return Result.success(articleDetialVO);
        }



    }
}
