package com.zy.seckill.common.utils;

import java.util.regex.Pattern;

//车牌号校验
public class PlateNumUtil {

    public static boolean checkPlateNumber(String content) {
        String carNumRegex = "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1})";
        return Pattern.matches(carNumRegex, content);
    }

/*     public static void main(String[] args) {
        String num = "川A1Q12";
         boolean b = checkPlateNumber(num);
         System.out.println("结果:"+b);
     }*/
}
