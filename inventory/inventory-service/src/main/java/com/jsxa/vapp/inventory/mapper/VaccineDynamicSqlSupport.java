package com.jsxa.vapp.inventory.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class VaccineDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Vaccine vaccine = new Vaccine();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = vaccine.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> orgId = vaccine.orgId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> orgName = vaccine.orgName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = vaccine.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> manufacturer = vaccine.manufacturer;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> batchNumber = vaccine.batchNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> totalDose = vaccine.totalDose;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> doseInterval = vaccine.doseInterval;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> stock = vaccine.stock;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> oosUrl = vaccine.oosUrl;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> orderNum = vaccine.orderNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> status = vaccine.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = vaccine.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Vaccine extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> orgId = column("org_id", JDBCType.BIGINT);

        public final SqlColumn<String> orgName = column("org_name", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> manufacturer = column("manufacturer", JDBCType.VARCHAR);

        public final SqlColumn<String> batchNumber = column("batch_number", JDBCType.VARCHAR);

        public final SqlColumn<Integer> totalDose = column("total_dose", JDBCType.INTEGER);

        public final SqlColumn<String> doseInterval = column("dose_interval", JDBCType.VARCHAR);

        public final SqlColumn<Integer> stock = column("stock", JDBCType.INTEGER);

        public final SqlColumn<String> oosUrl = column("oos_url", JDBCType.VARCHAR);

        public final SqlColumn<Integer> orderNum = column("order_num", JDBCType.INTEGER);

        public final SqlColumn<Byte> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public Vaccine() {
            super("vaccine");
        }
    }
}