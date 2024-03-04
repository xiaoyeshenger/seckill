package com.jsxa.vapp.common.mapper;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class SysFileDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SysFile sysFile = new SysFile();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = sysFile.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = sysFile.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> type = sysFile.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> fileName = sysFile.fileName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> fileKey = sysFile.fileKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> accessPath = sysFile.accessPath;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> storePath = sysFile.storePath;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> oriFileName = sysFile.oriFileName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> orderNum = sysFile.orderNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> activeFlag = sysFile.activeFlag;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = sysFile.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class SysFile extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Long> type = column("type", JDBCType.BIGINT);

        public final SqlColumn<String> fileName = column("file_name", JDBCType.VARCHAR);

        public final SqlColumn<String> fileKey = column("file_key", JDBCType.VARCHAR);

        public final SqlColumn<String> accessPath = column("access_path", JDBCType.VARCHAR);

        public final SqlColumn<String> storePath = column("store_path", JDBCType.VARCHAR);

        public final SqlColumn<String> oriFileName = column("ori_file_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> orderNum = column("order_num", JDBCType.INTEGER);

        public final SqlColumn<Byte> activeFlag = column("active_flag", JDBCType.TINYINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public SysFile() {
            super("sys_file");
        }
    }
}