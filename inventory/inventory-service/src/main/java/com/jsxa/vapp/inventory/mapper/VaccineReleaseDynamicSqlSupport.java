package com.jsxa.vapp.inventory.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class VaccineReleaseDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final VaccineRelease vaccineRelease = new VaccineRelease();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = vaccineRelease.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = vaccineRelease.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> siteId = vaccineRelease.siteId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> siteName = vaccineRelease.siteName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> dose = vaccineRelease.dose;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> amount = vaccineRelease.amount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> dockAmount = vaccineRelease.dockAmount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> useupTime = vaccineRelease.useupTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> version = vaccineRelease.version;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> startTime = vaccineRelease.startTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> vaccineId = vaccineRelease.vaccineId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vaccineName = vaccineRelease.vaccineName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> vaccineBatch = vaccineRelease.vaccineBatch;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> manufacturer = vaccineRelease.manufacturer;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> doseTime = vaccineRelease.doseTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> timePeriod = vaccineRelease.timePeriod;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> timeperiodName = vaccineRelease.timeperiodName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> contactName = vaccineRelease.contactName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> contactMobile = vaccineRelease.contactMobile;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> status = vaccineRelease.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = vaccineRelease.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class VaccineRelease extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Long> siteId = column("site_id", JDBCType.BIGINT);

        public final SqlColumn<String> siteName = column("site_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> dose = column("dose", JDBCType.INTEGER);

        public final SqlColumn<Integer> amount = column("amount", JDBCType.INTEGER);

        public final SqlColumn<Integer> dockAmount = column("dock_amount", JDBCType.INTEGER);

        public final SqlColumn<Long> useupTime = column("useup_time", JDBCType.BIGINT);

        public final SqlColumn<Integer> version = column("version", JDBCType.INTEGER);

        public final SqlColumn<Long> startTime = column("start_time", JDBCType.BIGINT);

        public final SqlColumn<Long> vaccineId = column("vaccine_id", JDBCType.BIGINT);

        public final SqlColumn<String> vaccineName = column("vaccine_name", JDBCType.VARCHAR);

        public final SqlColumn<String> vaccineBatch = column("vaccine_batch", JDBCType.VARCHAR);

        public final SqlColumn<String> manufacturer = column("manufacturer", JDBCType.VARCHAR);

        public final SqlColumn<Long> doseTime = column("dose_time", JDBCType.BIGINT);

        public final SqlColumn<Long> timePeriod = column("time_period", JDBCType.BIGINT);

        public final SqlColumn<String> timeperiodName = column("timePeriod_name", JDBCType.VARCHAR);

        public final SqlColumn<String> contactName = column("contact_name", JDBCType.VARCHAR);

        public final SqlColumn<String> contactMobile = column("contact_mobile", JDBCType.VARCHAR);

        public final SqlColumn<Byte> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public VaccineRelease() {
            super("vaccine_release");
        }
    }
}