package com.example.tour.controller;

import com.example.tour.dto.ArticlePageQueryDTO;
import com.example.tour.result.PageResult;
import com.example.tour.result.Result;
import com.example.tour.service.ArticleService;
import com.example.tour.service.impl.ArticleServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleServiceimpl articleServiceimpl;

    //文章分页查询
    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody ArticlePageQueryDTO articlePageQueryDTO){
        log.info("要查询的文章信息：{}",articlePageQueryDTO);

        PageResult pageResult=articleServiceimpl.page(articlePageQueryDTO);

        return Result.success(pageResult);

    }

}
