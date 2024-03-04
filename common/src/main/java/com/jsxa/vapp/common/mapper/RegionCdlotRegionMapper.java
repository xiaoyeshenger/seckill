package com.jsxa.vapp.common.mapper;

import static com.jsxa.vapp.common.mapper.RegionCdlotRegionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jsxa.vapp.common.bo.po.RegionCdlotRegion;
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
public interface RegionCdlotRegionMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<RegionCdlotRegion> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RegionCdlotRegionResult")
    RegionCdlotRegion selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RegionCdlotRegionResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="region_code", property="regionCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="cdlotRegion_code", property="cdlotRegionCode", jdbcType=JdbcType.VARCHAR)
    })
    List<RegionCdlotRegion> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(regionCdlotRegion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, regionCdlotRegion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, regionCdlotRegion)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RegionCdlotRegion record) {
        return insert(SqlBuilder.insert(record)
                .into(regionCdlotRegion)
                .map(regionCode).toProperty("regionCode")
                .map(cdlotregionCode).toProperty("cdlotRegionCode")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RegionCdlotRegion record) {
        return insert(SqlBuilder.insert(record)
                .into(regionCdlotRegion)
                .map(regionCode).toPropertyWhenPresent("regionCode", record::getRegionCode)
                .map(cdlotregionCode).toPropertyWhenPresent("cdlotRegionCode", record::getCdlotRegionCode)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RegionCdlotRegion>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, regionCode, cdlotregionCode)
                .from(regionCdlotRegion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RegionCdlotRegion>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, regionCode, cdlotregionCode)
                .from(regionCdlotRegion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RegionCdlotRegion selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, regionCode, cdlotregionCode)
                .from(regionCdlotRegion)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RegionCdlotRegion record) {
        return UpdateDSL.updateWithMapper(this::update, regionCdlotRegion)
                .set(regionCode).equalTo(record::getRegionCode)
                .set(cdlotregionCode).equalTo(record::getCdlotRegionCode);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RegionCdlotRegion record) {
        return UpdateDSL.updateWithMapper(this::update, regionCdlotRegion)
                .set(regionCode).equalToWhenPresent(record::getRegionCode)
                .set(cdlotregionCode).equalToWhenPresent(record::getCdlotRegionCode);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RegionCdlotRegion record) {
        return UpdateDSL.updateWithMapper(this::update, regionCdlotRegion)
                .set(regionCode).equalTo(record::getRegionCode)
                .set(cdlotregionCode).equalTo(record::getCdlotRegionCode)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RegionCdlotRegion record) {
        return UpdateDSL.updateWithMapper(this::update, regionCdlotRegion)
                .set(regionCode).equalToWhenPresent(record::getRegionCode)
                .set(cdlotregionCode).equalToWhenPresent(record::getCdlotRegionCode)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}