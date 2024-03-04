package com.jsxa.vapp.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtil {

    //验证邮箱地址合法
    public static boolean checkEmailAddress(String emailAddress){
        if(ObjUtil.isEmpty(emailAddress)){
            return false;
        }

        String patternStr = "[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9]+)+";

        Pattern emailPattern = Pattern.compile(patternStr);
        Matcher match = emailPattern.matcher(emailAddress);
        boolean isMatch = match.matches();
        if(isMatch){
            return true;
        }
        return false;
    }

/*  public static void main(String[] args) {
  }*/
}
