package com.jsxa.vapp.common.utils;


import java.lang.reflect.Field;
import java.util.*;

public class ParamBuildUtil {
    public static Map<String, Object> buildParam(Object obj) {
        Map<String, Object> param = new HashMap<>();
        if (CommonUtils.isNotEmpty(obj)) {
            // 得到类对象
            Class clz = obj.getClass();
            /* 得到类中的所有属性集合 */
            Field[] fs = clz.getDeclaredFields();
            List<Field> fieldsList = new ArrayList<Field>();
            while (clz != null) {  // 遍历所有父类字节码对象
                Field[] declaredFields = clz.getDeclaredFields();
                fieldsList.addAll(Arrays.asList(declaredFields));  //将`Filed[]`数组转换为`List<>`然后再将其拼接至`ArrayList`上
                clz = clz.getSuperclass();  // 获得父类的字节码对象
            }
            for (Field f : fieldsList) {
                // 设置些属性是可以访问的
                f.setAccessible(true);
                Object val = new Object();
                try {
                    val = f.get(obj);
                    // 得到此属性的值设置键值
                    if (CommonUtils.isNotEmpty(val)) {
                        param.put(f.getName(), val);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (param.containsKey("pageIndex") && param.containsKey("pageSize") && (int) param.get("pageIndex") > 0) {
                param.put("offset", ((int) param.get("pageIndex") - 1) * (int) param.get("pageSize"));
                param.put("limit", param.get("pageSize"));
            }
        }
        return param;
    }
}
