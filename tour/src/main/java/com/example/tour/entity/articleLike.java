package com.example.tour.entity;

import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class articleLike {
    private String id;
    private String userId;
    private String articleId;
    //private LocalDateTime createTime;
}

