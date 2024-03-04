package com.jsxa.vapp.codeGen.entity;

import io.swagger.annotations.ApiModelProperty;
import javax.annotation.Generated;

public class VaccinationPerson {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte sex;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer age;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String mobile;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String idNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String openId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String address;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String workUnit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long personType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long doseStatus;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String firstdoseId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String firstdoseName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String firstdoseUnit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long firstdoseTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String latestdoseId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String latestdoseName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String latestdoseUnit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long latestdoseTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long updateTime;

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
    public Byte getSex() {
        return sex;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getAge() {
        return age;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAge(Integer age) {
        this.age = age;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getMobile() {
        return mobile;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getIdNumber() {
        return idNumber;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getOpenId() {
        return openId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAddress() {
        return address;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getWorkUnit() {
        return workUnit;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit == null ? null : workUnit.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getPersonType() {
        return personType;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPersonType(Long personType) {
        this.personType = personType;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getDoseStatus() {
        return doseStatus;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDoseStatus(Long doseStatus) {
        this.doseStatus = doseStatus;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getFirstdoseId() {
        return firstdoseId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setFirstdoseId(String firstdoseId) {
        this.firstdoseId = firstdoseId == null ? null : firstdoseId.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getFirstdoseName() {
        return firstdoseName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setFirstdoseName(String firstdoseName) {
        this.firstdoseName = firstdoseName == null ? null : firstdoseName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getFirstdoseUnit() {
        return firstdoseUnit;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setFirstdoseUnit(String firstdoseUnit) {
        this.firstdoseUnit = firstdoseUnit == null ? null : firstdoseUnit.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getFirstdoseTime() {
        return firstdoseTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setFirstdoseTime(Long firstdoseTime) {
        this.firstdoseTime = firstdoseTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getLatestdoseId() {
        return latestdoseId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLatestdoseId(String latestdoseId) {
        this.latestdoseId = latestdoseId == null ? null : latestdoseId.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getLatestdoseName() {
        return latestdoseName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLatestdoseName(String latestdoseName) {
        this.latestdoseName = latestdoseName == null ? null : latestdoseName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getLatestdoseUnit() {
        return latestdoseUnit;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLatestdoseUnit(String latestdoseUnit) {
        this.latestdoseUnit = latestdoseUnit == null ? null : latestdoseUnit.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getLatestdoseTime() {
        return latestdoseTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLatestdoseTime(Long latestdoseTime) {
        this.latestdoseTime = latestdoseTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getCreateTime() {
        return createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getUpdateTime() {
        return updateTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}