package com.example.tour.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User  implements Serializable {
    private String id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createTime;
    private String url;
}
