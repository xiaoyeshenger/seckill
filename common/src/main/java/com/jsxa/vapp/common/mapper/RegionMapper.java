package com.jsxa.vapp.common.mapper;

import com.jsxa.vapp.common.bo.po.Region;
import org.apache.ibatis.annotations.*;
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

import javax.annotation.Generated;
import java.util.List;

import static com.jsxa.vapp.common.mapper.RegionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface RegionMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Region> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RegionResult")
    Region selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RegionResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="longitude", property="longitude", jdbcType=JdbcType.VARCHAR),
        @Result(column="latitude", property="latitude", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_code", property="parentCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="data_type", property="dataType", jdbcType=JdbcType.INTEGER),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
//        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<Region> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(region);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, region);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, region)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Region record) {
        return insert(SqlBuilder.insert(record)
                .into(region)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(value).toProperty("value")
                .map(code).toProperty("code")
                .map(type).toProperty("type")
                .map(dataType).toProperty("dataType")
                .map(orderNum).toProperty("orderNum")
                .map(address).toProperty("address")
                .map(longitude).toProperty("longitude")
                .map(latitude).toProperty("latitude")
                .map(parentCode).toProperty("parentCode")
//                .map(level).toProperty("level")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Region record) {
        return insert(SqlBuilder.insert(record)
                .into(region)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(value).toPropertyWhenPresent("value", record::getValue)
                .map(code).toPropertyWhenPresent("code", record::getCode)
                .map(type).toPropertyWhenPresent("type", record::getType)
                .map(dataType).toPropertyWhenPresent("dataType", record::getDataType)
                .map(orderNum).toPropertyWhenPresent("orderNum", record::getOrderNum)
                .map(address).toPropertyWhenPresent("address", record::getAddress)
                .map(longitude).toPropertyWhenPresent("longitude", record::getLongitude)
                .map(latitude).toPropertyWhenPresent("latitude", record::getLatitude)
                .map(parentCode).toPropertyWhenPresent("parentCode", record::getParentCode)
//                .map(level).toPropertyWhenPresent("level", record::getLevel)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Region>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, value, code, type,dataType,orderNum, address, longitude, latitude, parentCode, createTime)
                .from(region);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Region>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, value, code, type,dataType,orderNum, address, longitude, latitude, parentCode, createTime)
                .from(region);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Region>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, value, code, type,dataType,orderNum, address, longitude, latitude, parentCode, createTime)
                .from(region);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Region selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, value, code, type,dataType,orderNum, address, longitude, latitude, parentCode, createTime)
                .from(region)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Region record) {
        return UpdateDSL.updateWithMapper(this::update, region)
                .set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(value).equalTo(record::getValue)
                .set(code).equalTo(record::getCode)
                .set(type).equalTo(record::getType)
                .set(dataType).equalTo(record::getDataType)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(address).equalTo(record::getAddress)
                .set(longitude).equalTo(record::getLongitude)
                .set(latitude).equalTo(record::getLatitude)
                .set(parentCode).equalTo(record::getParentCode)
//                .set(level).equalTo(record::getLevel)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Region record) {
        return UpdateDSL.updateWithMapper(this::update, region)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(value).equalToWhenPresent(record::getValue)
                .set(code).equalToWhenPresent(record::getCode)
                .set(type).equalToWhenPresent(record::getType)
                .set(dataType).equalToWhenPresent(record::getDataType)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(address).equalToWhenPresent(record::getAddress)
                .set(longitude).equalToWhenPresent(record::getLongitude)
                .set(latitude).equalToWhenPresent(record::getLatitude)
                .set(parentCode).equalToWhenPresent(record::getParentCode)
//                .set(level).equalToWhenPresent(record::getLevel)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Region record) {
        return UpdateDSL.updateWithMapper(this::update, region)
                .set(name).equalTo(record::getName)
                .set(value).equalTo(record::getValue)
                .set(code).equalTo(record::getCode)
                .set(type).equalTo(record::getType)
                .set(dataType).equalTo(record::getDataType)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(address).equalTo(record::getAddress)
                .set(longitude).equalTo(record::getLongitude)
                .set(latitude).equalTo(record::getLatitude)
                .set(parentCode).equalTo(record::getParentCode)
//                .set(level).equalTo(record::getLevel)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Region record) {
        return UpdateDSL.updateWithMapper(this::update, region)
                .set(name).equalToWhenPresent(record::getName)
                .set(value).equalToWhenPresent(record::getValue)
                .set(code).equalToWhenPresent(record::getCode)
                .set(type).equalToWhenPresent(record::getType)
                .set(dataType).equalToWhenPresent(record::getDataType)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(address).equalToWhenPresent(record::getAddress)
                .set(longitude).equalToWhenPresent(record::getLongitude)
                .set(latitude).equalToWhenPresent(record::getLatitude)
                .set(parentCode).equalToWhenPresent(record::getParentCode)
//                .set(level).equalToWhenPresent(record::getLevel)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}