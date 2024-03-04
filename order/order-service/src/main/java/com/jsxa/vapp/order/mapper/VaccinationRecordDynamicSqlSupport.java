package com.jsxa.vapp.order.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class VaccinationRecordDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final VaccinationRecord vaccinationRecord = new VaccinationRecord();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = vaccinationRecord.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> recordType = vaccinationRecord.recordType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> vaild = vaccinationRecord.vaild;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> personId = vaccinationRecord.personId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> vaccineReleaseId = vaccinationRecord.vaccineReleaseId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vaccineReleaseName = vaccinationRecord.vaccineReleaseName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> personName = vaccinationRecord.personName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> sex = vaccinationRecord.sex;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> age = vaccinationRecord.age;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> mobile = vaccinationRecord.mobile;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> idNumber = vaccinationRecord.idNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> openId = vaccinationRecord.openId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> siteId = vaccinationRecord.siteId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> siteName = vaccinationRecord.siteName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> recordStatus = vaccinationRecord.recordStatus;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> vaccineId = vaccinationRecord.vaccineId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vaccineName = vaccinationRecord.vaccineName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vaccineBatch = vaccinationRecord.vaccineBatch;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> manufacturer = vaccinationRecord.manufacturer;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> dose = vaccinationRecord.dose;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> doseUnit = vaccinationRecord.doseUnit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> appointmentTime = vaccinationRecord.appointmentTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> timePeriod = vaccinationRecord.timePeriod;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> timeperiodName = vaccinationRecord.timeperiodName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> doseTime = vaccinationRecord.doseTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> imageUrl = vaccinationRecord.imageUrl;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> city = vaccinationRecord.city;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> county = vaccinationRecord.county;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> town = vaccinationRecord.town;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> msg = vaccinationRecord.msg;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = vaccinationRecord.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class VaccinationRecord extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> recordType = column("record_type", JDBCType.BIGINT);

        public final SqlColumn<Byte> vaild = column("vaild", JDBCType.TINYINT);

        public final SqlColumn<Long> vaccineReleaseId = column("vaccineRelease_id", JDBCType.BIGINT);

        public final SqlColumn<String> vaccineReleaseName = column("vaccineRelease_name", JDBCType.VARCHAR);

        public final SqlColumn<Long> personId = column("person_id", JDBCType.BIGINT);

        public final SqlColumn<String> personName = column("person_name", JDBCType.VARCHAR);

        public final SqlColumn<Byte> sex = column("sex", JDBCType.TINYINT);

        public final SqlColumn<Integer> age = column("age", JDBCType.INTEGER);

        public final SqlColumn<String> mobile = column("mobile", JDBCType.VARCHAR);

        public final SqlColumn<String> idNumber = column("id_number", JDBCType.VARCHAR);

        public final SqlColumn<String> openId = column("open_id", JDBCType.VARCHAR);

        public final SqlColumn<Long> siteId = column("site_id", JDBCType.BIGINT);

        public final SqlColumn<String> siteName = column("site_name", JDBCType.VARCHAR);

        public final SqlColumn<Long> recordStatus = column("record_status", JDBCType.BIGINT);

        public final SqlColumn<Long> vaccineId = column("vaccine_id", JDBCType.BIGINT);

        public final SqlColumn<String> vaccineName = column("vaccine_name", JDBCType.VARCHAR);

        public final SqlColumn<String> vaccineBatch = column("vaccine_batch", JDBCType.VARCHAR);

        public final SqlColumn<String> manufacturer = column("manufacturer", JDBCType.VARCHAR);

        public final SqlColumn<Integer> dose = column("dose", JDBCType.INTEGER);

        public final SqlColumn<String> doseUnit = column("dose_unit", JDBCType.VARCHAR);

        public final SqlColumn<Long> appointmentTime = column("appointment_time", JDBCType.BIGINT);

        public final SqlColumn<Long> timePeriod = column("time_period", JDBCType.BIGINT);

        public final SqlColumn<String> timeperiodName = column("timePeriod_name", JDBCType.VARCHAR);

        public final SqlColumn<Long> doseTime = column("dose_time", JDBCType.BIGINT);

        public final SqlColumn<String> imageUrl = column("image_url", JDBCType.VARCHAR);

        public final SqlColumn<String> city = column("city", JDBCType.VARCHAR);

        public final SqlColumn<String> county = column("county", JDBCType.VARCHAR);

        public final SqlColumn<String> town = column("town", JDBCType.VARCHAR);

        public final SqlColumn<String> msg = column("msg", JDBCType.VARCHAR);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public VaccinationRecord() {
            super("vaccination_record");
        }
    }
}