package com.zy.seckill.sys.mapper;

import static com.zy.seckill.sys.mapper.PageTemplateDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.zy.seckill.sys.bo.po.PageTemplate;
import java.util.List;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

@Mapper
public interface PageTemplateMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<PageTemplate> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PageTemplateResult")
    PageTemplate selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PageTemplateResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="page_type", property="pageType", jdbcType=JdbcType.VARCHAR),
        @Result(column="page_key", property="pageKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="template_type", property="templateType", jdbcType=JdbcType.BIGINT),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic_url", property="picUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="geo_json_url", property="geoJsonUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="active_flag", property="activeFlag", jdbcType=JdbcType.TINYINT),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.BIGINT),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<PageTemplate> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(pageTemplate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, pageTemplate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, pageTemplate)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(PageTemplate record) {
        return insert(SqlBuilder.insert(record)
                .into(pageTemplate)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(pageType).toProperty("pageType")
                .map(pageKey).toProperty("pageKey")
                .map(templateType).toProperty("templateType")
                .map(description).toProperty("description")
                .map(picUrl).toProperty("picUrl")
                .map(geoJsonUrl).toProperty("geoJsonUrl")
                .map(orderNum).toProperty("orderNum")
                .map(activeFlag).toProperty("activeFlag")
                .map(parkId).toProperty("parkId")
                .map(path).toProperty("path")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(PageTemplate record) {
        return insert(SqlBuilder.insert(record)
                .into(pageTemplate)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(pageType).toPropertyWhenPresent("pageType", record::getPageType)
                .map(pageKey).toPropertyWhenPresent("pageKey", record::getPageKey)
                .map(templateType).toPropertyWhenPresent("templateType", record::getTemplateType)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(picUrl).toPropertyWhenPresent("picUrl", record::getPicUrl)
                .map(geoJsonUrl).toPropertyWhenPresent("geoJsonUrl", record::getGeoJsonUrl)
                .map(orderNum).toPropertyWhenPresent("orderNum", record::getOrderNum)
                .map(activeFlag).toPropertyWhenPresent("activeFlag", record::getActiveFlag)
                .map(parkId).toPropertyWhenPresent("parkId", record::getParkId)
                .map(path).toPropertyWhenPresent("path", record::getPath)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<PageTemplate>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, pageType, pageKey, templateType, description, picUrl, geoJsonUrl, orderNum, activeFlag, parkId, path, createTime)
                .from(pageTemplate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<PageTemplate>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, pageType, pageKey, templateType, description, picUrl, geoJsonUrl, orderNum, activeFlag, parkId, path, createTime)
                .from(pageTemplate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<PageTemplate>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, pageType, pageKey, templateType, description, picUrl, geoJsonUrl, orderNum, activeFlag, parkId, path, createTime)
                .from(pageTemplate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default PageTemplate selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, pageType, pageKey, templateType, description, picUrl, geoJsonUrl, orderNum, activeFlag, parkId, path, createTime)
                .from(pageTemplate)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(PageTemplate record) {
        return UpdateDSL.updateWithMapper(this::update, pageTemplate)
                .set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(pageType).equalTo(record::getPageType)
                .set(pageKey).equalTo(record::getPageKey)
                .set(templateType).equalTo(record::getTemplateType)
                .set(description).equalTo(record::getDescription)
                .set(picUrl).equalTo(record::getPicUrl)
                .set(geoJsonUrl).equalTo(record::getGeoJsonUrl)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(activeFlag).equalTo(record::getActiveFlag)
                .set(parkId).equalTo(record::getParkId)
                .set(path).equalTo(record::getPath)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(PageTemplate record) {
        return UpdateDSL.updateWithMapper(this::update, pageTemplate)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(pageType).equalToWhenPresent(record::getPageType)
                .set(pageKey).equalToWhenPresent(record::getPageKey)
                .set(templateType).equalToWhenPresent(record::getTemplateType)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(picUrl).equalToWhenPresent(record::getPicUrl)
                .set(geoJsonUrl).equalToWhenPresent(record::getGeoJsonUrl)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(activeFlag).equalToWhenPresent(record::getActiveFlag)
                .set(parkId).equalToWhenPresent(record::getParkId)
                .set(path).equalToWhenPresent(record::getPath)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(PageTemplate record) {
        return UpdateDSL.updateWithMapper(this::update, pageTemplate)
                .set(name).equalTo(record::getName)
                .set(pageType).equalTo(record::getPageType)
                .set(pageKey).equalTo(record::getPageKey)
                .set(templateType).equalTo(record::getTemplateType)
                .set(description).equalTo(record::getDescription)
                .set(picUrl).equalTo(record::getPicUrl)
                .set(geoJsonUrl).equalTo(record::getGeoJsonUrl)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(activeFlag).equalTo(record::getActiveFlag)
                .set(parkId).equalTo(record::getParkId)
                .set(path).equalTo(record::getPath)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(PageTemplate record) {
        return UpdateDSL.updateWithMapper(this::update, pageTemplate)
                .set(name).equalToWhenPresent(record::getName)
                .set(pageType).equalToWhenPresent(record::getPageType)
                .set(pageKey).equalToWhenPresent(record::getPageKey)
                .set(templateType).equalToWhenPresent(record::getTemplateType)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(picUrl).equalToWhenPresent(record::getPicUrl)
                .set(geoJsonUrl).equalToWhenPresent(record::getGeoJsonUrl)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(activeFlag).equalToWhenPresent(record::getActiveFlag)
                .set(parkId).equalToWhenPresent(record::getParkId)
                .set(path).equalToWhenPresent(record::getPath)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}