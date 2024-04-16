package com.zy.seckill.common.mapper;

import com.zy.seckill.common.bo.po.OperateRecord;
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

import static com.zy.seckill.common.mapper.OperateRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface OperateRecordMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<OperateRecord> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OperateRecordResult")
    OperateRecord selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OperateRecordResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="object_type", property="objectType", jdbcType=JdbcType.VARCHAR),
        @Result(column="object_id", property="objectId", jdbcType=JdbcType.BIGINT),
        @Result(column="operate_type", property="operateType", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator_id", property="operatorId", jdbcType=JdbcType.BIGINT),
        @Result(column="operator_name", property="operatorName", jdbcType=JdbcType.VARCHAR),
        @Result(column="operate_time", property="operateTime", jdbcType=JdbcType.BIGINT)
    })
    List<OperateRecord> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(operateRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, operateRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, operateRecord)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(OperateRecord record) {
        return insert(SqlBuilder.insert(record)
                .into(operateRecord)
                .map(id).toProperty("id")
                .map(objectType).toProperty("objectType")
                .map(objectId).toProperty("objectId")
                .map(operateType).toProperty("operateType")
                .map(operatorId).toProperty("operatorId")
                .map(operatorName).toProperty("operatorName")
                .map(operateTime).toProperty("operateTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(OperateRecord record) {
        return insert(SqlBuilder.insert(record)
                .into(operateRecord)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(objectType).toPropertyWhenPresent("objectType", record::getObjectType)
                .map(objectId).toPropertyWhenPresent("objectId", record::getObjectId)
                .map(operateType).toPropertyWhenPresent("operateType", record::getOperateType)
                .map(operatorId).toPropertyWhenPresent("operatorId", record::getOperatorId)
                .map(operatorName).toPropertyWhenPresent("operatorName", record::getOperatorName)
                .map(operateTime).toPropertyWhenPresent("operateTime", record::getOperateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<OperateRecord>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, objectType, objectId, operateType, operatorId, operatorName, operateTime)
                .from(operateRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<OperateRecord>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, objectType, objectId, operateType, operatorId, operatorName, operateTime)
                .from(operateRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default OperateRecord selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, objectType, objectId, operateType, operatorId, operatorName, operateTime)
                .from(operateRecord)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(OperateRecord record) {
        return UpdateDSL.updateWithMapper(this::update, operateRecord)
                .set(id).equalTo(record::getId)
                .set(objectType).equalTo(record::getObjectType)
                .set(objectId).equalTo(record::getObjectId)
                .set(operateType).equalTo(record::getOperateType)
                .set(operatorId).equalTo(record::getOperatorId)
                .set(operatorName).equalTo(record::getOperatorName)
                .set(operateTime).equalTo(record::getOperateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(OperateRecord record) {
        return UpdateDSL.updateWithMapper(this::update, operateRecord)
                .set(id).equalToWhenPresent(record::getId)
                .set(objectType).equalToWhenPresent(record::getObjectType)
                .set(objectId).equalToWhenPresent(record::getObjectId)
                .set(operateType).equalToWhenPresent(record::getOperateType)
                .set(operatorId).equalToWhenPresent(record::getOperatorId)
                .set(operatorName).equalToWhenPresent(record::getOperatorName)
                .set(operateTime).equalToWhenPresent(record::getOperateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(OperateRecord record) {
        return UpdateDSL.updateWithMapper(this::update, operateRecord)
                .set(objectType).equalTo(record::getObjectType)
                .set(objectId).equalTo(record::getObjectId)
                .set(operateType).equalTo(record::getOperateType)
                .set(operatorId).equalTo(record::getOperatorId)
                .set(operatorName).equalTo(record::getOperatorName)
                .set(operateTime).equalTo(record::getOperateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(OperateRecord record) {
        return UpdateDSL.updateWithMapper(this::update, operateRecord)
                .set(objectType).equalToWhenPresent(record::getObjectType)
                .set(objectId).equalToWhenPresent(record::getObjectId)
                .set(operateType).equalToWhenPresent(record::getOperateType)
                .set(operatorId).equalToWhenPresent(record::getOperatorId)
                .set(operatorName).equalToWhenPresent(record::getOperatorName)
                .set(operateTime).equalToWhenPresent(record::getOperateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}