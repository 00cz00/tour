package com.example.tour.dto;

import lombok.Data;

@Data
public class ArticlePageQueryDTO {
    //页码
    private int page;

    //每页记录数
    private int pageSize;
    //
    private int offset;

    //查询条件
    private String condition;
    //最新或者最热
    private String searchBy;
}
