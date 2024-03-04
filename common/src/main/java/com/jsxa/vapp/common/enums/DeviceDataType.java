package com.jsxa.vapp.common.enums;


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
public enum DeviceDataType {

    //标准门磁
    homeQuarantine("homeQuarantine", "门磁管理",1741475008L),

    //简易门磁
    simpleDoorLock("simpleDoorLock","门磁管理", 1741475009L),

    //燃气
    gas("gas", "燃气管理",1741475010L),

    //烟雾
    smog("smog", "烟雾管理",1741475011L),

    //铁路
    railway("railway", "铁路预警",1741475012L),

    //高温
    thermography("thermography", "高温预警",1741475013L),

    //水位超限
    waterOverrun("waterOverrun", "水位监测",1741475014L),

    //水位入侵
    waterIpc("waterIpc", "水位监测",1741475015L),

    //环境监测
    environment("environment", "环境监测",1741475016L),

    //智慧城管
    cityManagement("cityManagement", "智慧城管",1741475017L),

    //消防通道
    fireIllegal("fireIllegal", "消防通道违停",1741475018L),

    //高空抛物
    highAltitude("highAltitude", "高空抛物",1741475019L),

    //智慧水表
    waterMeter("waterMeter", "智慧水表",1741475020L),

    //智慧电表
    eleMeter("eleMeter", "智慧电表",1741475021L),

    eleWatch("eleWatch", "电子手表",1741475026L),

    interPhone("interPhone", "对讲机",1741475025L),

    //智慧井盖
    manholeCover("manholeCover", "智慧井盖",1741475022L),

    //配电室
    humitureSensor("humitureSensor", "配电室",1741475023L),

    //水流开关
    waterFlow("waterFlow", "水流开关",1741475024L),

    //智慧床垫
    mattress("mattress", "智慧床垫",1741475027L),

    //智能挂件
    pendant("pendant", "智能挂件",1741475028L),

    //跌倒报警器
    fall("fall", "跌倒报警器",1741475029L),

    //防攀爬
    climb("climb", "防攀爬",1741475030L),

    //电梯梯控
    ladder("ladder", "电梯梯控",1741475031L),

    //人脸
    face("face", "人脸",1741475032L),

    //报警柱
    alarmColumn("alarmColumn ", "报警柱",1741475033L),

    //定位对讲机
    intercom("intercom ", "定位对讲机",1741475034L);



    //1.枚举属性
    private String code;
    private String name;
    private Long value;

    //2.构造器
    DeviceDataType(String code,String name, Long value) {
        this.code = code;
        this.name = name;
        this.value = value;
    }

    //3.code属性的get set
    public String getCode() {
        return code;
    }

    public DeviceDataType setCode(String code) {
        this.code = code;
        return this;
    }

     //3.code属性的get set
    public String getName() {
        return name;
    }

    public DeviceDataType setName(String name) {
        this.name = name;
        return this;
    }


    //4.value属性的get set
    public Long getValue() {
        return value;
    }

    public DeviceDataType setValue(Long value) {
        this.value = value;
        return this;
    }

    //5.获取全部枚举
    public static List<DeviceDataType> getAllEnum() {
        List<DeviceDataType> list = Lists.newArrayList();
        list.addAll(Arrays.asList(values()));
        return list;
    }

    //6.通过value获取枚举
    public static DeviceDataType getEnumByCode(String code) {
        DeviceDataType result = null;
        for (DeviceDataType statusEnum : getAllEnum()) {
            if (statusEnum.code.equals(code)) {
                result = statusEnum;
                break;
            }
        }
        return result;
    }

    public static DeviceDataType getEnumByValue(Long value) {
        DeviceDataType result = null;
        for (DeviceDataType statusEnum : getAllEnum()) {
            if (statusEnum.value.equals(value)) {
                result = statusEnum;
                break;
            }
        }
        return result;
    }
}
