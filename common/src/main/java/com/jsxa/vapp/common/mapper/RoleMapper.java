package com.jsxa.vapp.common.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jsxa.vapp.common.bo.po.Role;
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
public interface RoleMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Role> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RoleResult")
    Role selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RoleResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_key", property="roleKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="use_type", property="useType", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="park_id", property="parkId", jdbcType=JdbcType.BIGINT)
    })
    List<Role> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(RoleDynamicSqlSupport.role);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, RoleDynamicSqlSupport.role);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, RoleDynamicSqlSupport.role)
                .where(RoleDynamicSqlSupport.id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Role record) {
        return insert(SqlBuilder.insert(record)
                .into(RoleDynamicSqlSupport.role)
                .map(RoleDynamicSqlSupport.id).toProperty("id")
                .map(RoleDynamicSqlSupport.name).toProperty("name")
                .map(RoleDynamicSqlSupport.roleKey).toProperty("roleKey")
                .map(RoleDynamicSqlSupport.orderNum).toProperty("orderNum")
                .map(RoleDynamicSqlSupport.useType).toProperty("useType")
                .map(RoleDynamicSqlSupport.status).toProperty("status")
                .map(RoleDynamicSqlSupport.delFlag).toProperty("delFlag")
                .map(RoleDynamicSqlSupport.createTime).toProperty("createTime")
                .map(RoleDynamicSqlSupport.parkId).toProperty("parkId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Role record) {
        return insert(SqlBuilder.insert(record)
                .into(RoleDynamicSqlSupport.role)
                .map(RoleDynamicSqlSupport.id).toPropertyWhenPresent("id", record::getId)
                .map(RoleDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
                .map(RoleDynamicSqlSupport.roleKey).toPropertyWhenPresent("roleKey", record::getRoleKey)
                .map(RoleDynamicSqlSupport.orderNum).toPropertyWhenPresent("orderNum", record::getOrderNum)
                .map(RoleDynamicSqlSupport.useType).toPropertyWhenPresent("useType", record::getUseType)
                .map(RoleDynamicSqlSupport.status).toPropertyWhenPresent("status", record::getStatus)
                .map(RoleDynamicSqlSupport.delFlag).toPropertyWhenPresent("delFlag", record::getDelFlag)
                .map(RoleDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(RoleDynamicSqlSupport.parkId).toPropertyWhenPresent("parkId", record::getParkId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Role>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, RoleDynamicSqlSupport.id, RoleDynamicSqlSupport.name, RoleDynamicSqlSupport.roleKey, RoleDynamicSqlSupport.orderNum, RoleDynamicSqlSupport.useType, RoleDynamicSqlSupport.status, RoleDynamicSqlSupport.delFlag, RoleDynamicSqlSupport.createTime, RoleDynamicSqlSupport.parkId)
                .from(RoleDynamicSqlSupport.role);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Role>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, RoleDynamicSqlSupport.id, RoleDynamicSqlSupport.name, RoleDynamicSqlSupport.roleKey, RoleDynamicSqlSupport.orderNum, RoleDynamicSqlSupport.useType, RoleDynamicSqlSupport.status, RoleDynamicSqlSupport.delFlag, RoleDynamicSqlSupport.createTime, RoleDynamicSqlSupport.parkId)
                .from(RoleDynamicSqlSupport.role);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Role>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, RoleDynamicSqlSupport.id, RoleDynamicSqlSupport.name, RoleDynamicSqlSupport.roleKey, RoleDynamicSqlSupport.orderNum, RoleDynamicSqlSupport.useType, RoleDynamicSqlSupport.status, RoleDynamicSqlSupport.delFlag, RoleDynamicSqlSupport.createTime, RoleDynamicSqlSupport.parkId)
                .from(RoleDynamicSqlSupport.role);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Role selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, RoleDynamicSqlSupport.id, RoleDynamicSqlSupport.name, RoleDynamicSqlSupport.roleKey, RoleDynamicSqlSupport.orderNum, RoleDynamicSqlSupport.useType, RoleDynamicSqlSupport.status, RoleDynamicSqlSupport.delFlag, RoleDynamicSqlSupport.createTime, RoleDynamicSqlSupport.parkId)
                .from(RoleDynamicSqlSupport.role)
                .where(RoleDynamicSqlSupport.id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Role record) {
        return UpdateDSL.updateWithMapper(this::update, RoleDynamicSqlSupport.role)
                .set(RoleDynamicSqlSupport.id).equalTo(record::getId)
                .set(RoleDynamicSqlSupport.name).equalTo(record::getName)
                .set(RoleDynamicSqlSupport.roleKey).equalTo(record::getRoleKey)
                .set(RoleDynamicSqlSupport.orderNum).equalTo(record::getOrderNum)
                .set(RoleDynamicSqlSupport.useType).equalTo(record::getUseType)
                .set(RoleDynamicSqlSupport.status).equalTo(record::getStatus)
                .set(RoleDynamicSqlSupport.delFlag).equalTo(record::getDelFlag)
                .set(RoleDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                .set(RoleDynamicSqlSupport.parkId).equalTo(record::getParkId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Role record) {
        return UpdateDSL.updateWithMapper(this::update, RoleDynamicSqlSupport.role)
                .set(RoleDynamicSqlSupport.id).equalToWhenPresent(record::getId)
                .set(RoleDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(RoleDynamicSqlSupport.roleKey).equalToWhenPresent(record::getRoleKey)
                .set(RoleDynamicSqlSupport.orderNum).equalToWhenPresent(record::getOrderNum)
                .set(RoleDynamicSqlSupport.useType).equalToWhenPresent(record::getUseType)
                .set(RoleDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
                .set(RoleDynamicSqlSupport.delFlag).equalToWhenPresent(record::getDelFlag)
                .set(RoleDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                .set(RoleDynamicSqlSupport.parkId).equalToWhenPresent(record::getParkId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Role record) {
        return UpdateDSL.updateWithMapper(this::update, RoleDynamicSqlSupport.role)
                .set(RoleDynamicSqlSupport.name).equalTo(record::getName)
                .set(RoleDynamicSqlSupport.roleKey).equalTo(record::getRoleKey)
                .set(RoleDynamicSqlSupport.orderNum).equalTo(record::getOrderNum)
                .set(RoleDynamicSqlSupport.useType).equalTo(record::getUseType)
                .set(RoleDynamicSqlSupport.status).equalTo(record::getStatus)
                .set(RoleDynamicSqlSupport.delFlag).equalTo(record::getDelFlag)
                .set(RoleDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                .set(RoleDynamicSqlSupport.parkId).equalTo(record::getParkId)
                .where(RoleDynamicSqlSupport.id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Role record) {
        return UpdateDSL.updateWithMapper(this::update, RoleDynamicSqlSupport.role)
                .set(RoleDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(RoleDynamicSqlSupport.roleKey).equalToWhenPresent(record::getRoleKey)
                .set(RoleDynamicSqlSupport.orderNum).equalToWhenPresent(record::getOrderNum)
                .set(RoleDynamicSqlSupport.useType).equalToWhenPresent(record::getUseType)
                .set(RoleDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
                .set(RoleDynamicSqlSupport.delFlag).equalToWhenPresent(record::getDelFlag)
                .set(RoleDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                .set(RoleDynamicSqlSupport.parkId).equalToWhenPresent(record::getParkId)
                .where(RoleDynamicSqlSupport.id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}