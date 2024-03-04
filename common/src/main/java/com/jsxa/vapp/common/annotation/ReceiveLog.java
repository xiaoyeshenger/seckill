package com.jsxa.vapp.common.annotation;

import com.jsxa.vapp.common.enums.BusinessType;
import com.jsxa.vapp.common.enums.OperType;

import java.lang.annotation.*;


//自定义操作日志记录注解(用于service层或其他方法)
//@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReceiveLog {
    //业务操作类型
    BusinessType businessType() default BusinessType.OTHER;

    //操作人类别(PC/APP)
    OperType operType() default OperType.PC;

    //日志名称(默认为空字符串)
    String name();

    //是否保存请求的参数(默认为ture，保存请求参数)
    boolean isSaveRequestData() default true;

    //是否保存响应的参数(默认为false，不保存请求参数)
    boolean isSaveResponseData() default false;
}
