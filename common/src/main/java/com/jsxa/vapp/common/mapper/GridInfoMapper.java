package com.jsxa.vapp.common.mapper;

import static com.jsxa.vapp.common.mapper.GridInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jsxa.vapp.common.bo.po.GridInfo;
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
public interface GridInfoMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<GridInfo> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("GridInfoResult")
    GridInfo selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="GridInfoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="street_code", property="streetCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="street_name", property="streetName", jdbcType=JdbcType.VARCHAR),
        @Result(column="community", property="community", jdbcType=JdbcType.VARCHAR),
        @Result(column="community_name", property="communityName", jdbcType=JdbcType.VARCHAR),
        @Result(column="grid_code", property="gridCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="grid_name", property="gridName", jdbcType=JdbcType.VARCHAR),
        @Result(column="address_code", property="addressCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="address_name", property="addressName", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<GridInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(gridInfo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, gridInfo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, gridInfo)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(GridInfo record) {
        return insert(SqlBuilder.insert(record)
                .into(gridInfo)
                .map(streetCode).toProperty("streetCode")
                .map(streetName).toProperty("streetName")
                .map(community).toProperty("community")
                .map(communityName).toProperty("communityName")
                .map(gridCode).toProperty("gridCode")
                .map(gridName).toProperty("gridName")
                .map(addressCode).toProperty("addressCode")
                .map(addressName).toProperty("addressName")
                .map(status).toProperty("status")
                .map(delFlag).toProperty("delFlag")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(GridInfo record) {
        return insert(SqlBuilder.insert(record)
                .into(gridInfo)
                .map(streetCode).toPropertyWhenPresent("streetCode", record::getStreetCode)
                .map(streetName).toPropertyWhenPresent("streetName", record::getStreetName)
                .map(community).toPropertyWhenPresent("community", record::getCommunity)
                .map(communityName).toPropertyWhenPresent("communityName", record::getCommunityName)
                .map(gridCode).toPropertyWhenPresent("gridCode", record::getGridCode)
                .map(gridName).toPropertyWhenPresent("gridName", record::getGridName)
                .map(addressCode).toPropertyWhenPresent("addressCode", record::getAddressCode)
                .map(addressName).toPropertyWhenPresent("addressName", record::getAddressName)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(delFlag).toPropertyWhenPresent("delFlag", record::getDelFlag)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<GridInfo>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, streetCode, streetName, community, communityName, gridCode, gridName, addressCode, addressName, status, delFlag, createTime)
                .from(gridInfo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<GridInfo>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, streetCode, streetName, community, communityName, gridCode, gridName, addressCode, addressName, status, delFlag, createTime)
                .from(gridInfo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default GridInfo selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, streetCode, streetName, community, communityName, gridCode, gridName, addressCode, addressName, status, delFlag, createTime)
                .from(gridInfo)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(GridInfo record) {
        return UpdateDSL.updateWithMapper(this::update, gridInfo)
                .set(streetCode).equalTo(record::getStreetCode)
                .set(streetName).equalTo(record::getStreetName)
                .set(community).equalTo(record::getCommunity)
                .set(communityName).equalTo(record::getCommunityName)
                .set(gridCode).equalTo(record::getGridCode)
                .set(gridName).equalTo(record::getGridName)
                .set(addressCode).equalTo(record::getAddressCode)
                .set(addressName).equalTo(record::getAddressName)
                .set(status).equalTo(record::getStatus)
                .set(delFlag).equalTo(record::getDelFlag)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(GridInfo record) {
        return UpdateDSL.updateWithMapper(this::update, gridInfo)
                .set(streetCode).equalToWhenPresent(record::getStreetCode)
                .set(streetName).equalToWhenPresent(record::getStreetName)
                .set(community).equalToWhenPresent(record::getCommunity)
                .set(communityName).equalToWhenPresent(record::getCommunityName)
                .set(gridCode).equalToWhenPresent(record::getGridCode)
                .set(gridName).equalToWhenPresent(record::getGridName)
                .set(addressCode).equalToWhenPresent(record::getAddressCode)
                .set(addressName).equalToWhenPresent(record::getAddressName)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(delFlag).equalToWhenPresent(record::getDelFlag)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(GridInfo record) {
        return UpdateDSL.updateWithMapper(this::update, gridInfo)
                .set(streetCode).equalTo(record::getStreetCode)
                .set(streetName).equalTo(record::getStreetName)
                .set(community).equalTo(record::getCommunity)
                .set(communityName).equalTo(record::getCommunityName)
                .set(gridCode).equalTo(record::getGridCode)
                .set(gridName).equalTo(record::getGridName)
                .set(addressCode).equalTo(record::getAddressCode)
                .set(addressName).equalTo(record::getAddressName)
                .set(status).equalTo(record::getStatus)
                .set(delFlag).equalTo(record::getDelFlag)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, SqlBuilder.isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(GridInfo record) {
        return UpdateDSL.updateWithMapper(this::update, gridInfo)
                .set(streetCode).equalToWhenPresent(record::getStreetCode)
                .set(streetName).equalToWhenPresent(record::getStreetName)
                .set(community).equalToWhenPresent(record::getCommunity)
                .set(communityName).equalToWhenPresent(record::getCommunityName)
                .set(gridCode).equalToWhenPresent(record::getGridCode)
                .set(gridName).equalToWhenPresent(record::getGridName)
                .set(addressCode).equalToWhenPresent(record::getAddressCode)
                .set(addressName).equalToWhenPresent(record::getAddressName)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(delFlag).equalToWhenPresent(record::getDelFlag)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, SqlBuilder.isEqualTo(record::getId))
                .build()
                .execute();
    }
}