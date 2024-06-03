package com.example.tour.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Collections  implements Serializable {
    private String id;
    private String userId;
    private String articleId;
    //private LocalDateTime createTime;
}
