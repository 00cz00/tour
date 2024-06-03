package com.example.tour;

import com.example.tour.entity.Article;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

import java.time.LocalDateTime;

@EnableCaching
@ServletComponentScan
@SpringBootApplication
public class TourApplication {

    public static void main(String[] args) {



        SpringApplication.run(TourApplication.class, args);
    }

}
