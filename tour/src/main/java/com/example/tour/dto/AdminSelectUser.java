package com.example.tour.dto;

import lombok.Data;

@Data
public class AdminSelectUser {
    private String userName;
    //页码
    private int offset;

    //每页记录数
    private int pageSize;
}
