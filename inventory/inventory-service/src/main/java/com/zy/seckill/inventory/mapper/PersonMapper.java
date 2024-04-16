package com.zy.seckill.inventory.mapper;

import static com.zy.seckill.inventory.mapper.PersonDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.zy.seckill.inventory.bo.po.Person;

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
public interface PersonMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Person> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PersonResult")
    Person selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PersonResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.TINYINT),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="id_number", property="idNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="open_id", property="openId", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="work_unit", property="workUnit", jdbcType=JdbcType.VARCHAR),
        @Result(column="person_type", property="personType", jdbcType=JdbcType.BIGINT),
        @Result(column="dose_status", property="doseStatus", jdbcType=JdbcType.BIGINT),
        @Result(column="firstDose_id", property="firstDoseId", jdbcType=JdbcType.VARCHAR),
        @Result(column="firstDose_name", property="firstDoseName", jdbcType=JdbcType.VARCHAR),
        @Result(column="firstDose_unit", property="firstDoseUnit", jdbcType=JdbcType.VARCHAR),
        @Result(column="firstDose_time", property="firstDoseTime", jdbcType=JdbcType.BIGINT),
        @Result(column="latestDose_id", property="latestDoseId", jdbcType=JdbcType.VARCHAR),
        @Result(column="latestDose_name", property="latestDoseName", jdbcType=JdbcType.VARCHAR),
        @Result(column="latestDose_unit", property="latestDoseUnit", jdbcType=JdbcType.VARCHAR),
        @Result(column="latestDose_time", property="latestDoseTime", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT)
    })
    List<Person> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(person);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, person);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, person)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Person record) {
        return insert(SqlBuilder.insert(record)
                .into(person)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(sex).toProperty("sex")
                .map(age).toProperty("age")
                .map(mobile).toProperty("mobile")
                .map(idNumber).toProperty("idNumber")
                .map(openId).toProperty("openId")
                .map(address).toProperty("address")
                .map(workUnit).toProperty("workUnit")
                .map(personType).toProperty("personType")
                .map(doseStatus).toProperty("doseStatus")
                .map(firstdoseId).toProperty("firstDoseId")
                .map(firstdoseName).toProperty("firstDoseName")
                .map(firstdoseUnit).toProperty("firstDoseUnit")
                .map(firstdoseTime).toProperty("firstDoseTime")
                .map(latestdoseId).toProperty("latestDoseId")
                .map(latestdoseName).toProperty("latestDoseName")
                .map(latestdoseUnit).toProperty("latestDoseUnit")
                .map(latestdoseTime).toProperty("latestDoseTime")
                .map(createTime).toProperty("createTime")
                .map(updateTime).toProperty("updateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Person record) {
        return insert(SqlBuilder.insert(record)
                .into(person)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(sex).toPropertyWhenPresent("sex", record::getSex)
                .map(age).toPropertyWhenPresent("age", record::getAge)
                .map(mobile).toPropertyWhenPresent("mobile", record::getMobile)
                .map(idNumber).toPropertyWhenPresent("idNumber", record::getIdNumber)
                .map(openId).toPropertyWhenPresent("openId", record::getOpenId)
                .map(address).toPropertyWhenPresent("address", record::getAddress)
                .map(personType).toPropertyWhenPresent("personType", record::getPersonType)
                .map(doseStatus).toPropertyWhenPresent("doseStatus", record::getDoseStatus)
                .map(firstdoseId).toPropertyWhenPresent("firstDoseId", record::getFirstDoseId)
                .map(firstdoseName).toPropertyWhenPresent("firstDoseName", record::getFirstDoseName)
                .map(firstdoseTime).toPropertyWhenPresent("firstDoseTime", record::getFirstDoseTime)
                .map(latestdoseId).toPropertyWhenPresent("latestDoseId", record::getLatestDoseId)
                .map(latestdoseName).toPropertyWhenPresent("latestDoseName", record::getLatestDoseName)
                .map(latestdoseTime).toPropertyWhenPresent("latestDoseTime", record::getLatestDoseTime)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Person>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, sex, age, mobile, idNumber, openId, address, workUnit, personType, doseStatus, firstdoseId, firstdoseName, firstdoseUnit, firstdoseTime, latestdoseId, latestdoseName, latestdoseUnit, latestdoseTime, createTime, updateTime)
                .from(person);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Person>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, sex, age, mobile, idNumber, openId, address, workUnit, personType, doseStatus, firstdoseId, firstdoseName, firstdoseUnit, firstdoseTime, latestdoseId, latestdoseName, latestdoseUnit, latestdoseTime, createTime, updateTime)
                .from(person);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Person selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, sex, age, mobile, idNumber, openId, address, workUnit, personType, doseStatus, firstdoseId, firstdoseName, firstdoseUnit, firstdoseTime, latestdoseId, latestdoseName, latestdoseUnit, latestdoseTime, createTime, updateTime)
                .from(person)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Person record) {
        return UpdateDSL.updateWithMapper(this::update, person)
                .set(name).equalTo(record::getName)
                .set(sex).equalTo(record::getSex)
                .set(age).equalTo(record::getAge)
                .set(mobile).equalTo(record::getMobile)
                .set(idNumber).equalTo(record::getIdNumber)
                .set(openId).equalTo(record::getOpenId)
                .set(address).equalTo(record::getAddress)
                .set(personType).equalTo(record::getPersonType)
                .set(doseStatus).equalTo(record::getDoseStatus)
                .set(firstdoseId).equalTo(record::getFirstDoseId)
                .set(firstdoseName).equalTo(record::getFirstDoseName)
                .set(firstdoseTime).equalTo(record::getFirstDoseTime)
                .set(latestdoseId).equalTo(record::getLatestDoseId)
                .set(latestdoseName).equalTo(record::getLatestDoseName)
                .set(latestdoseTime).equalTo(record::getLatestDoseTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Person record) {
        return UpdateDSL.updateWithMapper(this::update, person)
                .set(name).equalToWhenPresent(record::getName)
                .set(sex).equalToWhenPresent(record::getSex)
                .set(age).equalToWhenPresent(record::getAge)
                .set(mobile).equalToWhenPresent(record::getMobile)
                .set(idNumber).equalToWhenPresent(record::getIdNumber)
                .set(openId).equalToWhenPresent(record::getOpenId)
                .set(address).equalToWhenPresent(record::getAddress)
                .set(personType).equalToWhenPresent(record::getPersonType)
                .set(doseStatus).equalToWhenPresent(record::getDoseStatus)
                .set(firstdoseId).equalToWhenPresent(record::getFirstDoseId)
                .set(firstdoseName).equalToWhenPresent(record::getFirstDoseName)
                .set(firstdoseTime).equalToWhenPresent(record::getFirstDoseTime)
                .set(latestdoseId).equalToWhenPresent(record::getLatestDoseId)
                .set(latestdoseName).equalToWhenPresent(record::getLatestDoseName)
                .set(latestdoseTime).equalToWhenPresent(record::getLatestDoseTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Person record) {
        return UpdateDSL.updateWithMapper(this::update, person)
                .set(name).equalTo(record::getName)
                .set(sex).equalTo(record::getSex)
                .set(age).equalTo(record::getAge)
                .set(mobile).equalTo(record::getMobile)
                .set(idNumber).equalTo(record::getIdNumber)
                .set(openId).equalTo(record::getOpenId)
                .set(address).equalTo(record::getAddress)
                .set(personType).equalTo(record::getPersonType)
                .set(doseStatus).equalTo(record::getDoseStatus)
                .set(firstdoseId).equalTo(record::getFirstDoseId)
                .set(firstdoseName).equalTo(record::getFirstDoseName)
                .set(firstdoseTime).equalTo(record::getFirstDoseTime)
                .set(latestdoseId).equalTo(record::getLatestDoseId)
                .set(latestdoseName).equalTo(record::getLatestDoseName)
                .set(latestdoseTime).equalTo(record::getLatestDoseTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Person record) {
        return UpdateDSL.updateWithMapper(this::update, person)
                .set(name).equalToWhenPresent(record::getName)
                .set(sex).equalToWhenPresent(record::getSex)
                .set(age).equalToWhenPresent(record::getAge)
                .set(mobile).equalToWhenPresent(record::getMobile)
                .set(idNumber).equalToWhenPresent(record::getIdNumber)
                .set(openId).equalToWhenPresent(record::getOpenId)
                .set(address).equalToWhenPresent(record::getAddress)
                .set(personType).equalToWhenPresent(record::getPersonType)
                .set(doseStatus).equalToWhenPresent(record::getDoseStatus)
                .set(firstdoseId).equalToWhenPresent(record::getFirstDoseId)
                .set(firstdoseName).equalToWhenPresent(record::getFirstDoseName)
                .set(firstdoseTime).equalToWhenPresent(record::getFirstDoseTime)
                .set(latestdoseId).equalToWhenPresent(record::getLatestDoseId)
                .set(latestdoseName).equalToWhenPresent(record::getLatestDoseName)
                .set(latestdoseTime).equalToWhenPresent(record::getLatestDoseTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}