package com.jsxa.vapp.common.mapper;

import static com.jsxa.vapp.common.mapper.CdlotRegionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jsxa.vapp.common.bo.po.CdlotRegion;
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
public interface CdlotRegionMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<CdlotRegion> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CdlotRegionResult")
    CdlotRegion selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CdlotRegionResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.BIGINT),
        @Result(column="data_type", property="dataType", jdbcType=JdbcType.INTEGER),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
        @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_code", property="parentCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<CdlotRegion> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(cdlotRegion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, cdlotRegion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, cdlotRegion)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(CdlotRegion record) {
        return insert(SqlBuilder.insert(record)
                .into(cdlotRegion)
                .map(name).toProperty("name")
                .map(value).toProperty("value")
                .map(code).toProperty("code")
                .map(type).toProperty("type")
                .map(dataType).toProperty("dataType")
                .map(address).toProperty("address")
                .map(longitude).toProperty("longitude")
                .map(latitude).toProperty("latitude")
                .map(parentCode).toProperty("parentCode")
                .map(orderNum).toProperty("orderNum")
                .map(level).toProperty("level")
                .map(token).toProperty("token")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(CdlotRegion record) {
        return insert(SqlBuilder.insert(record)
                .into(cdlotRegion)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(value).toPropertyWhenPresent("value", record::getValue)
                .map(code).toPropertyWhenPresent("code", record::getCode)
                .map(type).toPropertyWhenPresent("type", record::getType)
                .map(dataType).toPropertyWhenPresent("dataType", record::getDataType)
                .map(address).toPropertyWhenPresent("address", record::getAddress)
                .map(longitude).toPropertyWhenPresent("longitude", record::getLongitude)
                .map(latitude).toPropertyWhenPresent("latitude", record::getLatitude)
                .map(parentCode).toPropertyWhenPresent("parentCode", record::getParentCode)
                .map(orderNum).toPropertyWhenPresent("orderNum", record::getOrderNum)
                .map(level).toPropertyWhenPresent("level", record::getLevel)
                .map(token).toPropertyWhenPresent("token", record::getToken)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<CdlotRegion>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, value, code, type, dataType, address, longitude, latitude, parentCode, orderNum, level, token, createTime)
                .from(cdlotRegion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<CdlotRegion>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, value, code, type, dataType, address, longitude, latitude, parentCode, orderNum, level, token, createTime)
                .from(cdlotRegion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default CdlotRegion selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, value, code, type, dataType, address, longitude, latitude, parentCode, orderNum, level, token, createTime)
                .from(cdlotRegion)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(CdlotRegion record) {
        return UpdateDSL.updateWithMapper(this::update, cdlotRegion)
                .set(name).equalTo(record::getName)
                .set(value).equalTo(record::getValue)
                .set(code).equalTo(record::getCode)
                .set(type).equalTo(record::getType)
                .set(dataType).equalTo(record::getDataType)
                .set(address).equalTo(record::getAddress)
                .set(longitude).equalTo(record::getLongitude)
                .set(latitude).equalTo(record::getLatitude)
                .set(parentCode).equalTo(record::getParentCode)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(level).equalTo(record::getLevel)
                .set(token).equalTo(record::getToken)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(CdlotRegion record) {
        return UpdateDSL.updateWithMapper(this::update, cdlotRegion)
                .set(name).equalToWhenPresent(record::getName)
                .set(value).equalToWhenPresent(record::getValue)
                .set(code).equalToWhenPresent(record::getCode)
                .set(type).equalToWhenPresent(record::getType)
                .set(dataType).equalToWhenPresent(record::getDataType)
                .set(address).equalToWhenPresent(record::getAddress)
                .set(longitude).equalToWhenPresent(record::getLongitude)
                .set(latitude).equalToWhenPresent(record::getLatitude)
                .set(parentCode).equalToWhenPresent(record::getParentCode)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(level).equalToWhenPresent(record::getLevel)
                .set(token).equalToWhenPresent(record::getToken)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(CdlotRegion record) {
        return UpdateDSL.updateWithMapper(this::update, cdlotRegion)
                .set(name).equalTo(record::getName)
                .set(value).equalTo(record::getValue)
                .set(code).equalTo(record::getCode)
                .set(type).equalTo(record::getType)
                .set(dataType).equalTo(record::getDataType)
                .set(address).equalTo(record::getAddress)
                .set(longitude).equalTo(record::getLongitude)
                .set(latitude).equalTo(record::getLatitude)
                .set(parentCode).equalTo(record::getParentCode)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(level).equalTo(record::getLevel)
                .set(token).equalTo(record::getToken)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(CdlotRegion record) {
        return UpdateDSL.updateWithMapper(this::update, cdlotRegion)
                .set(name).equalToWhenPresent(record::getName)
                .set(value).equalToWhenPresent(record::getValue)
                .set(code).equalToWhenPresent(record::getCode)
                .set(type).equalToWhenPresent(record::getType)
                .set(dataType).equalToWhenPresent(record::getDataType)
                .set(address).equalToWhenPresent(record::getAddress)
                .set(longitude).equalToWhenPresent(record::getLongitude)
                .set(latitude).equalToWhenPresent(record::getLatitude)
                .set(parentCode).equalToWhenPresent(record::getParentCode)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(level).equalToWhenPresent(record::getLevel)
                .set(token).equalToWhenPresent(record::getToken)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}