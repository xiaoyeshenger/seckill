package com.jsxa.vapp.common.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class GridInfoDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final GridInfo gridInfo = new GridInfo();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = gridInfo.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> streetCode = gridInfo.streetCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> streetName = gridInfo.streetName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> community = gridInfo.community;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> communityName = gridInfo.communityName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> gridCode = gridInfo.gridCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> gridName = gridInfo.gridName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> addressCode = gridInfo.addressCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> addressName = gridInfo.addressName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> status = gridInfo.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> delFlag = gridInfo.delFlag;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = gridInfo.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class GridInfo extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> streetCode = column("street_code", JDBCType.VARCHAR);

        public final SqlColumn<String> streetName = column("street_name", JDBCType.VARCHAR);

        public final SqlColumn<String> community = column("community", JDBCType.VARCHAR);

        public final SqlColumn<String> communityName = column("community_name", JDBCType.VARCHAR);

        public final SqlColumn<String> gridCode = column("grid_code", JDBCType.VARCHAR);

        public final SqlColumn<String> gridName = column("grid_name", JDBCType.VARCHAR);

        public final SqlColumn<String> addressCode = column("address_code", JDBCType.VARCHAR);

        public final SqlColumn<String> addressName = column("address_name", JDBCType.VARCHAR);

        public final SqlColumn<Byte> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<Byte> delFlag = column("del_flag", JDBCType.TINYINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public GridInfo() {
            super("grid_info");
        }
    }
}