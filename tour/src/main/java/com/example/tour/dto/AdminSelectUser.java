package com.example.tour.dto;

import lombok.Data;

@Data
public class AdminSelectUser {
    private String condition;
    //页码
    private int page;

    //每页记录数
    private int pageSize;

}
