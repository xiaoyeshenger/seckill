package com.jsxa.vapp.common.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jsxa.vapp.common.bo.po.User;
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
public interface UserMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<User> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserResult")
    User selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="org_id", property="orgId", jdbcType=JdbcType.BIGINT),
        @Result(column="org_name", property="orgName", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_sex", property="userSex", jdbcType=JdbcType.TINYINT),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile_number", property="mobileNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic_url", property="picUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_key", property="roleKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="reg_type", property="regType", jdbcType=JdbcType.VARCHAR),
        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="login_ip", property="loginIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_time", property="loginTime", jdbcType=JdbcType.BIGINT),
        @Result(column="region_code", property="regionCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="dept_id", property="deptId", jdbcType=JdbcType.BIGINT),
        @Result(column="dept_name", property="deptName", jdbcType=JdbcType.VARCHAR),
        @Result(column="post_id", property="postId", jdbcType=JdbcType.BIGINT),
        @Result(column="post_name", property="postName", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(UserDynamicSqlSupport.user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, UserDynamicSqlSupport.user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(User record) {
        return insert(SqlBuilder.insert(record)
                .into(UserDynamicSqlSupport.user)
                .map(UserDynamicSqlSupport.id).toProperty("id")
                .map(UserDynamicSqlSupport.orgId).toProperty("orgId")
                .map(UserDynamicSqlSupport.orgName).toProperty("orgName")
                .map(UserDynamicSqlSupport.name).toProperty("name")
                .map(UserDynamicSqlSupport.username).toProperty("username")
                .map(UserDynamicSqlSupport.password).toProperty("password")
                .map(UserDynamicSqlSupport.userSex).toProperty("userSex")
                .map(UserDynamicSqlSupport.email).toProperty("email")
                .map(UserDynamicSqlSupport.mobileNumber).toProperty("mobileNumber")
                .map(UserDynamicSqlSupport.picUrl).toProperty("picUrl")
                .map(UserDynamicSqlSupport.roleKey).toProperty("roleKey")
                .map(UserDynamicSqlSupport.roleId).toProperty("roleId")
                .map(UserDynamicSqlSupport.status).toProperty("status")
                .map(UserDynamicSqlSupport.regType).toProperty("regType")
                .map(UserDynamicSqlSupport.delFlag).toProperty("delFlag")
                .map(UserDynamicSqlSupport.createTime).toProperty("createTime")
                .map(UserDynamicSqlSupport.loginIp).toProperty("loginIp")
                .map(UserDynamicSqlSupport.loginTime).toProperty("loginTime")
                .map(UserDynamicSqlSupport.regionCode).toProperty("regionCode")
                .map(UserDynamicSqlSupport.deptId).toProperty("deptId")
                .map(UserDynamicSqlSupport.deptName).toProperty("deptName")
                .map(UserDynamicSqlSupport.postId).toProperty("postId")
                .map(UserDynamicSqlSupport.postName).toProperty("postName")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(User record) {
        return insert(SqlBuilder.insert(record)
                .into(UserDynamicSqlSupport.user)
                .map(UserDynamicSqlSupport.id).toPropertyWhenPresent("id", record::getId)
                .map(UserDynamicSqlSupport.orgId).toPropertyWhenPresent("orgId", record::getOrgId)
                .map(UserDynamicSqlSupport.orgName).toPropertyWhenPresent("name", record::getOrgName)
                .map(UserDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
                .map(UserDynamicSqlSupport.username).toPropertyWhenPresent("username", record::getUsername)
                .map(UserDynamicSqlSupport.password).toPropertyWhenPresent("password", record::getPassword)
                .map(UserDynamicSqlSupport.userSex).toPropertyWhenPresent("userSex", record::getUserSex)
                .map(UserDynamicSqlSupport.email).toPropertyWhenPresent("email", record::getEmail)
                .map(UserDynamicSqlSupport.mobileNumber).toPropertyWhenPresent("mobileNumber", record::getMobileNumber)
                .map(UserDynamicSqlSupport.picUrl).toPropertyWhenPresent("picUrl", record::getPicUrl)
                .map(UserDynamicSqlSupport.roleKey).toPropertyWhenPresent("role", record::getRoleKey)
                .map(UserDynamicSqlSupport.roleId).toPropertyWhenPresent("role", record::getRoleId)
                .map(UserDynamicSqlSupport.status).toPropertyWhenPresent("status", record::getStatus)
                .map(UserDynamicSqlSupport.regType).toPropertyWhenPresent("regType", record::getRegType)
                .map(UserDynamicSqlSupport.delFlag).toPropertyWhenPresent("delFlag", record::getDelFlag)
                .map(UserDynamicSqlSupport.createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(UserDynamicSqlSupport.loginIp).toPropertyWhenPresent("loginIp", record::getLoginIp)
                .map(UserDynamicSqlSupport.loginTime).toPropertyWhenPresent("loginTime", record::getLoginTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<User>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, UserDynamicSqlSupport.id, UserDynamicSqlSupport.orgId, UserDynamicSqlSupport.orgName,UserDynamicSqlSupport.name, UserDynamicSqlSupport.username, UserDynamicSqlSupport.password, UserDynamicSqlSupport.userSex, UserDynamicSqlSupport.email, UserDynamicSqlSupport.mobileNumber, UserDynamicSqlSupport.picUrl, UserDynamicSqlSupport.roleKey, UserDynamicSqlSupport.roleId, UserDynamicSqlSupport.status, UserDynamicSqlSupport.regType, UserDynamicSqlSupport.delFlag, UserDynamicSqlSupport.createTime, UserDynamicSqlSupport.loginIp, UserDynamicSqlSupport.loginTime, UserDynamicSqlSupport.regionCode, UserDynamicSqlSupport.deptId, UserDynamicSqlSupport.deptName, UserDynamicSqlSupport.postId, UserDynamicSqlSupport.postName)
                .from(UserDynamicSqlSupport.user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<User>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, UserDynamicSqlSupport.id, UserDynamicSqlSupport.orgId, UserDynamicSqlSupport.orgName,UserDynamicSqlSupport.name, UserDynamicSqlSupport.username, UserDynamicSqlSupport.password, UserDynamicSqlSupport.userSex, UserDynamicSqlSupport.email, UserDynamicSqlSupport.mobileNumber, UserDynamicSqlSupport.picUrl, UserDynamicSqlSupport.roleKey, UserDynamicSqlSupport.roleId, UserDynamicSqlSupport.status, UserDynamicSqlSupport.regType, UserDynamicSqlSupport.delFlag, UserDynamicSqlSupport.createTime, UserDynamicSqlSupport.loginIp, UserDynamicSqlSupport.loginTime, UserDynamicSqlSupport.regionCode, UserDynamicSqlSupport.deptId, UserDynamicSqlSupport.deptName, UserDynamicSqlSupport.postId, UserDynamicSqlSupport.postName)
                .from(UserDynamicSqlSupport.user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<User>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, UserDynamicSqlSupport.id, UserDynamicSqlSupport.orgId, UserDynamicSqlSupport.orgName,UserDynamicSqlSupport.name, UserDynamicSqlSupport.username, UserDynamicSqlSupport.password, UserDynamicSqlSupport.userSex, UserDynamicSqlSupport.email, UserDynamicSqlSupport.mobileNumber, UserDynamicSqlSupport.picUrl, UserDynamicSqlSupport.roleKey, UserDynamicSqlSupport.roleId, UserDynamicSqlSupport.status, UserDynamicSqlSupport.regType, UserDynamicSqlSupport.delFlag, UserDynamicSqlSupport.createTime, UserDynamicSqlSupport.loginIp, UserDynamicSqlSupport.loginTime, UserDynamicSqlSupport.regionCode, UserDynamicSqlSupport.deptId, UserDynamicSqlSupport.deptName, UserDynamicSqlSupport.postId, UserDynamicSqlSupport.postName)
                .from(UserDynamicSqlSupport.user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default User selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, UserDynamicSqlSupport.id, UserDynamicSqlSupport.orgId, UserDynamicSqlSupport.orgName,UserDynamicSqlSupport.name, UserDynamicSqlSupport.username, UserDynamicSqlSupport.password, UserDynamicSqlSupport.userSex, UserDynamicSqlSupport.email, UserDynamicSqlSupport.mobileNumber, UserDynamicSqlSupport.picUrl, UserDynamicSqlSupport.roleKey, UserDynamicSqlSupport.roleId, UserDynamicSqlSupport.status, UserDynamicSqlSupport.regType, UserDynamicSqlSupport.delFlag, UserDynamicSqlSupport.createTime, UserDynamicSqlSupport.loginIp, UserDynamicSqlSupport.loginTime, UserDynamicSqlSupport.regionCode, UserDynamicSqlSupport.deptId, UserDynamicSqlSupport.deptName, UserDynamicSqlSupport.postId, UserDynamicSqlSupport.postName)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(User record) {
        return UpdateDSL.updateWithMapper(this::update, UserDynamicSqlSupport.user)
                .set(UserDynamicSqlSupport.id).equalTo(record::getId)
                .set(UserDynamicSqlSupport.orgId).equalTo(record::getOrgId)
                .set(UserDynamicSqlSupport.orgName).equalTo(record::getOrgName)
                .set(UserDynamicSqlSupport.name).equalTo(record::getName)
                .set(UserDynamicSqlSupport.username).equalTo(record::getUsername)
                .set(UserDynamicSqlSupport.password).equalTo(record::getPassword)
                .set(UserDynamicSqlSupport.userSex).equalTo(record::getUserSex)
                .set(UserDynamicSqlSupport.email).equalTo(record::getEmail)
                .set(UserDynamicSqlSupport.mobileNumber).equalTo(record::getMobileNumber)
                .set(UserDynamicSqlSupport.picUrl).equalTo(record::getPicUrl)
                .set(UserDynamicSqlSupport.roleKey).equalTo(record::getRoleKey)
                .set(UserDynamicSqlSupport.roleId).equalTo(record::getRoleId)
                .set(UserDynamicSqlSupport.status).equalTo(record::getStatus)
                .set(UserDynamicSqlSupport.regType).equalTo(record::getRegType)
                .set(UserDynamicSqlSupport.delFlag).equalTo(record::getDelFlag)
                .set(UserDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                .set(UserDynamicSqlSupport.loginIp).equalTo(record::getLoginIp)
                .set(UserDynamicSqlSupport.loginTime).equalTo(record::getLoginTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(User record) {
        return UpdateDSL.updateWithMapper(this::update, UserDynamicSqlSupport.user)
                .set(UserDynamicSqlSupport.id).equalToWhenPresent(record::getId)
                .set(UserDynamicSqlSupport.orgId).equalToWhenPresent(record::getOrgId)
                .set(UserDynamicSqlSupport.orgName).equalToWhenPresent(record::getOrgName)
                .set(UserDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(UserDynamicSqlSupport.username).equalToWhenPresent(record::getUsername)
                .set(UserDynamicSqlSupport.password).equalToWhenPresent(record::getPassword)
                .set(UserDynamicSqlSupport.userSex).equalToWhenPresent(record::getUserSex)
                .set(UserDynamicSqlSupport.email).equalToWhenPresent(record::getEmail)
                .set(UserDynamicSqlSupport.mobileNumber).equalToWhenPresent(record::getMobileNumber)
                .set(UserDynamicSqlSupport.picUrl).equalToWhenPresent(record::getPicUrl)
                .set(UserDynamicSqlSupport.roleKey).equalToWhenPresent(record::getRoleKey)
                .set(UserDynamicSqlSupport.roleId).equalToWhenPresent(record::getRoleId)
                .set(UserDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
                .set(UserDynamicSqlSupport.regType).equalToWhenPresent(record::getRegType)
                .set(UserDynamicSqlSupport.delFlag).equalToWhenPresent(record::getDelFlag)
                .set(UserDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                .set(UserDynamicSqlSupport.loginIp).equalToWhenPresent(record::getLoginIp)
                .set(UserDynamicSqlSupport.loginTime).equalToWhenPresent(record::getLoginTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(User record) {
        return UpdateDSL.updateWithMapper(this::update, UserDynamicSqlSupport.user)
                .set(UserDynamicSqlSupport.orgId).equalTo(record::getOrgId)
                .set(UserDynamicSqlSupport.orgName).equalTo(record::getOrgName)
                .set(UserDynamicSqlSupport.name).equalTo(record::getName)
                .set(UserDynamicSqlSupport.username).equalTo(record::getUsername)
                .set(UserDynamicSqlSupport.password).equalTo(record::getPassword)
                .set(UserDynamicSqlSupport.userSex).equalTo(record::getUserSex)
                .set(UserDynamicSqlSupport.email).equalTo(record::getEmail)
                .set(UserDynamicSqlSupport.mobileNumber).equalTo(record::getMobileNumber)
                .set(UserDynamicSqlSupport.picUrl).equalTo(record::getPicUrl)
                .set(UserDynamicSqlSupport.roleKey).equalTo(record::getRoleKey)
                .set(UserDynamicSqlSupport.roleId).equalTo(record::getRoleId)
                .set(UserDynamicSqlSupport.status).equalTo(record::getStatus)
                .set(UserDynamicSqlSupport.regType).equalTo(record::getRegType)
                .set(UserDynamicSqlSupport.delFlag).equalTo(record::getDelFlag)
                .set(UserDynamicSqlSupport.createTime).equalTo(record::getCreateTime)
                .set(UserDynamicSqlSupport.loginIp).equalTo(record::getLoginIp)
                .set(UserDynamicSqlSupport.loginTime).equalTo(record::getLoginTime)
                .where(UserDynamicSqlSupport.id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(User record) {
        return UpdateDSL.updateWithMapper(this::update, UserDynamicSqlSupport.user)
                .set(UserDynamicSqlSupport.orgId).equalToWhenPresent(record::getOrgId)
                .set(UserDynamicSqlSupport.orgName).equalToWhenPresent(record::getOrgName)
                .set(UserDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(UserDynamicSqlSupport.username).equalToWhenPresent(record::getUsername)
                .set(UserDynamicSqlSupport.password).equalToWhenPresent(record::getPassword)
                .set(UserDynamicSqlSupport.userSex).equalToWhenPresent(record::getUserSex)
                .set(UserDynamicSqlSupport.email).equalToWhenPresent(record::getEmail)
                .set(UserDynamicSqlSupport.mobileNumber).equalToWhenPresent(record::getMobileNumber)
                .set(UserDynamicSqlSupport.picUrl).equalToWhenPresent(record::getPicUrl)
                .set(UserDynamicSqlSupport.roleKey).equalToWhenPresent(record::getRoleKey)
                .set(UserDynamicSqlSupport.roleId).equalToWhenPresent(record::getRoleId)
                .set(UserDynamicSqlSupport.status).equalToWhenPresent(record::getStatus)
                .set(UserDynamicSqlSupport.regType).equalToWhenPresent(record::getRegType)
                .set(UserDynamicSqlSupport.delFlag).equalToWhenPresent(record::getDelFlag)
                .set(UserDynamicSqlSupport.createTime).equalToWhenPresent(record::getCreateTime)
                .set(UserDynamicSqlSupport.loginIp).equalToWhenPresent(record::getLoginIp)
                .set(UserDynamicSqlSupport.loginTime).equalToWhenPresent(record::getLoginTime)
                .where(UserDynamicSqlSupport.id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}