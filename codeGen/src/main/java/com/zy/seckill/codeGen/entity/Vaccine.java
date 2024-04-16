package com.zy.seckill.codeGen.entity;

import io.swagger.annotations.ApiModelProperty;
import javax.annotation.Generated;

public class Vaccine {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long orgId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String orgName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String manufacturer;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String batchNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer totalDose;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String doseInterval;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer stock;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String oosUrl;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer orderNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte status;

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
    public Long getOrgId() {
        return orgId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getOrgName() {
        return orgName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getName() {
        return name;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getManufacturer() {
        return manufacturer;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getBatchNumber() {
        return batchNumber;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber == null ? null : batchNumber.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getTotalDose() {
        return totalDose;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTotalDose(Integer totalDose) {
        this.totalDose = totalDose;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDoseInterval() {
        return doseInterval;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDoseInterval(String doseInterval) {
        this.doseInterval = doseInterval == null ? null : doseInterval.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getStock() {
        return stock;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getOosUrl() {
        return oosUrl;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setOosUrl(String oosUrl) {
        this.oosUrl = oosUrl == null ? null : oosUrl.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getOrderNum() {
        return orderNum;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getStatus() {
        return status;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setStatus(Byte status) {
        this.status = status;
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