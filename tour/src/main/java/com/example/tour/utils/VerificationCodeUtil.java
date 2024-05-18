package com.example.tour.utils;

import java.util.Random;


public class VerificationCodeUtil {
    private static final int CODE_LENGTH = 6;
    private static final Random RANDOM = new Random();

    public static String generateCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(RANDOM.nextInt(10)); // 添加一位随机数字
        }
        return code.toString();
    }
}
