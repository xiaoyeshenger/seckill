package com.zy.seckill.common.utils;

import cn.jiguang.common.utils.StringUtils;
import lombok.SneakyThrows;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtils {

    //1.删除字符串中指定的字符
    public static String deleteString(String str, char delChar) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != delChar) {
                stringBuffer.append(str.charAt(i));
            }
        }
        return stringBuffer.toString();
    }

    //2.截取字符串中数字
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    //3.截取字符串中的非数字
    public static String splitNotNumber(String content) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static int countChinese(String str) {
        int count = 0;
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count = count + 1;
            }
        }
        return count;
    }

    public static List<String> getOptionNameStrList(String str) {
        String pat = "[A-Z].";
        Pattern p = Pattern.compile(pat);
        Matcher m = p.matcher(str);
        List<String> optionNameList = new ArrayList<>();
        while (m.find()) {
            String group = m.group();
            if(!optionNameList.contains(group) && group.contains(".")){
                optionNameList.add(group);
            }

        }
        return optionNameList;
    }

    public static String getOptionStr(String str) {
        String pat = "[A].+[\\u4e00-\\u9fa5]{1}|[A-Za-z]{1}+[A-Z].";
        Pattern p = Pattern.compile(pat);
        Matcher m = p.matcher(str);
        String  optionStr = "";
        if (m.find()) {
            optionStr = m.group();
        }
        return optionStr;
    }



    /*
     * @Author: zhangyong
     * description: 将十六进制的字符串转换成字节数组
     * @Date: 2020-12-02 16:34
     * @Param:
     * @Return:
     */
    public static byte[] hexStrToByteArrs(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            return null;
        }

        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        int index = 0;

        byte[] bytes = new byte[len / 2];

        while (index < len) {
            String sub = hexString.substring(index, index + 2);
            bytes[index / 2] = (byte) Integer.parseInt(sub, 16);
            index += 2;
        }

        return bytes;
    }

    /*
     * @Author: zhangyong
     * description: 数组转换成十六进制字符串
     * @Date: 2020-12-02 16:34
     * @Param:
     * @Return:
     */
    public static final String bytesToHexStr(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
            // 在这里故意追加一个逗号便于最后的区分
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * byte[]数组转换为16进制的字符串
     *
     * @param bytes 要转换的字节数组
     * @return 转换后的结果
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 16进制表示的字符串转换为字节数组
     *
     * @param hexString 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }

    public static String byteArrayToStr(byte[] bytes) {
        String res = "";
        try {
            res = new String(bytes,"UTF-8");
            //System.out.println(res);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return res;
    }

    //脱敏数据，只保留最后几位数据()
    public static String getDesStr(String str,Integer digit){
        if(ObjUtil.isEmpty(str)){
            return "";
        }

        if(str.length() <= digit){
            return "**" + str;
        }

        String strSuffix = str.substring(str.length() - digit, str.length());
        String strSimple = "**" +strSuffix;
        return strSimple;
    }


    //字符串转换为Ascii
    public static String stringToAscii(String value)
    {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                sbu.append((int)chars[i]).append(",");
            }
            else {
                sbu.append((int)chars[i]);
            }
        }
        return sbu.toString();

    }

    //Ascii转换为字符串
    public static String asciiToString(String value)
    {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

    //字符串转换unicode
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    //unicode转字符串
    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }

        return string.toString();

    }


    @SneakyThrows
    public static String convertToGBK(String sourceStr){
        String gbk = new String(sourceStr.toString().getBytes("GBK"));
        return gbk;
    }

    @SneakyThrows
    public static String convertToUTF8(String sourceStr){
        String utf8 = new String(sourceStr.toString().getBytes("UTF-8"));
        return utf8;
    }

    //将字符串按指定的位数分割并返回列表
    public static List<String> digitSegStr(String original,int digit) {

        List<String> strList = new ArrayList<>();
        for (int i = 0; i < original.length(); i += digit) {
            String segment = original.substring(i, i + digit);
            strList.add(segment);
        }
        return strList;
    }


/*    public static void main(String[] args) throws Exception {


        List<String> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");
        strList.add("c");
        strList.add("d");
        strList.add("e");
        System.out.println(strList.toString());
        strList.remove(0);
        System.out.println(strList.toString());

    }*/
}
