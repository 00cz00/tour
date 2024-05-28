package com.example.tour.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class ScenicSpotLike {
    private  int id;
    private int userId;
    private int scenicSpotId;
}
