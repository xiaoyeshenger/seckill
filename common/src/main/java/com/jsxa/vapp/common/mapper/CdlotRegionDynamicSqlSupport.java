package com.jsxa.vapp.common.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CdlotRegionDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final CdlotRegion cdlotRegion = new CdlotRegion();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = cdlotRegion.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = cdlotRegion.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> value = cdlotRegion.value;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> code = cdlotRegion.code;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> type = cdlotRegion.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> dataType = cdlotRegion.dataType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> address = cdlotRegion.address;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> longitude = cdlotRegion.longitude;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> latitude = cdlotRegion.latitude;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> parentCode = cdlotRegion.parentCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> orderNum = cdlotRegion.orderNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> level = cdlotRegion.level;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> token = cdlotRegion.token;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = cdlotRegion.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class CdlotRegion extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> value = column("value", JDBCType.VARCHAR);

        public final SqlColumn<String> code = column("code", JDBCType.VARCHAR);

        public final SqlColumn<Long> type = column("type", JDBCType.BIGINT);

        public final SqlColumn<Integer> dataType = column("data_type", JDBCType.INTEGER);

        public final SqlColumn<String> address = column("address", JDBCType.VARCHAR);

        public final SqlColumn<String> longitude = column("longitude", JDBCType.VARCHAR);

        public final SqlColumn<String> latitude = column("latitude", JDBCType.VARCHAR);

        public final SqlColumn<String> parentCode = column("parent_code", JDBCType.VARCHAR);

        public final SqlColumn<Integer> orderNum = column("order_num", JDBCType.INTEGER);

        public final SqlColumn<Integer> level = column("level", JDBCType.INTEGER);

        public final SqlColumn<String> token = column("token", JDBCType.VARCHAR);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public CdlotRegion() {
            super("cdlot_region");
        }
    }
}