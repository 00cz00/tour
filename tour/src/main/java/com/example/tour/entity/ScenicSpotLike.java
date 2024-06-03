package com.example.tour.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class ScenicSpotLike  implements Serializable {
    private  String id;
    private int userId;
    private int scenicSpotId;
}
