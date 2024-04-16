package com.zy.seckill.common.enums;


import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/*
 * @Author wangchao
 * @Description  设备数据类型
 * @Date 2022/12/15 9:11
 * @Param
 * @return
 **/
public enum DevicePushType {

    //标准门磁
    homeQuarantine("homeQuarantine", "门磁管理"),

    //简易门磁
    simpleDoorLock("simpleDoorLock","门磁管理"),

    //燃气
    gas("gas", "燃气管理"),

    //烟雾
    smog("smog", "烟雾管理"),

    //铁路
    railway("railway", "铁路预警"),

    //高温
    thermography("thermography", "高温预警"),

    //水位
    water("water", "水位监测"),

    //环境监测
    environment("environment", "环境监测"),

    //智慧城管
    cityManagement("cityManagement", "智慧城管"),

    //消防通道
    fireIllegal("fireIllegal", "消防通道违停"),

    //高空抛物
    highAltitude("highAltitude", "高空抛物"),

    //智慧水表
    waterMeter("waterMeter", "智慧水表"),

    //智慧电表
    eleMeter("eleMeter", "智慧电表");

    //1.枚举属性
    private String code;
    private String name;

    //2.构造器
    DevicePushType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    //3.code属性的get set
    public String getCode() {
        return code;
    }

    public DevicePushType setCode(String code) {
        this.code = code;
        return this;
    }

     //3.code属性的get set
    public String getName() {
        return name;
    }

    public DevicePushType setName(String name) {
        this.name = name;
        return this;
    }


    //5.获取全部枚举
    public static List<DevicePushType> getAllEnum() {
        List<DevicePushType> list = Lists.newArrayList();
        list.addAll(Arrays.asList(values()));
        return list;
    }

    //6.通过value获取枚举
    public static DevicePushType getEnumByCode(String code) {
        DevicePushType result = null;
        for (DevicePushType statusEnum : getAllEnum()) {
            if (statusEnum.code.equals(code)) {
                result = statusEnum;
                break;
            }
        }
        return result;
    }
}
