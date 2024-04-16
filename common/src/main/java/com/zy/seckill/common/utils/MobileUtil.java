package com.zy.seckill.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileUtil {

    //验证手机号合法
    public static boolean checkMobileNumber(String mobile){
        if(mobile == null){
            return false;
        }
        if (mobile.length() != 11)
        {
            return false;
        }else{
            /**
             * 移动号段正则表达式
             */
            String pat1 = "^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8]))\\d{8}|(1705)\\d{7}$";
            /**
             * 联通号段正则表达式
             */
            String pat2  = "^((13[0-2])|(145)|(15[4-6])|(176)|(18[5,6]))\\d{8}|(1709)\\d{7}$";
            /**
             * 电信号段正则表达式
             */
            String pat3  = "^((133)|(153)|(16[0-9])|(17[0-9])|(18[0,1,9])|(19[0-9])|(149))\\d{8}$";
            /**
             * 虚拟运营商正则表达式
             */
            String pat4 = "^((170))\\d{8}|(1718)|(1719)\\d{7}$";

            Pattern pattern1 = Pattern.compile(pat1);
            Matcher match1 = pattern1.matcher(mobile);
            boolean isMatch1 = match1.matches();
            if(isMatch1){
                return true;
            }
            Pattern pattern2 = Pattern.compile(pat2);
            Matcher match2 = pattern2.matcher(mobile);
            boolean isMatch2 = match2.matches();
            if(isMatch2){
                return true;
            }
            Pattern pattern3 = Pattern.compile(pat3);
            Matcher match3 = pattern3.matcher(mobile);
            boolean isMatch3 = match3.matches();
            if(isMatch3){
                return true;
            }
            Pattern pattern4 = Pattern.compile(pat4);
            Matcher match4 = pattern4.matcher(mobile);
            boolean isMatch4 = match4.matches();
            if(isMatch4){
                return true;
            }
            return false;
        }
    }

    //获取脱敏手机号
    public static String getDesensitizationMobileNumber(String mobileNumber){
        if(ObjUtil.isEmpty(mobileNumber)){
            return "";
        }
        if(!checkMobileNumber(mobileNumber)){
            return "xxx******xxxx";
        }
        String mobileNumberPrefix = mobileNumber.substring(0, 3);
        String mobileNumberSuffix = mobileNumber.substring(mobileNumber.length() - 4);
        String mobileNumberNumSimple = mobileNumberPrefix + "******" +mobileNumberSuffix;
        return mobileNumberNumSimple;
    }




     //生成单个的号码
     public static String getPhoneNum() {
         //给予真实的初始号段，号段是在百度上面查找的真实号段
         String[] start = {"133", "149", "153", "173", "177",
                         "180", "181", "189", "199", "130", "131", "132",
                         "145", "155", "156", "166", "171", "175", "176", "185", "186", "166", "134", "135",
                         "136", "137", "138", "139", "147", "150", "151", "152", "157", "158", "159", "172",
                         "178", "182", "183", "184", "187", "188", "198", "170", "171"};

         //随机出真实号段   使用数组的length属性，获得数组长度，
         //通过Math.random（）*数组长度获得数组下标，从而随机出前三位的号段
         String phoneFirstNum = start[(int) (Math.random() * start.length)];
         //随机出剩下的8位数
         String phoneLastNum = "";
         //定义尾号，尾号是8位
         final int LENPHONE = 8;
         //循环剩下的位数
         for (int i = 0; i < LENPHONE; i++) {
                 //每次循环都从0~9挑选一个随机数
                 phoneLastNum += (int) (Math.random() * 10);
             }
         //最终将号段和尾数连接起来
         String phoneNum = phoneFirstNum + phoneLastNum;
         return phoneNum;
     }


/*  public static void main(String[] args) {
        String num = getPhoneNum();
      System.out.println("随机手机号:"+num);
  }*/
}
