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
public enum AlarmType {

    Illegal_Dustbin("Illegal_Dustbin", "垃圾箱外逸"),
    Illegal_Material("Illegal_Material", "乱堆物料"),
    Illegal_Manage("Illegal_Manage", "占道经营"),
    Illegal_Stalls("Illegal_Stalls", "非法摆摊"),
    High_Altitude("High_Altitude", "高空抛物");

    //1.枚举属性
    private String code;
    private String value;

    //2.构造器
    AlarmType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    //3.code属性的get set
    public String getCode() {
        return code;
    }

    public AlarmType setCode(String code) {
        this.code = code;
        return this;
    }

    //4.value属性的get set
    public String getValue() {
        return value;
    }

    public AlarmType setValue(String value) {
        this.value = value;
        return this;
    }

    //5.获取全部枚举
    public static List<AlarmType> getAllEnum() {
        List<AlarmType> list = Lists.newArrayList();
        list.addAll(Arrays.asList(values()));
        return list;
    }

    //6.通过value获取枚举
    public static AlarmType getEnumByCode(String code) {
        AlarmType result = null;
        for (AlarmType statusEnum : getAllEnum()) {
            if (statusEnum.code.equals(code)) {
                result = statusEnum;
                break;
            }
        }
        return result;
    }
}
