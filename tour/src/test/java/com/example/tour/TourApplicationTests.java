package com.example.tour;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TourApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void test(){
        String url="https://cqwmmmm.oss-cn-hangzhou.aliyuncs.com/0619fbe7-9ec8-43ff-9f21-deb2d30babd5.jpg";
        String[] arr=url.split("/");
        System.out.println(arr[3]);

    }

}
