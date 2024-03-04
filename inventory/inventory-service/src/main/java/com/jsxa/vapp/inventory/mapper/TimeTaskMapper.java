package com.jsxa.vapp.inventory.mapper;

import com.jsxa.vapp.inventory.bo.po.TimeTask;
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

import static com.jsxa.vapp.inventory.mapper.TimeTaskDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface TimeTaskMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<TimeTask> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TimeTaskResult")
    TimeTask selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TimeTaskResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.BIGINT),
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.BIGINT),
        @Result(column="project_name", property="projectName", jdbcType=JdbcType.VARCHAR),
        @Result(column="job_id", property="jobId", jdbcType=JdbcType.VARCHAR),
        @Result(column="xxlJobAdmin_address", property="xxlJobAdminAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="corn", property="corn", jdbcType=JdbcType.VARCHAR),
        @Result(column="handler", property="handler", jdbcType=JdbcType.VARCHAR),
        @Result(column="app_name", property="appName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<TimeTask> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(timeTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, timeTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, timeTask)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TimeTask record) {
        return insert(SqlBuilder.insert(record)
                .into(timeTask)
                .map(id).toProperty("id")
                .map(type).toProperty("type")
                .map(projectId).toProperty("projectId")
                .map(projectName).toProperty("projectName")
                .map(jobId).toProperty("jobId")
                .map(xxljobadminAddress).toProperty("xxlJobAdminAddress")
                .map(corn).toProperty("corn")
                .map(handler).toProperty("handler")
                .map(appName).toProperty("appName")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TimeTask record) {
        return insert(SqlBuilder.insert(record)
                .into(timeTask)
                .map(id).toProperty("id")
                .map(type).toPropertyWhenPresent("type", record::getType)
                .map(projectId).toPropertyWhenPresent("projectId", record::getProjectId)
                .map(projectName).toPropertyWhenPresent("projectName", record::getProjectName)
                .map(jobId).toPropertyWhenPresent("jobId", record::getJobId)
                .map(xxljobadminAddress).toPropertyWhenPresent("xxlJobAdminAddress", record::getXxlJobAdminAddress)
                .map(corn).toPropertyWhenPresent("corn", record::getCorn)
                .map(handler).toPropertyWhenPresent("handler", record::getHandler)
                .map(appName).toPropertyWhenPresent("appName", record::getAppName)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }


    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<TimeTask>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne ,id, type, projectId, projectName, jobId, xxljobadminAddress, corn, handler, appName, createTime)
                .from(timeTask);
    }
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<TimeTask>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, type, projectId, projectName, jobId, xxljobadminAddress, corn, handler, appName, createTime)
                .from(timeTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<TimeTask>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, type,  projectId, projectName, jobId, xxljobadminAddress, corn, handler, appName, createTime)
                .from(timeTask);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default TimeTask selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, type,  projectId, projectName, jobId, xxljobadminAddress, corn, handler, appName, createTime)
                .from(timeTask)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(TimeTask record) {
        return UpdateDSL.updateWithMapper(this::update, timeTask)
                .set(type).equalTo(record::getType)
                .set(projectId).equalTo(record::getProjectId)
                .set(projectName).equalTo(record::getProjectName)
                .set(jobId).equalTo(record::getJobId)
                .set(xxljobadminAddress).equalTo(record::getXxlJobAdminAddress)
                .set(corn).equalTo(record::getCorn)
                .set(handler).equalTo(record::getHandler)
                .set(appName).equalTo(record::getAppName)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(TimeTask record) {
        return UpdateDSL.updateWithMapper(this::update, timeTask)
                .set(type).equalToWhenPresent(record::getType)
                .set(projectId).equalToWhenPresent(record::getProjectId)
                .set(projectName).equalToWhenPresent(record::getProjectName)
                .set(jobId).equalToWhenPresent(record::getJobId)
                .set(xxljobadminAddress).equalToWhenPresent(record::getXxlJobAdminAddress)
                .set(corn).equalToWhenPresent(record::getCorn)
                .set(handler).equalToWhenPresent(record::getHandler)
                .set(appName).equalToWhenPresent(record::getAppName)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(TimeTask record) {
        return UpdateDSL.updateWithMapper(this::update, timeTask)
                .set(type).equalTo(record::getType)
                .set(projectId).equalTo(record::getProjectId)
                .set(projectName).equalTo(record::getProjectName)
                .set(jobId).equalTo(record::getJobId)
                .set(xxljobadminAddress).equalTo(record::getXxlJobAdminAddress)
                .set(corn).equalTo(record::getCorn)
                .set(handler).equalTo(record::getHandler)
                .set(appName).equalTo(record::getAppName)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(TimeTask record) {
        return UpdateDSL.updateWithMapper(this::update, timeTask)
                .set(type).equalToWhenPresent(record::getType)
                .set(projectId).equalToWhenPresent(record::getProjectId)
                .set(projectName).equalToWhenPresent(record::getProjectName)
                .set(jobId).equalToWhenPresent(record::getJobId)
                .set(xxljobadminAddress).equalToWhenPresent(record::getXxlJobAdminAddress)
                .set(corn).equalToWhenPresent(record::getCorn)
                .set(handler).equalToWhenPresent(record::getHandler)
                .set(appName).equalToWhenPresent(record::getAppName)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}