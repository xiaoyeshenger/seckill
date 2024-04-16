package com.zy.seckill.common.utils;

import java.lang.reflect.Field;
import java.util.*;

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

    /**
     * 通过value获取map(如果相同的value可能对应不同的key，所以一个value能够获取到多个key)
     */
    public static Object getKeyList(Map map, Object value){
        List<Object> keyList = new ArrayList<>();
        for(Object key: map.keySet()){
            if(map.get(key).equals(value)){
                keyList.add(key);
            }
        }
        return keyList;
    }

    /**
     * 通过value获取map(一个value对应一个key的情况)
     */
    public static Object getKey(Map map, Object value){
        Object mapKey = null;
        for(Object key: map.keySet()){
            if(map.get(key).equals(value)){
                mapKey = key;
            }
        }
        return mapKey;
    }

}
