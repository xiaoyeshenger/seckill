package com.zy.seckill.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtil {

    /**
     * 得到 全拼
     *
     * @param src
     * @return
     */
    public static String getPinYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        //t3是全部的拼音，不带声调
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);

        //format是全部的拼音并且带声调
        HanyuPinyinOutputFormat format= new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);



        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (Character.toString(t1[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
//					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], format);
                    t4 += t2[0]+" ";
                } else {
                    t4 += Character.toString(t1[i]);
                }
            }
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    /**
     * 得到中文首字母大写
     *
     * @param str
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String getPinYinHeadChar(String str){

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert.toUpperCase();
    }

    /**
     * 得到中文首字母小写
     *
     * @param str
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String getPinYinHeadCharLower(String str){

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert.toUpperCase();
    }

    /**
     * 将字符串转移为ASCII码
     *
     * @param cnStr
     * @return
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // System.out.println(Integer.toHexString(bGBK[i]&0xff));
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

/*    public static void main(String[] args) throws Exception {
        //String cnStr = "我爱Ryan Albert"; //Ryan Albert是我的英文名，这里就不写我的中文名字了。
        String mo = "15208492745"; //Ryan Albert是我的英文名，这里就不写我的中文名字了。

        String password = UUIDUtil.getUUID(4)+mo.substring(7);
        System.out.println("password::"+password);
    }*/
}