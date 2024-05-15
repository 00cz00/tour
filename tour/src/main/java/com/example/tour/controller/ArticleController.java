package com.example.tour.controller;

import com.example.tour.dto.ArticlePageQueryDTO;
import com.example.tour.result.Result;
import com.example.tour.service.impl.ArticleServiceimpl;
import com.example.tour.vo.ArticlePageQueryVO;
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
    public Result<List<ArticlePageQueryVO>> page(@RequestBody ArticlePageQueryDTO articlePageQueryDTO){
        log.info("要查询的文章信息：{}",articlePageQueryDTO);

        List<ArticlePageQueryVO> articlePageQueryVOList=articleServiceimpl.page(articlePageQueryDTO);

        return Result.success(articlePageQueryVOList);

    }

}
