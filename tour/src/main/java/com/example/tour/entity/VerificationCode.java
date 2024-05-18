package com.example.tour.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationCode {
    private String code;
    private LocalDateTime dateTime;
}
