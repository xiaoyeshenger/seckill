package com.zy.seckill.common.enums;


import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/*
 * @Author wangchao
 * @Description  推送给银海的设备类型
 * @Date 2022/7/21 15:09
 * @Param
 * @return
 **/
public enum WaterMeterType {

    elderly("老人", 1),
    Idle_House("闲置房屋", 1),
    Group_Rental_Housing("群租房", 3),
    Personnel("重点人员", 4);

    //1.枚举属性
    private String code;
    private Integer value;

    //2.构造器
    WaterMeterType(String code, Integer value) {
        this.code = code;
        this.value = value;
    }

    //3.code属性的get set
    public String getCode() {
        return code;
    }

    public WaterMeterType setCode(String code) {
        this.code = code;
        return this;
    }

    //4.value属性的get set
    public Integer getValue() {
        return value;
    }

    public WaterMeterType setValue(Integer value) {
        this.value = value;
        return this;
    }

    //5.获取全部枚举
    public static List<WaterMeterType> getAllEnum() {
        List<WaterMeterType> list = Lists.newArrayList();
        list.addAll(Arrays.asList(values()));
        return list;
    }

    //6.通过value获取枚举
    public static WaterMeterType getEnumByCode(String code) {
        WaterMeterType result = null;
        for (WaterMeterType statusEnum : getAllEnum()) {
            if (statusEnum.code.equals(code)) {
                result = statusEnum;
                break;
            }
        }
        return result;
    }
}
