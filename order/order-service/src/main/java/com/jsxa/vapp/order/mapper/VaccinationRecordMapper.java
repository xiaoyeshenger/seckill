package com.jsxa.vapp.order.mapper;

import static com.jsxa.vapp.order.mapper.VaccinationRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jsxa.vapp.order.bo.po.VaccinationRecord;
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
public interface VaccinationRecordMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<VaccinationRecord> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("VaccinationRecordResult")
    VaccinationRecord selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="VaccinationRecordResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="record_type", property="recordType", jdbcType=JdbcType.BIGINT),
        @Result(column="vaild", property="vaild", jdbcType=JdbcType.TINYINT),
        @Result(column="vaccineRelease_id", property="vaccineReleaseId", jdbcType=JdbcType.BIGINT),
        @Result(column="vaccineRelease_name", property="vaccineReleaseName", jdbcType=JdbcType.VARCHAR),
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
        @Result(column="vaccine_id", property="vaccineId", jdbcType=JdbcType.BIGINT),
        @Result(column="vaccine_name", property="vaccineName", jdbcType=JdbcType.VARCHAR),
        @Result(column="vaccine_batch", property="vaccineBatch", jdbcType=JdbcType.VARCHAR),
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
    List<VaccinationRecord> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(vaccinationRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, vaccinationRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, vaccinationRecord)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(VaccinationRecord record) {
        return insert(SqlBuilder.insert(record)
                .into(vaccinationRecord)
                .map(recordType).toProperty("recordType")
                .map(vaild).toProperty("vaild")
                .map(vaccineReleaseId).toProperty("vaccineReleaseId")
                .map(vaccineReleaseName).toProperty("vaccineReleaseName")
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
                .map(vaccineId).toProperty("vaccineId")
                .map(vaccineName).toProperty("vaccineName")
                .map(vaccineBatch).toProperty("vaccineBatch")
                .map(manufacturer).toProperty("manufacturer")
                .map(dose).toProperty("dose")
                .map(doseUnit).toProperty("doseUnit")
                .map(appointmentTime).toProperty("appointmentTime")
                .map(timePeriod).toProperty("timePeriod")
                .map(timeperiodName).toProperty("timePeriodName")
                .map(doseTime).toProperty("doseTime")
                .map(imageUrl).toProperty("imageUrl")
                .map(city).toProperty("city")
                .map(county).toProperty("county")
                .map(town).toProperty("town")
                .map(msg).toProperty("msg")
                .map(createTime).toProperty("createTime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(VaccinationRecord record) {
        return insert(SqlBuilder.insert(record)
                .into(vaccinationRecord)
                .map(recordType).toPropertyWhenPresent("recordType", record::getRecordType)
                .map(vaild).toPropertyWhenPresent("vaild", record::getVaild)
                .map(vaccineReleaseId).toPropertyWhenPresent("vaccineReleaseId", record::getVaccineReleaseId)
                .map(vaccineReleaseName).toPropertyWhenPresent("vaccineReleaseName", record::getVaccineReleaseName)
                .map(personId).toPropertyWhenPresent("personId", record::getPersonId)
                .map(personName).toPropertyWhenPresent("personName", record::getPersonName)
                .map(sex).toPropertyWhenPresent("sex", record::getSex)
                .map(age).toPropertyWhenPresent("age", record::getAge)
                .map(mobile).toPropertyWhenPresent("mobile", record::getMobile)
                .map(idNumber).toPropertyWhenPresent("idNumber", record::getIdNumber)
                .map(openId).toPropertyWhenPresent("openId", record::getOpenId)
                .map(siteId).toPropertyWhenPresent("siteId", record::getSiteId)
                .map(siteName).toPropertyWhenPresent("siteName", record::getSiteName)
                .map(recordStatus).toPropertyWhenPresent("recordStatus", record::getRecordStatus)
                .map(vaccineId).toPropertyWhenPresent("vaccineId", record::getVaccineId)
                .map(vaccineName).toPropertyWhenPresent("vaccineName", record::getVaccineName)
                .map(vaccineBatch).toPropertyWhenPresent("vaccineBatch", record::getVaccineBatch)
                .map(manufacturer).toPropertyWhenPresent("manufacturer", record::getManufacturer)
                .map(dose).toPropertyWhenPresent("dose", record::getDose)
                .map(doseUnit).toPropertyWhenPresent("doseUnit", record::getDoseUnit)
                .map(appointmentTime).toPropertyWhenPresent("appointmentTime", record::getAppointmentTime)
                .map(timePeriod).toPropertyWhenPresent("timePeriod", record::getTimePeriod)
                .map(timeperiodName).toPropertyWhenPresent("timePeriodName", record::getTimePeriodName)
                .map(doseTime).toPropertyWhenPresent("doseTime", record::getDoseTime)
                .map(imageUrl).toPropertyWhenPresent("imageUrl", record::getImageUrl)
                .map(city).toPropertyWhenPresent("city", record::getCity)
                .map(county).toPropertyWhenPresent("county", record::getCounty)
                .map(town).toPropertyWhenPresent("town", record::getTown)
                .map(msg).toPropertyWhenPresent("msg", record::getMsg)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<VaccinationRecord>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, recordType, vaild,vaccineReleaseId,vaccineReleaseName, personId, personName, sex, age, mobile, idNumber, openId, siteId, siteName, recordStatus, vaccineId, vaccineName, vaccineBatch, manufacturer, dose, doseUnit, appointmentTime, timePeriod, timeperiodName, doseTime, imageUrl, city, county, town, msg, createTime)
                .from(vaccinationRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<VaccinationRecord>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, recordType, vaild,vaccineReleaseId,vaccineReleaseName, personId, personName, sex, age, mobile, idNumber, openId, siteId, siteName, recordStatus, vaccineId, vaccineName, vaccineBatch, manufacturer, dose, doseUnit, appointmentTime, timePeriod, timeperiodName, doseTime, imageUrl, city, county, town, msg, createTime)
                .from(vaccinationRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default VaccinationRecord selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, recordType, vaild,vaccineReleaseId,vaccineReleaseName, personId, personName, sex, age, mobile, idNumber, openId, siteId, siteName, recordStatus, vaccineId, vaccineName, vaccineBatch, manufacturer, dose, doseUnit, appointmentTime, timePeriod, timeperiodName, doseTime, imageUrl, city, county, town, msg, createTime)
                .from(vaccinationRecord)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(VaccinationRecord record) {
        return UpdateDSL.updateWithMapper(this::update, vaccinationRecord)
                .set(recordType).equalTo(record::getRecordType)
                .set(vaild).equalTo(record::getVaild)
                .set(vaccineReleaseId).equalTo( record::getVaccineReleaseId)
                .set(vaccineReleaseName).equalTo(record::getVaccineReleaseName)
                .set(personId).equalTo(record::getPersonId)
                .set(personName).equalTo(record::getPersonName)
                .set(sex).equalTo(record::getSex)
                .set(age).equalTo(record::getAge)
                .set(mobile).equalTo(record::getMobile)
                .set(idNumber).equalTo(record::getIdNumber)
                .set(openId).equalTo(record::getOpenId)
                .set(siteId).equalTo(record::getSiteId)
                .set(siteName).equalTo(record::getSiteName)
                .set(recordStatus).equalTo(record::getRecordStatus)
                .set(vaccineId).equalTo(record::getVaccineId)
                .set(vaccineName).equalTo(record::getVaccineName)
                .set(vaccineBatch).equalTo(record::getVaccineBatch)
                .set(manufacturer).equalTo(record::getManufacturer)
                .set(dose).equalTo(record::getDose)
                .set(doseUnit).equalTo(record::getDoseUnit)
                .set(appointmentTime).equalTo(record::getAppointmentTime)
                .set(timePeriod).equalTo(record::getTimePeriod)
                .set(timeperiodName).equalTo(record::getTimePeriodName)
                .set(doseTime).equalTo(record::getDoseTime)
                .set(imageUrl).equalTo(record::getImageUrl)
                .set(city).equalTo(record::getCity)
                .set(county).equalTo(record::getCounty)
                .set(town).equalTo(record::getTown)
                .set(msg).equalTo(record::getMsg)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(VaccinationRecord record) {
        return UpdateDSL.updateWithMapper(this::update, vaccinationRecord)
                .set(recordType).equalToWhenPresent(record::getRecordType)
                .set(vaild).equalToWhenPresent(record::getVaild)
                .set(vaccineReleaseId).equalToWhenPresent(record::getVaccineReleaseId)
                .set(vaccineReleaseName).equalToWhenPresent(record::getVaccineReleaseName)
                .set(personId).equalToWhenPresent(record::getPersonId)
                .set(personName).equalToWhenPresent(record::getPersonName)
                .set(sex).equalToWhenPresent(record::getSex)
                .set(age).equalToWhenPresent(record::getAge)
                .set(mobile).equalToWhenPresent(record::getMobile)
                .set(idNumber).equalToWhenPresent(record::getIdNumber)
                .set(openId).equalToWhenPresent(record::getOpenId)
                .set(siteId).equalToWhenPresent(record::getSiteId)
                .set(siteName).equalToWhenPresent(record::getSiteName)
                .set(recordStatus).equalToWhenPresent(record::getRecordStatus)
                .set(vaccineId).equalToWhenPresent(record::getVaccineId)
                .set(vaccineName).equalToWhenPresent(record::getVaccineName)
                .set(vaccineBatch).equalToWhenPresent(record::getVaccineBatch)
                .set(manufacturer).equalToWhenPresent(record::getManufacturer)
                .set(dose).equalToWhenPresent(record::getDose)
                .set(doseUnit).equalToWhenPresent(record::getDoseUnit)
                .set(appointmentTime).equalToWhenPresent(record::getAppointmentTime)
                .set(timePeriod).equalToWhenPresent(record::getTimePeriod)
                .set(timeperiodName).equalToWhenPresent(record::getTimePeriodName)
                .set(doseTime).equalToWhenPresent(record::getDoseTime)
                .set(imageUrl).equalToWhenPresent(record::getImageUrl)
                .set(city).equalToWhenPresent(record::getCity)
                .set(county).equalToWhenPresent(record::getCounty)
                .set(town).equalToWhenPresent(record::getTown)
                .set(msg).equalToWhenPresent(record::getMsg)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(VaccinationRecord record) {
        return UpdateDSL.updateWithMapper(this::update, vaccinationRecord)
                .set(recordType).equalTo(record::getRecordType)
                .set(vaild).equalTo(record::getVaild)
                .set(vaccineReleaseId).equalTo(record::getVaccineReleaseId)
                .set(vaccineReleaseName).equalTo(record::getVaccineReleaseName)
                .set(personId).equalTo(record::getPersonId)
                .set(personName).equalTo(record::getPersonName)
                .set(sex).equalTo(record::getSex)
                .set(age).equalTo(record::getAge)
                .set(mobile).equalTo(record::getMobile)
                .set(idNumber).equalTo(record::getIdNumber)
                .set(openId).equalTo(record::getOpenId)
                .set(siteId).equalTo(record::getSiteId)
                .set(siteName).equalTo(record::getSiteName)
                .set(recordStatus).equalTo(record::getRecordStatus)
                .set(vaccineId).equalTo(record::getVaccineId)
                .set(vaccineName).equalTo(record::getVaccineName)
                .set(vaccineBatch).equalTo(record::getVaccineBatch)
                .set(manufacturer).equalTo(record::getManufacturer)
                .set(dose).equalTo(record::getDose)
                .set(doseUnit).equalTo(record::getDoseUnit)
                .set(appointmentTime).equalTo(record::getAppointmentTime)
                .set(timePeriod).equalTo(record::getTimePeriod)
                .set(timeperiodName).equalTo(record::getTimePeriodName)
                .set(doseTime).equalTo(record::getDoseTime)
                .set(imageUrl).equalTo(record::getImageUrl)
                .set(city).equalTo(record::getCity)
                .set(county).equalTo(record::getCounty)
                .set(town).equalTo(record::getTown)
                .set(msg).equalTo(record::getMsg)
                .set(createTime).equalTo(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(VaccinationRecord record) {
        return UpdateDSL.updateWithMapper(this::update, vaccinationRecord)
                .set(recordType).equalToWhenPresent(record::getRecordType)
                .set(vaild).equalToWhenPresent(record::getVaild)
                .set(vaccineReleaseId).equalToWhenPresent(record::getVaccineReleaseId)
                .set(vaccineReleaseName).equalToWhenPresent(record::getVaccineReleaseName)
                .set(personId).equalToWhenPresent(record::getPersonId)
                .set(personName).equalToWhenPresent(record::getPersonName)
                .set(sex).equalToWhenPresent(record::getSex)
                .set(age).equalToWhenPresent(record::getAge)
                .set(mobile).equalToWhenPresent(record::getMobile)
                .set(idNumber).equalToWhenPresent(record::getIdNumber)
                .set(openId).equalToWhenPresent(record::getOpenId)
                .set(siteId).equalToWhenPresent(record::getSiteId)
                .set(siteName).equalToWhenPresent(record::getSiteName)
                .set(recordStatus).equalToWhenPresent(record::getRecordStatus)
                .set(vaccineId).equalToWhenPresent(record::getVaccineId)
                .set(vaccineName).equalToWhenPresent(record::getVaccineName)
                .set(vaccineBatch).equalToWhenPresent(record::getVaccineBatch)
                .set(manufacturer).equalToWhenPresent(record::getManufacturer)
                .set(dose).equalToWhenPresent(record::getDose)
                .set(doseUnit).equalToWhenPresent(record::getDoseUnit)
                .set(appointmentTime).equalToWhenPresent(record::getAppointmentTime)
                .set(timePeriod).equalToWhenPresent(record::getTimePeriod)
                .set(timeperiodName).equalToWhenPresent(record::getTimePeriodName)
                .set(doseTime).equalToWhenPresent(record::getDoseTime)
                .set(imageUrl).equalToWhenPresent(record::getImageUrl)
                .set(city).equalToWhenPresent(record::getCity)
                .set(county).equalToWhenPresent(record::getCounty)
                .set(town).equalToWhenPresent(record::getTown)
                .set(msg).equalToWhenPresent(record::getMsg)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

}