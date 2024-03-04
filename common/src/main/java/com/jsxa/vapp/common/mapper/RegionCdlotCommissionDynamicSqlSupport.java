package com.jsxa.vapp.common.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RegionCdlotCommissionDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RegionCdlotCommission regionCdlotCommission = new RegionCdlotCommission();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = regionCdlotCommission.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> regionCode = regionCdlotCommission.regionCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> cdlotcommissionCode = regionCdlotCommission.cdlotcommissionCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RegionCdlotCommission extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> regionCode = column("region_code", JDBCType.VARCHAR);

        public final SqlColumn<String> cdlotcommissionCode = column("cdlotCommission_code", JDBCType.VARCHAR);

        public RegionCdlotCommission() {
            super("region_cdlot_commission");
        }
    }
}