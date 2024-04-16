package com.zy.seckill.codeGen.entity;

import io.swagger.annotations.ApiModelProperty;
import javax.annotation.Generated;

public class VaccinationRecord {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long recordType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Byte vaild;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long personId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String personName;

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
    private Long siteId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String siteName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long recordStatus;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long vaccineId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String vaccineName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String vaccineBatch;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String manufacturer;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer dose;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String doseUnit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long appointmentTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long timePeriod;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String timeperiodName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long doseTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String imageUrl;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String city;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String county;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String town;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String msg;

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
    public Long getRecordType() {
        return recordType;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRecordType(Long recordType) {
        this.recordType = recordType;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Byte getVaild() {
        return vaild;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVaild(Byte vaild) {
        this.vaild = vaild;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getPersonId() {
        return personId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getPersonName() {
        return personName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
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
    public Long getSiteId() {
        return siteId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getSiteName() {
        return siteName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getRecordStatus() {
        return recordStatus;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRecordStatus(Long recordStatus) {
        this.recordStatus = recordStatus;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getVaccineId() {
        return vaccineId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVaccineId(Long vaccineId) {
        this.vaccineId = vaccineId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVaccineName() {
        return vaccineName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName == null ? null : vaccineName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVaccineBatch() {
        return vaccineBatch;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVaccineBatch(String vaccineBatch) {
        this.vaccineBatch = vaccineBatch == null ? null : vaccineBatch.trim();
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
    public Integer getDose() {
        return dose;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDose(Integer dose) {
        this.dose = dose;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDoseUnit() {
        return doseUnit;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDoseUnit(String doseUnit) {
        this.doseUnit = doseUnit == null ? null : doseUnit.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getAppointmentTime() {
        return appointmentTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAppointmentTime(Long appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getTimePeriod() {
        return timePeriod;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTimePeriod(Long timePeriod) {
        this.timePeriod = timePeriod;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getTimeperiodName() {
        return timeperiodName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTimeperiodName(String timeperiodName) {
        this.timeperiodName = timeperiodName == null ? null : timeperiodName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getDoseTime() {
        return doseTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDoseTime(Long doseTime) {
        this.doseTime = doseTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getImageUrl() {
        return imageUrl;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCity() {
        return city;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCounty() {
        return county;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getTown() {
        return town;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTown(String town) {
        this.town = town == null ? null : town.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getMsg() {
        return msg;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
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