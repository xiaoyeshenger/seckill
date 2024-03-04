package com.jsxa.vapp.common.enums;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public enum PopulationCodeStatus {

    NotQuery(100,"NotQuery","未查询到"),
    Normal (0,"Normal","正常人员"),
    EscapingCriminal (1,"EscapingCriminal","在逃人员"),
    Special(2,"Special","重点人员(肇事肇祸)");
    ;

    //1.枚举属性
    private Integer healthCode;
    private String code;
    private String value;

    //2.构造器
    PopulationCodeStatus(Integer healthCode, String code, String value){
        this.healthCode = healthCode;
        this.code = code;
        this.value = value;
    }


    //3.code属性的get set
    public Integer getHealthCode() {
        return healthCode;
    }

    public PopulationCodeStatus setHealthCode(Integer healthCode) {
        this.healthCode = healthCode;
        return this;
    }

    //3.code属性的get set
    public String getCode() {
        return code;
    }

    public PopulationCodeStatus setCode(String code) {
        this.code = code;
        return this;
    }

    //4.value属性的get set
    public String getValue() {
        return value;
    }

    public PopulationCodeStatus setValue(String value) {
        this.value = value;
        return this;
    }

    //5.获取全部枚举
    public static List<PopulationCodeStatus> getAllEnum() {
        List<PopulationCodeStatus> list = Lists.newArrayList();
        list.addAll(Arrays.asList(values()));
        return list;
    }

    //6.通过code获取枚举
    public static PopulationCodeStatus getEnumByCode(String code) {
        PopulationCodeStatus result = null;
        for (PopulationCodeStatus statusEnum : getAllEnum()) {
            if (statusEnum.code.equals(code)) {
                result = statusEnum;
                break;
            }
        }
        return result;
    }

    //6.通过code获取枚举
    public static PopulationCodeStatus getEnumByHealthCode(Integer healthCode) {
        PopulationCodeStatus result = null;
        for (PopulationCodeStatus statusEnum : getAllEnum()) {
            if (statusEnum.healthCode.equals(healthCode)) {
                result = statusEnum;
                break;
            }
        }
        return result;
    }

}
