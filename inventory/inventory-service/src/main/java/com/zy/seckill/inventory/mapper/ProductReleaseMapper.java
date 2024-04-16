package com.zy.seckill.inventory.mapper;

import static com.zy.seckill.inventory.mapper.ProductReleaseDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.zy.seckill.inventory.bo.po.ProductRelease;

import java.util.List;
import javax.annotation.Generated;

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

@Mapper
public interface ProductReleaseMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<ProductRelease> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ProductReleaseResult")
    ProductRelease selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ProductReleaseResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="site_id", property="siteId", jdbcType=JdbcType.BIGINT),
        @Result(column="site_name", property="siteName", jdbcType=JdbcType.VARCHAR),
        @Result(column="dose", property="dose", jdbcType=JdbcType.INTEGER),
        @Result(column="amount", property="amount", jdbcType=JdbcType.INTEGER),
        @Result(column="dock_amount", property="dockAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="useup_time", property="useupTime", jdbcType=JdbcType.BIGINT),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_batch", property="productBatch", jdbcType=JdbcType.VARCHAR),
        @Result(column="manufacturer", property="manufacturer", jdbcType=JdbcType.VARCHAR),
        @Result(column="dose_time", property="doseTime", jdbcType=JdbcType.BIGINT),
        @Result(column="time_period", property="timePeriod", jdbcType=JdbcType.BIGINT),
    @Result(column="timePeriod_name", property="timePeriodName", jdbcType=JdbcType.VARCHAR),
        @Result(column="contact_name", property="contactName", jdbcType=JdbcType.VARCHAR),
        @Result(column="contact_mobile", property="contactMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<ProductRelease> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(productRelease);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, productRelease);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, productRelease)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(ProductRelease record) {
        return insert(SqlBuilder.insert(record)
                .into(productRelease)
                .map(name).toProperty("name")
                .map(siteId).toProperty("siteId")
                .map(siteName).toProperty("siteName")
                .map(dose).toProperty("dose")
                .map(amount).toProperty("amount")
                .map(dockAmount).toProperty("dockAmount")
                .map(useupTime).toProperty("useupTime")
                .map(version).toProperty("version")
                .map(startTime).toProperty("startTime")
                .map(productId).toProperty("productId")
                .map(productName).toProperty("productName")
                .map(productBatch).toProperty("productBatch")
                .map(manufacturer).toProperty("manufacturer")
                .map(doseTime).toProperty("doseTime")
                .map(timePeriod).toProperty("timePeriod")
                .map(timeperiodName).toProperty("timeperiodName")
                .map(contactName).toProperty("contactName")
                .map(contactMobile).toProperty("contactMobile")
                .map(status).toProperty("status")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(ProductRelease record) {
        return insert(SqlBuilder.insert(record)
                .into(productRelease)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(siteId).toPropertyWhenPresent("siteId", record::getSiteId)
                .map(siteName).toPropertyWhenPresent("siteName", record::getSiteName)
                .map(dose).toPropertyWhenPresent("dose", record::getDose)
                .map(amount).toPropertyWhenPresent("amount", record::getAmount)
                .map(dockAmount).toPropertyWhenPresent("dockAmount", record::getDockAmount)
                .map(useupTime).toPropertyWhenPresent("useupTime", record::getUseupTime)
                .map(version).toPropertyWhenPresent("version", record::getVersion)
                .map(startTime).toPropertyWhenPresent("startTime", record::getStartTime)
                .map(productId).toPropertyWhenPresent("productId", record::getProductId)
                .map(productName).toPropertyWhenPresent("productName", record::getProductName)
                .map(productBatch).toPropertyWhenPresent("productBatch", record::getProductBatch)
                .map(manufacturer).toPropertyWhenPresent("manufacturer", record::getManufacturer)
                .map(contactName).toPropertyWhenPresent("contactName", record::getContactName)
                .map(contactMobile).toPropertyWhenPresent("contactMobile", record::getContactMobile)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<ProductRelease>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, siteId, siteName, dose, amount, dockAmount, useupTime, version, startTime, productId, productName, productBatch, manufacturer, doseTime, timePeriod, timeperiodName, contactName, contactMobile, status, createTime)
                .from(productRelease);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<ProductRelease>> selectByExampleOne() {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, siteId, siteName, dose, amount, dockAmount, useupTime, version, startTime, productId, productName, productBatch, manufacturer, doseTime, timePeriod, timeperiodName, contactName, contactMobile, status, createTime)
                .from(productRelease);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<ProductRelease>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, siteId, siteName, dose, amount, dockAmount, useupTime, version, startTime, productId, productName, productBatch, manufacturer, doseTime, timePeriod, timeperiodName, contactName, contactMobile, status, createTime)
                .from(productRelease);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default ProductRelease selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, siteId, siteName, dose, amount, dockAmount, useupTime, version, startTime, productId, productName, productBatch, manufacturer, doseTime, timePeriod, timeperiodName, contactName, contactMobile, status, createTime)
                .from(productRelease)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(ProductRelease record) {
        return UpdateDSL.updateWithMapper(this::update, productRelease)
                .set(name).equalTo(record::getName)
                .set(siteId).equalTo(record::getSiteId)
                .set(siteName).equalTo(record::getSiteName)
                .set(dose).equalTo(record::getDose)
                .set(amount).equalTo(record::getAmount)
                .set(dockAmount).equalTo(record::getDockAmount)
                .set(useupTime).equalTo(record::getUseupTime)
                .set(version).equalTo(record::getVersion)
                .set(startTime).equalTo(record::getStartTime)
                .set(productId).equalTo(record::getProductId)
                .set(productName).equalTo(record::getProductName)
                .set(productBatch).equalTo(record::getProductBatch)
                .set(manufacturer).equalTo(record::getManufacturer)
                .set(contactName).equalTo(record::getContactName)
                .set(contactMobile).equalTo(record::getContactMobile)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(ProductRelease record) {
        return UpdateDSL.updateWithMapper(this::update, productRelease)
                .set(name).equalToWhenPresent(record::getName)
                .set(siteId).equalToWhenPresent(record::getSiteId)
                .set(siteName).equalToWhenPresent(record::getSiteName)
                .set(dose).equalToWhenPresent(record::getDose)
                .set(amount).equalToWhenPresent(record::getAmount)
                .set(dockAmount).equalToWhenPresent(record::getDockAmount)
                .set(useupTime).equalToWhenPresent(record::getUseupTime)
                .set(version).equalToWhenPresent(record::getVersion)
                .set(startTime).equalToWhenPresent(record::getStartTime)
                .set(productId).equalToWhenPresent(record::getProductId)
                .set(productName).equalToWhenPresent(record::getProductName)
                .set(productBatch).equalToWhenPresent(record::getProductBatch)
                .set(manufacturer).equalToWhenPresent(record::getManufacturer)
                .set(contactName).equalToWhenPresent(record::getContactName)
                .set(contactMobile).equalToWhenPresent(record::getContactMobile)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(ProductRelease record) {
        return UpdateDSL.updateWithMapper(this::update, productRelease)
                .set(name).equalTo(record::getName)
                .set(siteId).equalTo(record::getSiteId)
                .set(siteName).equalTo(record::getSiteName)
                .set(dose).equalTo(record::getDose)
                .set(amount).equalTo(record::getAmount)
                .set(dockAmount).equalTo(record::getDockAmount)
                .set(useupTime).equalTo(record::getUseupTime)
                .set(version).equalTo(record::getVersion)
                .set(startTime).equalTo(record::getStartTime)
                .set(productId).equalTo(record::getProductId)
                .set(productName).equalTo(record::getProductName)
                .set(productBatch).equalTo(record::getProductBatch)
                .set(manufacturer).equalTo(record::getManufacturer)
                .set(contactName).equalTo(record::getContactName)
                .set(contactMobile).equalTo(record::getContactMobile)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(ProductRelease record) {
        return UpdateDSL.updateWithMapper(this::update, productRelease)
                .set(name).equalToWhenPresent(record::getName)
                .set(siteId).equalToWhenPresent(record::getSiteId)
                .set(siteName).equalToWhenPresent(record::getSiteName)
                .set(dose).equalToWhenPresent(record::getDose)
                .set(amount).equalToWhenPresent(record::getAmount)
                .set(dockAmount).equalToWhenPresent(record::getDockAmount)
                .set(useupTime).equalToWhenPresent(record::getUseupTime)
                .set(version).equalToWhenPresent(record::getVersion)
                .set(startTime).equalToWhenPresent(record::getStartTime)
                .set(productId).equalToWhenPresent(record::getProductId)
                .set(productName).equalToWhenPresent(record::getProductName)
                .set(productBatch).equalToWhenPresent(record::getProductBatch)
                .set(manufacturer).equalToWhenPresent(record::getManufacturer)
                .set(contactName).equalToWhenPresent(record::getContactName)
                .set(contactMobile).equalToWhenPresent(record::getContactMobile)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Update("update product_release set dock_amount = dock_amount-#{amount},version = version+1 where id = #{productReleaseId} and dock_amount-#{amount} >= 0 and version = #{version};")
    int reduceDock(
            @Param("amount") Integer amount,
            @Param("productReleaseId") Long productReleaseId,
            @Param("version") Integer version
    );

    @Select("select version from product_release where id=#{productReleaseId};")
    int selectVersionNum(
            @Param("productReleaseId") Long productReleaseId
    );
}