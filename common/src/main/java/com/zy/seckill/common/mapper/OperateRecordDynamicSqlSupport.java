package com.zy.seckill.common.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class OperateRecordDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final OperateRecord operateRecord = new OperateRecord();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = operateRecord.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> objectType = operateRecord.objectType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> objectId = operateRecord.objectId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> operateType = operateRecord.operateType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> operatorId = operateRecord.operatorId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> operatorName = operateRecord.operatorName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> operateTime = operateRecord.operateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class OperateRecord extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> objectType = column("object_type", JDBCType.VARCHAR);

        public final SqlColumn<Long> objectId = column("object_id", JDBCType.BIGINT);

        public final SqlColumn<String> operateType = column("operate_type", JDBCType.VARCHAR);

        public final SqlColumn<Long> operatorId = column("operator_id", JDBCType.BIGINT);

        public final SqlColumn<String> operatorName = column("operator_name", JDBCType.VARCHAR);

        public final SqlColumn<Long> operateTime = column("operate_time", JDBCType.BIGINT);

        public OperateRecord() {
            super("operate_record");
        }
    }
}