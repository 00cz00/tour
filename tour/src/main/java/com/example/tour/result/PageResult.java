package com.example.tour.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

//封装分页查询结果
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult implements Serializable {
    //总数
    private long total;
    //结果
    private List record;
}
