package com.zy.seckill.order.mapper;

import static com.zy.seckill.order.mapper.OrderRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

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
import com.zy.seckill.order.bo.po.OrderRecord;

@Mapper
public interface OrderRecordMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<OrderRecord> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OrderRecordResult")
    OrderRecord selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OrderRecordResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="record_type", property="recordType", jdbcType=JdbcType.BIGINT),
        @Result(column="vaild", property="vaild", jdbcType=JdbcType.TINYINT),
        @Result(column="productRelease_id", property="productReleaseId", jdbcType=JdbcType.BIGINT),
        @Result(column="productRelease_name", property="productReleaseName", jdbcType=JdbcType.VARCHAR),
        @Result(column="person_id", property="personId", jdbcType=JdbcType.BIGINT),
        @Result(column="person_name", property="personName", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.TINYINT),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="id_number", property="idNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="open_id", property="openId", jdbcType=JdbcType.VARCHAR),
        @Result(column="site_id", property="siteId", jdbcType=JdbcType.BIGINT),
        @Result(column="site_name", property="siteName", jdbcType=JdbcType.VARCHAR),
        @Result(column="record_status", property="recordStatus", jdbcType=JdbcType.BIGINT),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_batch", property="productBatch", jdbcType=JdbcType.VARCHAR),
        @Result(column="manufacturer", property="manufacturer", jdbcType=JdbcType.VARCHAR),
        @Result(column="dose", property="dose", jdbcType=JdbcType.INTEGER),
        @Result(column="dose_unit", property="doseUnit", jdbcType=JdbcType.VARCHAR),
        @Result(column="appointment_time", property="appointmentTime", jdbcType=JdbcType.BIGINT),
        @Result(column="time_period", property="timePeriod", jdbcType=JdbcType.BIGINT),
        @Result(column="timePeriod_name", property="timePeriodName", jdbcType=JdbcType.VARCHAR),
        @Result(column="dose_time", property="doseTime", jdbcType=JdbcType.BIGINT),
        @Result(column="image_url", property="imageUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="county", property="county", jdbcType=JdbcType.VARCHAR),
        @Result(column="town", property="town", jdbcType=JdbcType.VARCHAR),
        @Result(column="msg", property="msg", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<OrderRecord> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(orderRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, orderRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, orderRecord)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(OrderRecord record) {
        return insert(SqlBuilder.insert(record)
                .into(orderRecord)
                .map(id).toProperty("id")
                .map(recordType).toProperty("recordType")
                .map(productReleaseId).toProperty("productReleaseId")
                .map(productReleaseName).toProperty("productReleaseName")
                .map(personId).toProperty("personId")
                .map(personName).toProperty("personName")
                .map(sex).toProperty("sex")
                .map(age).toProperty("age")
                .map(mobile).toProperty("mobile")
                .map(idNumber).toProperty("idNumber")
                .map(openId).toProperty("openId")
                .map(siteId).toProperty("siteId")
                .map(siteName).toProperty("siteName")
                .map(recordStatus).toProperty("recordStatus")
                .map(productId).toProperty("productId")
                .map(productName).toProperty("productName")
                .map(productBatch).toProperty("productBatch")
                .map(manufacturer).toProperty("manufacturer")
                .map(appointmentTime).toProperty("appointmentTime")
                .map(imageUrl).toProperty("imageUrl")
                .map(msg).toProperty("msg")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(OrderRecord record) {
        return insert(SqlBuilder.insert(record)
                .into(orderRecord)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(recordType).toPropertyWhenPresent("recordType", record::getRecordType)
                .map(productReleaseId).toPropertyWhenPresent("productReleaseId", record::getProductReleaseId)
                .map(productReleaseName).toPropertyWhenPresent("productReleaseName", record::getProductReleaseName)
                .map(personId).toPropertyWhenPresent("personId", record::getPersonId)
                .map(personName).toPropertyWhenPresent("personName", record::getPersonName)
                .map(sex).toPropertyWhenPresent("sex", record::getSex)
                .map(age).toPropertyWhenPresent("age", record::getAge)
                .map(mobile).toPropertyWhenPresent("mobile", record::getMobile)
                .map(openId).toPropertyWhenPresent("openId", record::getOpenId)
                .map(siteId).toPropertyWhenPresent("siteId", record::getSiteId)
                .map(siteName).toPropertyWhenPresent("siteName", record::getSiteName)
                .map(recordStatus).toPropertyWhenPresent("recordStatus", record::getRecordStatus)
                .map(productId).toPropertyWhenPresent("productId", record::getProductId)
                .map(productName).toPropertyWhenPresent("productName", record::getProductName)
                .map(productBatch).toPropertyWhenPresent("productBatch", record::getProductBatch)
                .map(manufacturer).toPropertyWhenPresent("manufacturer", record::getManufacturer)
                .map(appointmentTime).toPropertyWhenPresent("appointmentTime", record::getAppointmentTime)
                .map(imageUrl).toPropertyWhenPresent("imageUrl", record::getImageUrl)
                .map(msg).toPropertyWhenPresent("msg", record::getMsg)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<OrderRecord>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, recordType, productReleaseId,productReleaseName, personId, personName, sex, age, mobile, idNumber, openId, siteId, siteName, recordStatus, productId, productName, productBatch, manufacturer, appointmentTime,  imageUrl, msg, createTime)
                .from(orderRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<OrderRecord>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, recordType, productReleaseId,productReleaseName, personId, personName, sex, age, mobile, idNumber, openId, siteId, siteName, recordStatus, productId, productName, productBatch, manufacturer, appointmentTime,imageUrl,msg, createTime)
                .from(orderRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default OrderRecord selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, recordType, productReleaseId,productReleaseName, personId, personName, sex, age, mobile, idNumber, openId, siteId, siteName, recordStatus, productId, productName, productBatch, manufacturer,  appointmentTime,  imageUrl, msg, createTime)
                .from(orderRecord)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(OrderRecord record) {
        return UpdateDSL.updateWithMapper(this::update, orderRecord)
                .set(recordType).equalTo(record::getRecordType)
                .set(productReleaseId).equalTo( record::getProductReleaseId)
                .set(productReleaseName).equalTo(record::getProductReleaseName)
                .set(personId).equalTo(record::getPersonId)
                .set(personName).equalTo(record::getPersonName)
                .set(sex).equalTo(record::getSex)
                .set(age).equalTo(record::getAge)
                .set(mobile).equalTo(record::getMobile)
                .set(openId).equalTo(record::getOpenId)
                .set(siteId).equalTo(record::getSiteId)
                .set(siteName).equalTo(record::getSiteName)
                .set(recordStatus).equalTo(record::getRecordStatus)
                .set(productId).equalTo(record::getProductId)
                .set(productName).equalTo(record::getProductName)
                .set(productBatch).equalTo(record::getProductBatch)
                .set(manufacturer).equalTo(record::getManufacturer)
                .set(appointmentTime).equalTo(record::getAppointmentTime)
                .set(imageUrl).equalTo(record::getImageUrl)
                .set(msg).equalTo(record::getMsg)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(OrderRecord record) {
        return UpdateDSL.updateWithMapper(this::update, orderRecord)
                .set(recordType).equalToWhenPresent(record::getRecordType)
                .set(productReleaseId).equalToWhenPresent(record::getProductReleaseId)
                .set(productReleaseName).equalToWhenPresent(record::getProductReleaseName)
                .set(personId).equalToWhenPresent(record::getPersonId)
                .set(personName).equalToWhenPresent(record::getPersonName)
                .set(sex).equalToWhenPresent(record::getSex)
                .set(age).equalToWhenPresent(record::getAge)
                .set(mobile).equalToWhenPresent(record::getMobile)
                .set(openId).equalToWhenPresent(record::getOpenId)
                .set(siteId).equalToWhenPresent(record::getSiteId)
                .set(siteName).equalToWhenPresent(record::getSiteName)
                .set(recordStatus).equalToWhenPresent(record::getRecordStatus)
                .set(productId).equalToWhenPresent(record::getProductId)
                .set(productName).equalToWhenPresent(record::getProductName)
                .set(productBatch).equalToWhenPresent(record::getProductBatch)
                .set(manufacturer).equalToWhenPresent(record::getManufacturer)
                .set(appointmentTime).equalToWhenPresent(record::getAppointmentTime)
                .set(imageUrl).equalToWhenPresent(record::getImageUrl)
                .set(msg).equalToWhenPresent(record::getMsg)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(OrderRecord record) {
        return UpdateDSL.updateWithMapper(this::update, orderRecord)
                .set(recordType).equalTo(record::getRecordType)
                .set(productReleaseId).equalTo(record::getProductReleaseId)
                .set(productReleaseName).equalTo(record::getProductReleaseName)
                .set(personId).equalTo(record::getPersonId)
                .set(personName).equalTo(record::getPersonName)
                .set(sex).equalTo(record::getSex)
                .set(age).equalTo(record::getAge)
                .set(mobile).equalTo(record::getMobile)
                .set(openId).equalTo(record::getOpenId)
                .set(siteId).equalTo(record::getSiteId)
                .set(siteName).equalTo(record::getSiteName)
                .set(recordStatus).equalTo(record::getRecordStatus)
                .set(productId).equalTo(record::getProductId)
                .set(productName).equalTo(record::getProductName)
                .set(productBatch).equalTo(record::getProductBatch)
                .set(manufacturer).equalTo(record::getManufacturer)
                .set(appointmentTime).equalTo(record::getAppointmentTime)
                .set(imageUrl).equalTo(record::getImageUrl)
                .set(msg).equalTo(record::getMsg)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(OrderRecord record) {
        return UpdateDSL.updateWithMapper(this::update, orderRecord)
                .set(recordType).equalToWhenPresent(record::getRecordType)
                .set(productReleaseId).equalToWhenPresent(record::getProductReleaseId)
                .set(productReleaseName).equalToWhenPresent(record::getProductReleaseName)
                .set(personId).equalToWhenPresent(record::getPersonId)
                .set(personName).equalToWhenPresent(record::getPersonName)
                .set(sex).equalToWhenPresent(record::getSex)
                .set(age).equalToWhenPresent(record::getAge)
                .set(mobile).equalToWhenPresent(record::getMobile)
                .set(openId).equalToWhenPresent(record::getOpenId)
                .set(siteId).equalToWhenPresent(record::getSiteId)
                .set(siteName).equalToWhenPresent(record::getSiteName)
                .set(recordStatus).equalToWhenPresent(record::getRecordStatus)
                .set(productId).equalToWhenPresent(record::getProductId)
                .set(productName).equalToWhenPresent(record::getProductName)
                .set(productBatch).equalToWhenPresent(record::getProductBatch)
                .set(manufacturer).equalToWhenPresent(record::getManufacturer)
                .set(appointmentTime).equalToWhenPresent(record::getAppointmentTime)
                .set(imageUrl).equalToWhenPresent(record::getImageUrl)
                .set(msg).equalToWhenPresent(record::getMsg)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

}