package com.jsxa.vapp.common.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class RegionDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Region region = new Region();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = region.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = region.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> value = region.value;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> code = region.code;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> type = region.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> address = region.address;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> longitude = region.longitude;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> latitude = region.latitude;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> parentCode = region.parentCode;

//    @Generated("org.mybatis.generator.api.MyBatisGenerator")
//    public static final SqlColumn<Integer> level = region.level;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = region.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> dataType = region.dataType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> orderNum = region.orderNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Region extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> value = column("value", JDBCType.VARCHAR);

        public final SqlColumn<String> code = column("code", JDBCType.VARCHAR);

        public final SqlColumn<Long> type = column("type", JDBCType.BIGINT);

        public final SqlColumn<String> address = column("address", JDBCType.VARCHAR);

        public final SqlColumn<String> longitude = column("longitude", JDBCType.VARCHAR);

        public final SqlColumn<String> latitude = column("latitude", JDBCType.VARCHAR);

        public final SqlColumn<String> parentCode = column("parent_code", JDBCType.VARCHAR);

//        public final SqlColumn<Integer> level = column("level", JDBCType.INTEGER);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Integer> dataType = column("data_type", JDBCType.INTEGER);

        public final SqlColumn<Integer> orderNum = column("order_num", JDBCType.INTEGER);

        public Region() {
            super("region");
        }
    }
}