package com.jsxa.vapp.common.mapper;

import com.jsxa.vapp.common.bo.po.SysFile;
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

import static com.jsxa.vapp.common.mapper.SysFileDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface SysFileMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<SysFile> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SysFileResult")
    SysFile selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SysFileResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.BIGINT),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_key", property="fileKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="access_path", property="accessPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="store_path", property="storePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="ori_file_name", property="oriFileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="active_flag", property="activeFlag", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<SysFile> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(sysFile);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, sysFile);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, sysFile)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(SysFile record) {
        return insert(SqlBuilder.insert(record)
                .into(sysFile)
                .map(name).toProperty("name")
                .map(type).toProperty("type")
                .map(fileName).toProperty("fileName")
                .map(fileKey).toProperty("fileKey")
                .map(accessPath).toProperty("accessPath")
                .map(storePath).toProperty("storePath")
                .map(oriFileName).toProperty("oriFileName")
                .map(orderNum).toProperty("orderNum")
                .map(activeFlag).toProperty("activeFlag")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(SysFile record) {
        return insert(SqlBuilder.insert(record)
                .into(sysFile)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(type).toPropertyWhenPresent("type", record::getType)
                .map(fileName).toPropertyWhenPresent("fileName", record::getFileName)
                .map(fileKey).toPropertyWhenPresent("fileKey", record::getFileKey)
                .map(accessPath).toPropertyWhenPresent("accessPath", record::getAccessPath)
                .map(storePath).toPropertyWhenPresent("storePath", record::getStorePath)
                .map(oriFileName).toPropertyWhenPresent("oriFileName", record::getOriFileName)
                .map(orderNum).toPropertyWhenPresent("orderNum", record::getOrderNum)
                .map(activeFlag).toPropertyWhenPresent("activeFlag", record::getActiveFlag)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<SysFile>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, type, fileName, fileKey, accessPath, storePath,oriFileName, orderNum, activeFlag, createTime)
                .from(sysFile);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SysFile>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, type, fileName, fileKey, accessPath, storePath,oriFileName, orderNum, activeFlag, createTime)
                .from(sysFile);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SysFile>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, type, fileName, fileKey, accessPath, storePath,oriFileName, orderNum, activeFlag, createTime)
                .from(sysFile);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default SysFile selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, type, fileName, fileKey, accessPath, storePath,oriFileName, orderNum, activeFlag, createTime)
                .from(sysFile)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(SysFile record) {
        return UpdateDSL.updateWithMapper(this::update, sysFile)
                .set(name).equalTo(record::getName)
                .set(type).equalTo(record::getType)
                .set(fileName).equalTo(record::getFileName)
                .set(fileKey).equalTo(record::getFileKey)
                .set(accessPath).equalTo(record::getAccessPath)
                .set(storePath).equalTo(record::getStorePath)
                .set(oriFileName).equalTo(record::getOriFileName)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(activeFlag).equalTo(record::getActiveFlag)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(SysFile record) {
        return UpdateDSL.updateWithMapper(this::update, sysFile)
                .set(name).equalToWhenPresent(record::getName)
                .set(type).equalToWhenPresent(record::getType)
                .set(fileName).equalToWhenPresent(record::getFileName)
                .set(fileKey).equalToWhenPresent(record::getFileKey)
                .set(accessPath).equalToWhenPresent(record::getAccessPath)
                .set(storePath).equalToWhenPresent(record::getStorePath)
                .set(oriFileName).equalToWhenPresent(record::getOriFileName)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(activeFlag).equalToWhenPresent(record::getActiveFlag)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(SysFile record) {
        return UpdateDSL.updateWithMapper(this::update, sysFile)
                .set(name).equalTo(record::getName)
                .set(type).equalTo(record::getType)
                .set(fileName).equalTo(record::getFileName)
                .set(fileKey).equalTo(record::getFileKey)
                .set(accessPath).equalTo(record::getAccessPath)
                .set(storePath).equalTo(record::getStorePath)
                .set(oriFileName).equalTo(record::getOriFileName)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(activeFlag).equalTo(record::getActiveFlag)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(SysFile record) {
        return UpdateDSL.updateWithMapper(this::update, sysFile)
                .set(name).equalToWhenPresent(record::getName)
                .set(type).equalToWhenPresent(record::getType)
                .set(fileName).equalToWhenPresent(record::getFileName)
                .set(fileKey).equalToWhenPresent(record::getFileKey)
                .set(accessPath).equalToWhenPresent(record::getAccessPath)
                .set(storePath).equalToWhenPresent(record::getStorePath)
                .set(oriFileName).equalToWhenPresent(record::getOriFileName)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(activeFlag).equalToWhenPresent(record::getActiveFlag)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}