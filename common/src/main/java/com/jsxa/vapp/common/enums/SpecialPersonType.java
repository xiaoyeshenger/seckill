package com.jsxa.vapp.common.enums;


import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * @Author zhangyong
 * @Description //特殊人员类型（包含人员类型和报警级别）
 * @Date 下午 5:31 2019/7/30 0030
 * @Param
 * @return
 **/

public enum SpecialPersonType {

    She_Du("She_Du","涉毒人员","1",1,1),


    She_Wen("She_Wen","涉稳人员","1",1,2),


    Zai_Tao("Zai_Tao","在逃人员","1",1,3),

    Fan_Zui_Qian_ke("Fan_Zui_Qian_ke","犯罪前科","1",1,4),

    Shang_Fang_Ren_Yuan("Shang_Fang_Ren_Yuan","上访人员","1",1,5),

    Dao_Qie("Dao_Qie","盗窃人员","1",1,6),

    She_Qu_Fu_Xing("She_Qu_Fu_Xing","社区服刑人员","1",1,7);



    //1.枚举属性
    private String code;
    private String value;
    private String alarmType;
    private Integer alarmLevel;
    private Integer order;

    //2.构造器
    SpecialPersonType(String code, String value, String alarmType, Integer alarmLevel, Integer order) {
        this.code = code;
        this.value = value;
        this.alarmType = alarmType;
        this.alarmLevel = alarmLevel;
        this.order = order;
    }

    //3.属性的get set
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setType(String alarmType) {
        this.alarmType = alarmType;
    }

    public Integer getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(Integer alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    //4.获取全部枚举
    public static List<SpecialPersonType> getAllEnum() {
        List<SpecialPersonType> list = Lists.newArrayList();
        list.addAll(Arrays.asList(values()));
        list.sort(Comparator.comparingInt(SpecialPersonType::getOrder));
        return list;
    }

    //5.通过code获取枚举
    public static SpecialPersonType getEnumByCode(String code) {
        SpecialPersonType result = null;
        for (SpecialPersonType statusEnum : getAllEnum()) {
            if (statusEnum.code.equals(code)) {
                result = statusEnum;
                break;
            }
        }
        return result;
    }

    //5.通过value获取枚举
    public static SpecialPersonType getEnumByValue(String value) {
        SpecialPersonType result = null;
        for (SpecialPersonType statusEnum : getAllEnum()) {
            if (statusEnum.value.equals(value)) {
                result = statusEnum;
                break;
            }
        }
        return result;
    }
}
