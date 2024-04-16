package com.zy.seckill.inventory.mapper;

import static com.zy.seckill.inventory.mapper.SiteDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.zy.seckill.inventory.bo.po.Site;

import java.util.List;
import javax.annotation.Generated;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
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
public interface SiteMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Site> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SiteResult")
    Site selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SiteResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="address_code", property="addressCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="org_id", property="orgId", jdbcType=JdbcType.BIGINT),
        @Result(column="org_name", property="orgName", jdbcType=JdbcType.VARCHAR),
        @Result(column="contact_name", property="contactName", jdbcType=JdbcType.VARCHAR),
        @Result(column="contact_mobile", property="contactMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<Site> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(site);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, site);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, site)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Site record) {
        return insert(SqlBuilder.insert(record)
                .into(site)
                .map(name).toProperty("name")
                .map(addressCode).toProperty("addressCode")
                .map(orgId).toProperty("orgId")
                .map(orgName).toProperty("orgName")
                .map(contactName).toProperty("contactName")
                .map(contactMobile).toProperty("contactMobile")
                .map(status).toProperty("status")
                .map(orderNum).toProperty("orderNum")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Site record) {
        return insert(SqlBuilder.insert(record)
                .into(site)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(addressCode).toPropertyWhenPresent("addressCode", record::getAddressCode)
                .map(orgId).toPropertyWhenPresent("orgId", record::getOrgId)
                .map(orgName).toPropertyWhenPresent("orgName", record::getOrgName)
                .map(contactName).toPropertyWhenPresent("contactName", record::getContactName)
                .map(contactMobile).toPropertyWhenPresent("contactMobile", record::getContactMobile)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(orderNum).toPropertyWhenPresent("orderNum", record::getOrderNum)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Site>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, addressCode, orgId, orgName, contactName, contactMobile, status, orderNum, createTime)
                .from(site);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Site>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, addressCode, orgId, orgName, contactName, contactMobile, status, orderNum, createTime)
                .from(site);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Site selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, addressCode, orgId, orgName, contactName, contactMobile, status, orderNum, createTime)
                .from(site)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Site record) {
        return UpdateDSL.updateWithMapper(this::update, site)
                .set(name).equalTo(record::getName)
                .set(addressCode).equalTo(record::getAddressCode)
                .set(orgId).equalTo(record::getOrgId)
                .set(orgName).equalTo(record::getOrgName)
                .set(contactName).equalTo(record::getContactName)
                .set(contactMobile).equalTo(record::getContactMobile)
                .set(status).equalTo(record::getStatus)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Site record) {
        return UpdateDSL.updateWithMapper(this::update, site)
                .set(name).equalToWhenPresent(record::getName)
                .set(addressCode).equalToWhenPresent(record::getAddressCode)
                .set(orgId).equalToWhenPresent(record::getOrgId)
                .set(orgName).equalToWhenPresent(record::getOrgName)
                .set(contactName).equalToWhenPresent(record::getContactName)
                .set(contactMobile).equalToWhenPresent(record::getContactMobile)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Site record) {
        return UpdateDSL.updateWithMapper(this::update, site)
                .set(name).equalTo(record::getName)
                .set(addressCode).equalTo(record::getAddressCode)
                .set(orgId).equalTo(record::getOrgId)
                .set(orgName).equalTo(record::getOrgName)
                .set(contactName).equalTo(record::getContactName)
                .set(contactMobile).equalTo(record::getContactMobile)
                .set(status).equalTo(record::getStatus)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Site record) {
        return UpdateDSL.updateWithMapper(this::update, site)
                .set(name).equalToWhenPresent(record::getName)
                .set(addressCode).equalToWhenPresent(record::getAddressCode)
                .set(orgId).equalToWhenPresent(record::getOrgId)
                .set(orgName).equalToWhenPresent(record::getOrgName)
                .set(contactName).equalToWhenPresent(record::getContactName)
                .set(contactMobile).equalToWhenPresent(record::getContactMobile)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}