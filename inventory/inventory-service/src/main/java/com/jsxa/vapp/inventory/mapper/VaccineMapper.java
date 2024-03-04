package com.jsxa.vapp.inventory.mapper;

import static com.jsxa.vapp.inventory.mapper.VaccineDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jsxa.vapp.inventory.bo.po.Vaccine;
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
public interface VaccineMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Vaccine> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("VaccineResult")
    Vaccine selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="VaccineResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="org_id", property="orgId", jdbcType=JdbcType.BIGINT),
        @Result(column="org_name", property="orgName", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="manufacturer", property="manufacturer", jdbcType=JdbcType.VARCHAR),
        @Result(column="batch_number", property="batchNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="total_dose", property="totalDose", jdbcType=JdbcType.INTEGER),
        @Result(column="dose_interval", property="doseInterval", jdbcType=JdbcType.VARCHAR),
        @Result(column="stock", property="stock", jdbcType=JdbcType.INTEGER),
        @Result(column="oos_url", property="oosUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<Vaccine> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(vaccine);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, vaccine);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, vaccine)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Vaccine record) {
        return insert(SqlBuilder.insert(record)
                .into(vaccine)
                .map(orgId).toProperty("orgId")
                .map(orgName).toProperty("orgName")
                .map(name).toProperty("name")
                .map(manufacturer).toProperty("manufacturer")
                .map(batchNumber).toProperty("batchNumber")
                .map(totalDose).toProperty("totalDose")
                .map(doseInterval).toProperty("doseInterval")
                .map(stock).toProperty("stock")
                .map(oosUrl).toProperty("oosUrl")
                .map(orderNum).toProperty("orderNum")
                .map(status).toProperty("status")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Vaccine record) {
        return insert(SqlBuilder.insert(record)
                .into(vaccine)
                .map(orgId).toPropertyWhenPresent("orgId", record::getOrgId)
                .map(orgName).toPropertyWhenPresent("orgName", record::getOrgName)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(manufacturer).toPropertyWhenPresent("manufacturer", record::getManufacturer)
                .map(batchNumber).toPropertyWhenPresent("batchNumber", record::getBatchNumber)
                .map(totalDose).toPropertyWhenPresent("totalDose", record::getTotalDose)
                .map(doseInterval).toPropertyWhenPresent("doseInterval", record::getDoseInterval)
                .map(stock).toPropertyWhenPresent("stock", record::getStock)
                .map(oosUrl).toPropertyWhenPresent("oosUrl", record::getOosUrl)
                .map(orderNum).toPropertyWhenPresent("orderNum", record::getOrderNum)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Vaccine>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, orgId, orgName, name, manufacturer, batchNumber, totalDose, doseInterval, stock, oosUrl, orderNum, status, createTime)
                .from(vaccine);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Vaccine>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, orgId, orgName, name, manufacturer, batchNumber, totalDose, doseInterval, stock, oosUrl, orderNum, status, createTime)
                .from(vaccine);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Vaccine selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, orgId, orgName, name, manufacturer, batchNumber, totalDose, doseInterval, stock, oosUrl, orderNum, status, createTime)
                .from(vaccine)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Vaccine record) {
        return UpdateDSL.updateWithMapper(this::update, vaccine)
                .set(orgId).equalTo(record::getOrgId)
                .set(orgName).equalTo(record::getOrgName)
                .set(name).equalTo(record::getName)
                .set(manufacturer).equalTo(record::getManufacturer)
                .set(batchNumber).equalTo(record::getBatchNumber)
                .set(totalDose).equalTo(record::getTotalDose)
                .set(doseInterval).equalTo(record::getDoseInterval)
                .set(stock).equalTo(record::getStock)
                .set(oosUrl).equalTo(record::getOosUrl)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Vaccine record) {
        return UpdateDSL.updateWithMapper(this::update, vaccine)
                .set(orgId).equalToWhenPresent(record::getOrgId)
                .set(orgName).equalToWhenPresent(record::getOrgName)
                .set(name).equalToWhenPresent(record::getName)
                .set(manufacturer).equalToWhenPresent(record::getManufacturer)
                .set(batchNumber).equalToWhenPresent(record::getBatchNumber)
                .set(totalDose).equalToWhenPresent(record::getTotalDose)
                .set(doseInterval).equalToWhenPresent(record::getDoseInterval)
                .set(stock).equalToWhenPresent(record::getStock)
                .set(oosUrl).equalToWhenPresent(record::getOosUrl)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Vaccine record) {
        return UpdateDSL.updateWithMapper(this::update, vaccine)
                .set(orgId).equalTo(record::getOrgId)
                .set(orgName).equalTo(record::getOrgName)
                .set(name).equalTo(record::getName)
                .set(manufacturer).equalTo(record::getManufacturer)
                .set(batchNumber).equalTo(record::getBatchNumber)
                .set(totalDose).equalTo(record::getTotalDose)
                .set(doseInterval).equalTo(record::getDoseInterval)
                .set(stock).equalTo(record::getStock)
                .set(oosUrl).equalTo(record::getOosUrl)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Vaccine record) {
        return UpdateDSL.updateWithMapper(this::update, vaccine)
                .set(orgId).equalToWhenPresent(record::getOrgId)
                .set(orgName).equalToWhenPresent(record::getOrgName)
                .set(name).equalToWhenPresent(record::getName)
                .set(manufacturer).equalToWhenPresent(record::getManufacturer)
                .set(batchNumber).equalToWhenPresent(record::getBatchNumber)
                .set(totalDose).equalToWhenPresent(record::getTotalDose)
                .set(doseInterval).equalToWhenPresent(record::getDoseInterval)
                .set(stock).equalToWhenPresent(record::getStock)
                .set(oosUrl).equalToWhenPresent(record::getOosUrl)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}