package com.example.tour.controller;

import com.example.tour.dto.ArticleDTO;
import com.example.tour.dto.ArticlePageQueryDTO;
import com.example.tour.entity.Article;
import com.example.tour.entity.User;
import com.example.tour.mapper.ArticleMapper;
import com.example.tour.result.Result;
import com.example.tour.service.ArticleService;
import com.example.tour.service.UserService;
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
    @Autowired
    UserService userService;


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
    @PostMapping("/ThumbsUp/{id}")
    public Result ThumbsUp(@PathVariable("id") String id,ServletRequest servletRequest) {
        System.out.println("asdasdasdsa"+id);
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        articleServiceimpl.ThumbsUp(id,userId);
        return Result.success("点赞成功");
    }

    //文章取消点赞
    @PostMapping("/disThumbsUp/{id}")
    public Result disThumbsUp(@PathVariable String id,ServletRequest servletRequest) {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        articleServiceimpl.disThumbsUp(id,userId);
        return Result.success("取消点赞成功");
    }


    //删除文章
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable String id){
        articleServiceimpl.delete(id);
        return Result.success("删除成功");
    }

    //根据文章id查询文章详细内容
    @GetMapping ("/detail/{id}")
    public Result<ArticleDetialVO> getArticleDetial(@PathVariable String id,ServletRequest servletRequest){
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
    //关注的作者发布的文章
    @PostMapping("/follow/page")
    public  Result<List<ArticlePageQueryVO>> followeeArticle(@RequestBody ArticlePageQueryDTO articlePageQueryDTO,ServletRequest servletRequest){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        /*List<User> userList= userService.selectFollowee(userId);
        for (:
             ) {

        }*/

        List<ArticlePageQueryVO> articlePageQueryVOList= articleServiceimpl.followeeArticle(articlePageQueryDTO,userId);


        return  Result.success(articlePageQueryVOList);
    }

    //发布文章
    @PostMapping("/publish")
    public Result publish(@RequestBody ArticleDTO articleDTO,ServletRequest servletRequest){
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        Claims claims = JwtUtils.parserJwt(jwt);
        String userId = (String) claims.get("id");
        articleDTO.setUserId(userId);
        articleServiceimpl.publish(articleDTO);
        return Result.success();
    }

}
