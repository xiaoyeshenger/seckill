package com.jsxa.vapp.common.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapUtil {

    //通过时间复杂度获取LinkedHashMap中的首位元素
    public <K, V> Map.Entry<K, V> getHead(LinkedHashMap<K, V> map) {
        return map.entrySet().iterator().next();
    }

    //通过反射获取LinkedHashMap中的末尾元素
    public static <K, V> Map.Entry<K, V> getTailByReflection(LinkedHashMap<K, V> map)
            throws NoSuchFieldException, IllegalAccessException {
        Field tail = map.getClass().getDeclaredField("tail");
        tail.setAccessible(true);
        return (Map.Entry<K, V>) tail.get(map);
    }

    //paramsMap  被拷贝对象  resultMap 拷贝后的对象
    public static void mapCopy(Map paramsMap, Map resultMap) {
        if (resultMap == null){
            resultMap = new HashMap();
        }
        if (paramsMap == null){
            return;
        }

        Iterator it = paramsMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            resultMap.put(key, paramsMap.get(key) != null ? paramsMap.get(key) : "");

        }
    }
}
