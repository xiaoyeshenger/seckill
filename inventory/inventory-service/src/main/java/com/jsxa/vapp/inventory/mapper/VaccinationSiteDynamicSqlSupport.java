package com.jsxa.vapp.inventory.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class VaccinationSiteDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final VaccinationSite vaccinationSite = new VaccinationSite();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = vaccinationSite.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = vaccinationSite.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> addressCode = vaccinationSite.addressCode;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> orgId = vaccinationSite.orgId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> orgName = vaccinationSite.orgName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> contactName = vaccinationSite.contactName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> contactMobile = vaccinationSite.contactMobile;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> status = vaccinationSite.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> orderNum = vaccinationSite.orderNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = vaccinationSite.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class VaccinationSite extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> addressCode = column("address_code", JDBCType.VARCHAR);

        public final SqlColumn<Long> orgId = column("org_id", JDBCType.BIGINT);

        public final SqlColumn<String> orgName = column("org_name", JDBCType.VARCHAR);

        public final SqlColumn<String> contactName = column("contact_name", JDBCType.VARCHAR);

        public final SqlColumn<String> contactMobile = column("contact_mobile", JDBCType.VARCHAR);

        public final SqlColumn<Byte> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<Integer> orderNum = column("order_num", JDBCType.INTEGER);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public VaccinationSite() {
            super("vaccination_site");
        }
    }
}