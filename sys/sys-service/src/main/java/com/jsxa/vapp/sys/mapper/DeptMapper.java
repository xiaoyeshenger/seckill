package com.jsxa.vapp.sys.mapper;

import static com.jsxa.vapp.sys.mapper.DeptDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jsxa.vapp.sys.bo.po.Dept;
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
public interface DeptMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Dept> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DeptResult")
    Dept selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DeptResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="parent_name", property="parentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ancestors", property="ancestors", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.TINYINT),
        @Result(column="principal_name", property="principalName", jdbcType=JdbcType.VARCHAR),
        @Result(column="principal_mobile", property="principalMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="org_id", property="orgId", jdbcType=JdbcType.BIGINT),
        @Result(column="org_name", property="orgName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<Dept> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(dept);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, dept);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, dept)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Dept record) {
        return insert(SqlBuilder.insert(record)
                .into(dept)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(parentId).toProperty("parentId")
                .map(parentName).toProperty("parentName")
                .map(ancestors).toProperty("ancestors")
                .map(orderNum).toProperty("orderNum")
                .map(status).toProperty("status")
                .map(delFlag).toProperty("delFlag")
                .map(principalName).toProperty("principalName")
                .map(principalMobile).toProperty("principalMobile")
                .map(orgId).toProperty("orgId")
                .map(orgName).toProperty("orgName")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Dept record) {
        return insert(SqlBuilder.insert(record)
                .into(dept)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(parentId).toPropertyWhenPresent("parentId", record::getParentId)
                .map(parentName).toPropertyWhenPresent("parentName", record::getParentName)
                .map(ancestors).toPropertyWhenPresent("ancestors", record::getAncestors)
                .map(orderNum).toPropertyWhenPresent("orderNum", record::getOrderNum)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(delFlag).toPropertyWhenPresent("delFlag", record::getDelFlag)
                .map(principalName).toPropertyWhenPresent("principalName", record::getPrincipalName)
                .map(principalMobile).toPropertyWhenPresent("principalMobile", record::getPrincipalMobile)
                .map(orgId).toPropertyWhenPresent("parkId", record::getOrgId)
                .map(orgName).toPropertyWhenPresent("parkId", record::getOrgName)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Dept>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, parentId, parentName, ancestors, orderNum, status, delFlag, principalName, principalMobile, orgId,orgName, createTime)
                .from(dept);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Dept>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, parentId, parentName, ancestors, orderNum, status, delFlag, principalName, principalMobile, orgId,orgName, createTime)
                .from(dept);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Dept>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, parentId, parentName, ancestors, orderNum, status, delFlag, principalName, principalMobile, orgId,orgName, createTime)
                .from(dept);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Dept selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, parentId, parentName, ancestors, orderNum, status, delFlag, principalName, principalMobile, orgId,orgName, createTime)
                .from(dept)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Dept record) {
        return UpdateDSL.updateWithMapper(this::update, dept)
                .set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(parentId).equalTo(record::getParentId)
                .set(parentName).equalTo(record::getParentName)
                .set(ancestors).equalTo(record::getAncestors)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(status).equalTo(record::getStatus)
                .set(delFlag).equalTo(record::getDelFlag)
                .set(principalName).equalTo(record::getPrincipalName)
                .set(principalMobile).equalTo(record::getPrincipalMobile)
                .set(orgId).equalTo(record::getOrgId)
                .set(orgName).equalTo(record::getOrgName)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Dept record) {
        return UpdateDSL.updateWithMapper(this::update, dept)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(parentId).equalToWhenPresent(record::getParentId)
                .set(parentName).equalToWhenPresent(record::getParentName)
                .set(ancestors).equalToWhenPresent(record::getAncestors)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(delFlag).equalToWhenPresent(record::getDelFlag)
                .set(principalName).equalToWhenPresent(record::getPrincipalName)
                .set(principalMobile).equalToWhenPresent(record::getPrincipalMobile)
                .set(orgId).equalToWhenPresent(record::getOrgId)
                .set(orgName).equalToWhenPresent(record::getOrgName)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Dept record) {
        return UpdateDSL.updateWithMapper(this::update, dept)
                .set(name).equalTo(record::getName)
                .set(parentId).equalTo(record::getParentId)
                .set(parentName).equalTo(record::getParentName)
                .set(ancestors).equalTo(record::getAncestors)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(status).equalTo(record::getStatus)
                .set(delFlag).equalTo(record::getDelFlag)
                .set(principalName).equalTo(record::getPrincipalName)
                .set(principalMobile).equalTo(record::getPrincipalMobile)
                .set(orgId).equalTo(record::getOrgId)
                .set(orgName).equalTo(record::getOrgName)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Dept record) {
        return UpdateDSL.updateWithMapper(this::update, dept)
                .set(name).equalToWhenPresent(record::getName)
                .set(parentId).equalToWhenPresent(record::getParentId)
                .set(parentName).equalToWhenPresent(record::getParentName)
                .set(ancestors).equalToWhenPresent(record::getAncestors)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(delFlag).equalToWhenPresent(record::getDelFlag)
                .set(principalName).equalToWhenPresent(record::getPrincipalName)
                .set(principalMobile).equalToWhenPresent(record::getPrincipalMobile)
                .set(orgId).equalToWhenPresent(record::getOrgId)
                .set(orgName).equalToWhenPresent(record::getOrgName)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}