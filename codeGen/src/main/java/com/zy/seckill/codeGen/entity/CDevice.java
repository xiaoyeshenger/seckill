package com.zy.seckill.codeGen.entity;

import io.swagger.annotations.ApiModelProperty;
import javax.annotation.Generated;

public class CDevice {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String serviceName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String serviceValue;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String methodName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String methodValue;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String paramStr;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String errorMsg;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String createDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getType() {
        return type;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setType(Long type) {
        this.type = type;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getServiceName() {
        return serviceName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getServiceValue() {
        return serviceValue;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setServiceValue(String serviceValue) {
        this.serviceValue = serviceValue == null ? null : serviceValue.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getMethodName() {
        return methodName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getMethodValue() {
        return methodValue;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMethodValue(String methodValue) {
        this.methodValue = methodValue == null ? null : methodValue.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getParamStr() {
        return paramStr;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setParamStr(String paramStr) {
        this.paramStr = paramStr == null ? null : paramStr.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getErrorMsg() {
        return errorMsg;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCreateDate() {
        return createDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getCreateTime() {
        return createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}