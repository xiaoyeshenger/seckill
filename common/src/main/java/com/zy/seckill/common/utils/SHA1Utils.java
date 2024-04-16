package com.zy.seckill.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Classname SHA1Utils
 * @Description TODO
 * @Date 2023-11-10 16:47
 * @Created by Administrator
 */
public class SHA1Utils {

    public static String calculateSHA1(String input) {
        try {
            // 获取SHA-1 MessageDigest实例
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 计算输入字符串的SHA-1哈希值
            byte[] digest = md.digest(input.getBytes());
            // 将哈希值转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            // 返回十六进制表示的SHA-1哈希值
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
