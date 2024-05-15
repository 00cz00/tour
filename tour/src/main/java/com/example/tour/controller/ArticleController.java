package com.example.tour.controller;

import com.example.tour.dto.ArticlePageQueryDTO;
import com.example.tour.result.Result;
import com.example.tour.service.impl.ArticleServiceimpl;
import com.example.tour.utils.JwtUtils;
import com.example.tour.vo.ArticlePageQueryVO;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleServiceimpl articleServiceimpl;

    //文章分页查询
    @PostMapping("/page")
    public Result<List<ArticlePageQueryVO>> page(@RequestBody ArticlePageQueryDTO articlePageQueryDTO, ServletRequest servletRequest){
        log.info("要查询的文章信息：{}",articlePageQueryDTO);
        String userId="null";

        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String jwt = req.getHeader("jwt");
        log.info("jwt撒大大:"+jwt);
        if (jwt != null) {
            Claims claims = JwtUtils.parserJwt(jwt);
             userId = (String) claims.get("id");
        }


        List<ArticlePageQueryVO> articlePageQueryVOList=articleServiceimpl.page(articlePageQueryDTO,userId);

        return Result.success(articlePageQueryVOList);

    }

}
