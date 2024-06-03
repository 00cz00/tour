package com.example.tour.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationCode  implements Serializable {
    private String code;
    private LocalDateTime dateTime;
}
