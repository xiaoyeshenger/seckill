package com.jsxa.vapp.codeGen.entity;

import io.swagger.annotations.ApiModelProperty;
import javax.annotation.Generated;

public class VaccinationSite {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String addressCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long orgId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String orgName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String contactName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String contactMobile;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer orderNum;

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
    public String getName() {
        return name;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAddressCode() {
        return addressCode;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode == null ? null : addressCode.trim();
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
    public String getContactName() {
        return contactName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getContactMobile() {
        return contactMobile;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
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
    public Integer getOrderNum() {
        return orderNum;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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