package com.example.tour;

import com.example.tour.entity.Article;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.time.LocalDateTime;

@ServletComponentScan
@SpringBootApplication
public class TourApplication {

    public static void main(String[] args) {

      /*  LocalDateTime localDateTime=LocalDateTime.now();
        String a=localDateTime.toString();
        System.out.println(a);
        =a.equals("T");
        */

        SpringApplication.run(TourApplication.class, args);
    }

}
