package com.swt301.roms.utils;

import java.util.Random;

public class DataGenerator {
    private static final Random RANDOM = new Random();

    /**
     * Sinh số điện thoại Việt Nam ngẫu nhiên 10 chữ số (đầu 09)
     */
    public static String getRandomPhoneNumber() {
        long suffix = 10000000L + (long) (RANDOM.nextDouble() * 89999999L);
        return "09" + String.valueOf(suffix).substring(1);
    }

    /**
     * Sinh chuỗi ngẫu nhiên kèm timestamp để tránh trùng dữ liệu
     */
    public static String getUniqueString(String prefix) {
        return prefix + "_" + System.currentTimeMillis() % 100000;
    }

    /**
     * Thay thế placeholder {RANDOM} trong chuỗi test data
     */
    public static String resolveRandom(String input) {
        if (input == null) return null;
        if (input.contains("{RANDOM}")) {
            long token = System.currentTimeMillis() % 100000;
            return input.replace("{RANDOM}", String.valueOf(token));
        }
        return input;
    }
}
