package com.example.tour.dto;

import lombok.Data;
@Data
public class UserUpdatePasswordDTO{
    private String newPassword;
    private String oldPassword;

}
