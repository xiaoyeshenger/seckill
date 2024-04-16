package com.zy.seckill.inventory.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class TimeTaskDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TimeTask timeTask = new TimeTask();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = timeTask.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> type = timeTask.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> projectId = timeTask.projectId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> projectName = timeTask.projectName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> jobId = timeTask.jobId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> xxljobadminAddress = timeTask.xxljobadminAddress;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> corn = timeTask.corn;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> handler = timeTask.handler;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> appName = timeTask.appName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = timeTask.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TimeTask extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> projectId = column("project_id", JDBCType.BIGINT);

        public final SqlColumn<Long> type = column("type", JDBCType.BIGINT);

        public final SqlColumn<String> projectName = column("project_name", JDBCType.VARCHAR);

        public final SqlColumn<String> jobId = column("job_id", JDBCType.VARCHAR);

        public final SqlColumn<String> xxljobadminAddress = column("xxlJobAdmin_address", JDBCType.VARCHAR);

        public final SqlColumn<String> corn = column("corn", JDBCType.VARCHAR);

        public final SqlColumn<String> handler = column("handler", JDBCType.VARCHAR);

        public final SqlColumn<String> appName = column("app_name", JDBCType.VARCHAR);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public TimeTask() {
            super("time_task");
        }
    }
}