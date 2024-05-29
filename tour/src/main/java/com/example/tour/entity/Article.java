package com.example.tour.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
   private long id;
   private  String userId;//发布人id
   private String title;//文章题目
   private String content;//文章内容
   private String url;//文章背景图
   private int likes;//点赞数
   private int provinceId;//文章对应的省份id
   private int scenicSpotId;//文章对应的景点id

   //@DateTimeFormat(pattern ="yyyy-MM-dd")
   private LocalDateTime createTime;//发布时间


}
