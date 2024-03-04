package com.jsxa.vapp.common.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RegionCdlotRegionDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RegionCdlotRegion regionCdlotRegion = new RegionCdlotRegion();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = regionCdlotRegion.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> regionCode = regionCdlotRegion.regionCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> cdlotregionCode = regionCdlotRegion.cdlotregionCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RegionCdlotRegion extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> regionCode = column("region_code", JDBCType.VARCHAR);

        public final SqlColumn<String> cdlotregionCode = column("cdlotRegion_code", JDBCType.VARCHAR);

        public RegionCdlotRegion() {
            super("region_cdlot_region");
        }
    }
}