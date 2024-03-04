package com.jsxa.vapp.common.mapper;

import static com.jsxa.vapp.common.mapper.RegionCdlotTokenDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jsxa.vapp.common.bo.po.RegionCdlotToken;
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
public interface RegionCdlotTokenMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<RegionCdlotToken> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RegionCdlotTokenResult")
    RegionCdlotToken selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RegionCdlotTokenResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="region_code", property="regionCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="cdlot_token", property="cdlotToken", jdbcType=JdbcType.VARCHAR)
    })
    List<RegionCdlotToken> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(regionCdlotToken);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, regionCdlotToken);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, regionCdlotToken)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RegionCdlotToken record) {
        return insert(SqlBuilder.insert(record)
                .into(regionCdlotToken)
                .map(regionCode).toProperty("regionCode")
                .map(cdlotToken).toProperty("cdlotToken")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RegionCdlotToken record) {
        return insert(SqlBuilder.insert(record)
                .into(regionCdlotToken)
                .map(regionCode).toPropertyWhenPresent("regionCode", record::getRegionCode)
                .map(cdlotToken).toPropertyWhenPresent("cdlotToken", record::getCdlotToken)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RegionCdlotToken>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, regionCode, cdlotToken)
                .from(regionCdlotToken);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RegionCdlotToken>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, regionCode, cdlotToken)
                .from(regionCdlotToken);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RegionCdlotToken selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, regionCode, cdlotToken)
                .from(regionCdlotToken)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RegionCdlotToken record) {
        return UpdateDSL.updateWithMapper(this::update, regionCdlotToken)
                .set(regionCode).equalTo(record::getRegionCode)
                .set(cdlotToken).equalTo(record::getCdlotToken);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RegionCdlotToken record) {
        return UpdateDSL.updateWithMapper(this::update, regionCdlotToken)
                .set(regionCode).equalToWhenPresent(record::getRegionCode)
                .set(cdlotToken).equalToWhenPresent(record::getCdlotToken);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RegionCdlotToken record) {
        return UpdateDSL.updateWithMapper(this::update, regionCdlotToken)
                .set(regionCode).equalTo(record::getRegionCode)
                .set(cdlotToken).equalTo(record::getCdlotToken)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RegionCdlotToken record) {
        return UpdateDSL.updateWithMapper(this::update, regionCdlotToken)
                .set(regionCode).equalToWhenPresent(record::getRegionCode)
                .set(cdlotToken).equalToWhenPresent(record::getCdlotToken)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}