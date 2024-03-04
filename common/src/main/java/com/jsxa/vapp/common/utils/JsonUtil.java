package com.jsxa.vapp.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class JsonUtil {

    public static String readJsonFile(File jsonFile) {
        String jsonStr = "";
        try {
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();

            return jsonStr;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isJsonObject(String content) {
             if(ObjUtil.isEmpty(content))
                     return false;
             try {
                 JSONArray jsonArray = JSONObject.parseArray(content);
                 return true;
                 } catch (Exception e) {
                     return false;
                 }
         }


}
