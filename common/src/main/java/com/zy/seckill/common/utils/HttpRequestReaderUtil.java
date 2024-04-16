package com.zy.seckill.common.utils;

import cn.jiguang.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestReaderUtil {


    // 1.读取为字符串
    public static String readAsString(HttpServletRequest request,String charset) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try
        {
            ServletInputStream inputStream = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream, charset);
            br = new BufferedReader(isr);

            //br = request.getReader();
            String str;
            while ((str = br.readLine()) != null)
            {
                sb.append(str);
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != br)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        String s = sb.toString();
        return s;
    }

    // 2.读取为map
    public static Map<String,Object> readAsMap(HttpServletRequest request) {

        Map<String,Object> params = new HashMap();
        BufferedReader br;
        try {
            br = request.getReader();
            String str, wholeStr = "";
            while((str = br.readLine()) != null){
                wholeStr += str;
            }
            if(StringUtils.isNotEmpty(wholeStr)){
                params = JSON.parseObject(wholeStr,Map.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return params;
    }

    //3.读取为字符串
    public static void ReadAsChars(HttpServletRequest request)
    {
        InputStream is = null;
        try
        {
            is = request.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] b = new byte[4096];
            for (int n; (n = is.read(b)) != -1;)
            {
                sb.append(new String(b, 0, n));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != is)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    // 4.读取为二进制
    public static byte[] readAsBytes(HttpServletRequest request)
    {

        int len = request.getContentLength();
        byte[] buffer = new byte[len];
        ServletInputStream in = null;

        try
        {
            in = request.getInputStream();
            in.read(buffer, 0, len);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != in)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }

    public static Map<String,Object> getHeaderAsMap(HttpServletRequest request) {
        Map<String,Object> params = new HashMap();
        Enumeration<String> e = request.getHeaderNames(); //获取请求头信息的所有的key。
        //遍历所有的键
        while(e.hasMoreElements()){
            String key = e.nextElement();
            //根据键取值
            String value = request.getHeader(key);  //根据键取出对应的值（请求头的信息）
            params.put(key,value);
        }
        return params;
    }

}
