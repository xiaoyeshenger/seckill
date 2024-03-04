package com.jsxa.vapp.common.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RegionCdlotTokenDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RegionCdlotToken regionCdlotToken = new RegionCdlotToken();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = regionCdlotToken.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> regionCode = regionCdlotToken.regionCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> cdlotToken = regionCdlotToken.cdlotToken;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RegionCdlotToken extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> regionCode = column("region_code", JDBCType.VARCHAR);

        public final SqlColumn<String> cdlotToken = column("cdlot_token", JDBCType.VARCHAR);

        public RegionCdlotToken() {
            super("region_cdlot_token");
        }
    }
}