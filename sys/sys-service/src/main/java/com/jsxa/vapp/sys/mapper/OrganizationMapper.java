package com.jsxa.vapp.sys.mapper;

import static com.jsxa.vapp.sys.mapper.OrganizationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jsxa.vapp.sys.bo.po.Organization;
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
public interface OrganizationMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Organization> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OrganizationResult")
    Organization selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OrganizationResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="contact_name", property="contactName", jdbcType=JdbcType.VARCHAR),
        @Result(column="contact_mobile", property="contactMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<Organization> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(organization);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, organization);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, organization)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Organization record) {
        return insert(SqlBuilder.insert(record)
                .into(organization)
                .map(code).toProperty("code")
                .map(name).toProperty("name")
                .map(contactName).toProperty("contactName")
                .map(contactMobile).toProperty("contactMobile")
                .map(status).toProperty("status")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Organization record) {
        return insert(SqlBuilder.insert(record)
                .into(organization)
                .map(code).toPropertyWhenPresent("code", record::getCode)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(contactName).toPropertyWhenPresent("contactName", record::getContactName)
                .map(contactMobile).toPropertyWhenPresent("contactMobile", record::getContactMobile)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Organization>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, id, code, name, contactName, contactMobile, status, createTime)
                .from(organization);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Organization>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, code, name, contactName, contactMobile, status, createTime)
                .from(organization);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Organization>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, code, name, contactName, contactMobile, status, createTime)
                .from(organization);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Organization selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, code, name, contactName, contactMobile, status, createTime)
                .from(organization)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Organization record) {
        return UpdateDSL.updateWithMapper(this::update, organization)
                .set(code).equalTo(record::getCode)
                .set(name).equalTo(record::getName)
                .set(contactName).equalTo(record::getContactName)
                .set(contactMobile).equalTo(record::getContactMobile)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Organization record) {
        return UpdateDSL.updateWithMapper(this::update, organization)
                .set(code).equalToWhenPresent(record::getCode)
                .set(name).equalToWhenPresent(record::getName)
                .set(contactName).equalToWhenPresent(record::getContactName)
                .set(contactMobile).equalToWhenPresent(record::getContactMobile)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Organization record) {
        return UpdateDSL.updateWithMapper(this::update, organization)
                .set(code).equalTo(record::getCode)
                .set(name).equalTo(record::getName)
                .set(contactName).equalTo(record::getContactName)
                .set(contactMobile).equalTo(record::getContactMobile)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Organization record) {
        return UpdateDSL.updateWithMapper(this::update, organization)
                .set(code).equalToWhenPresent(record::getCode)
                .set(name).equalToWhenPresent(record::getName)
                .set(contactName).equalToWhenPresent(record::getContactName)
                .set(contactMobile).equalToWhenPresent(record::getContactMobile)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}