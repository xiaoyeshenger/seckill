package com.zy.seckill.inventory.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ProductReleaseDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final ProductRelease productRelease = new ProductRelease();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = productRelease.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = productRelease.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> siteId = productRelease.siteId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> siteName = productRelease.siteName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> dose = productRelease.dose;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> amount = productRelease.amount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> dockAmount = productRelease.dockAmount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> useupTime = productRelease.useupTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> version = productRelease.version;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> startTime = productRelease.startTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> productId = productRelease.productId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> productName = productRelease.productName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> productBatch = productRelease.productBatch;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> manufacturer = productRelease.manufacturer;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> doseTime = productRelease.doseTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> timePeriod = productRelease.timePeriod;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> timeperiodName = productRelease.timeperiodName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> contactName = productRelease.contactName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> contactMobile = productRelease.contactMobile;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> status = productRelease.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = productRelease.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class ProductRelease extends SqlTable {
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

        public final SqlColumn<Long> productId = column("product_id", JDBCType.BIGINT);

        public final SqlColumn<String> productName = column("product_name", JDBCType.VARCHAR);

        public final SqlColumn<String> productBatch = column("product_batch", JDBCType.VARCHAR);

        public final SqlColumn<String> manufacturer = column("manufacturer", JDBCType.VARCHAR);

        public final SqlColumn<Long> doseTime = column("dose_time", JDBCType.BIGINT);

        public final SqlColumn<Long> timePeriod = column("time_period", JDBCType.BIGINT);

        public final SqlColumn<String> timeperiodName = column("timePeriod_name", JDBCType.VARCHAR);

        public final SqlColumn<String> contactName = column("contact_name", JDBCType.VARCHAR);

        public final SqlColumn<String> contactMobile = column("contact_mobile", JDBCType.VARCHAR);

        public final SqlColumn<Byte> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public ProductRelease() {
            super("product_release");
        }
    }
}