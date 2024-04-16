package com.zy.seckill.sys.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PageTemplateDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final PageTemplate pageTemplate = new PageTemplate();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = pageTemplate.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = pageTemplate.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> pageType = pageTemplate.pageType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> pageKey = pageTemplate.pageKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> templateType = pageTemplate.templateType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> description = pageTemplate.description;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> picUrl = pageTemplate.picUrl;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> geoJsonUrl = pageTemplate.geoJsonUrl;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> orderNum = pageTemplate.orderNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> activeFlag = pageTemplate.activeFlag;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> parkId = pageTemplate.parkId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> path = pageTemplate.path;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = pageTemplate.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class PageTemplate extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Long> pageType = column("page_type", JDBCType.BIGINT);

        public final SqlColumn<String> pageKey = column("page_key", JDBCType.VARCHAR);

        public final SqlColumn<Long> templateType = column("template_type", JDBCType.BIGINT);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> picUrl = column("pic_url", JDBCType.VARCHAR);

        public final SqlColumn<String> geoJsonUrl = column("geo_json_url", JDBCType.VARCHAR);

        public final SqlColumn<Integer> orderNum = column("order_num", JDBCType.INTEGER);

        public final SqlColumn<Byte> activeFlag = column("active_flag", JDBCType.TINYINT);

        public final SqlColumn<Long> parkId = column("park_id", JDBCType.BIGINT);

        public final SqlColumn<String> path = column("path", JDBCType.VARCHAR);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public PageTemplate() {
            super("page_template");
        }
    }
}