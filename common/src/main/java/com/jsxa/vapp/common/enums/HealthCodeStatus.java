package com.jsxa.vapp.common.enums;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public enum HealthCodeStatus {

    NotQuery(100,"NotQuery","未查询到"),
    Green(1,"Green","绿码"),
    Orange(11,"Orange","黄码"),
    Red(31,"Red","红码"),
    Without (0,"Without","无码");

    //1.枚举属性
    private Integer healthCode;
    private String code;
    private String value;

    //2.构造器
    HealthCodeStatus(Integer healthCode, String code, String value){
        this.healthCode = healthCode;
        this.code = code;
        this.value = value;
    }


    //3.code属性的get set
    public Integer getHealthCode() {
        return healthCode;
    }

    public HealthCodeStatus setHealthCode(Integer healthCode) {
        this.healthCode = healthCode;
        return this;
    }

    //3.code属性的get set
    public String getCode() {
        return code;
    }

    public HealthCodeStatus setCode(String code) {
        this.code = code;
        return this;
    }

    //4.value属性的get set
    public String getValue() {
        return value;
    }

    public HealthCodeStatus setValue(String value) {
        this.value = value;
        return this;
    }

    //5.获取全部枚举
    public static List<HealthCodeStatus> getAllEnum() {
        List<HealthCodeStatus> list = Lists.newArrayList();
        list.addAll(Arrays.asList(values()));
        return list;
    }

    //6.通过code获取枚举
    public static HealthCodeStatus getEnumByCode(String code) {
        HealthCodeStatus result = null;
        for (HealthCodeStatus statusEnum : getAllEnum()) {
            if (statusEnum.code.equals(code)) {
                result = statusEnum;
                break;
            }
        }
        return result;
    }

    //6.通过code获取枚举
    public static HealthCodeStatus getEnumByHealthCode(Integer healthCode) {
        HealthCodeStatus result = null;
        for (HealthCodeStatus statusEnum : getAllEnum()) {
            if (statusEnum.healthCode.equals(healthCode)) {
                result = statusEnum;
                break;
            }
        }
        return result;
    }

}
