package com.zy.seckill.common.enums;


import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/*
 * @Author zhangyong
 * @Description //门口类型
 * @Date 下午 5:31 2019/7/30 0030
 * @Param
 * @return
 **/
public enum GateType{

    //小区大门
    COMMUNITYDOOR("COMMUNITYDOOR","小区大门"),

    //单元门
    UNITDOOR("UNITDOOR","单元门"),

    //车棚门
    SHEDDOOR("SHEDDOOR","车棚门"),

    //围墙
    WALL("WALL","围墙");


    private String code;

    private String value;


    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    GateType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public GateType setCode(String code) {
        this.code = code;
        return this;
    }

    public GateType setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * 获取全部枚举
     */
    public static List<GateType> getAllEnum() {
        List<GateType> list = Lists.newArrayList();
        list.addAll(Arrays.asList(values()));
        return list;
    }


    /**
     * 获取全部枚举值
     *
     */
    public static List<String> getAllEnumCode() {
        List<String> list = Lists.newArrayList();
        for (GateType each : values()) {
            list.add(each.value());
            list.add(getEnumByCode(each.value()).toString());
        }
        return list;
    }


    /**
     * 通过value获取枚举
     */
    public static GateType getEnumByCode(String code) {
        GateType result = null;
        for (GateType statusEnum : getAllEnum()) {
            if (statusEnum.code.equals(code)) {
                result = statusEnum;
                break;
            }
        }
        return result;
    }

    /**
     * 枚举value值
     */
    public String value() {
        return value;
    }

    /**
     * 枚举code值
     */
    public String code() {
        return code;
    }
}
