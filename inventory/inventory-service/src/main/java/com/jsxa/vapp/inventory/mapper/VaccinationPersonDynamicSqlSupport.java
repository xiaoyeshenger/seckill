package com.jsxa.vapp.inventory.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class VaccinationPersonDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final VaccinationPerson vaccinationPerson = new VaccinationPerson();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = vaccinationPerson.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = vaccinationPerson.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> sex = vaccinationPerson.sex;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> age = vaccinationPerson.age;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> mobile = vaccinationPerson.mobile;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> idNumber = vaccinationPerson.idNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> openId = vaccinationPerson.openId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> address = vaccinationPerson.address;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> workUnit = vaccinationPerson.workUnit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> personType = vaccinationPerson.personType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> doseStatus = vaccinationPerson.doseStatus;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> firstdoseId = vaccinationPerson.firstdoseId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> firstdoseName = vaccinationPerson.firstdoseName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> firstdoseUnit = vaccinationPerson.firstdoseUnit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> firstdoseTime = vaccinationPerson.firstdoseTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> latestdoseId = vaccinationPerson.latestdoseId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> latestdoseName = vaccinationPerson.latestdoseName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> latestdoseUnit = vaccinationPerson.latestdoseUnit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> latestdoseTime = vaccinationPerson.latestdoseTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = vaccinationPerson.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> updateTime = vaccinationPerson.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class VaccinationPerson extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Byte> sex = column("sex", JDBCType.TINYINT);

        public final SqlColumn<Integer> age = column("age", JDBCType.INTEGER);

        public final SqlColumn<String> mobile = column("mobile", JDBCType.VARCHAR);

        public final SqlColumn<String> idNumber = column("id_number", JDBCType.VARCHAR);

        public final SqlColumn<String> openId = column("open_id", JDBCType.VARCHAR);

        public final SqlColumn<String> address = column("address", JDBCType.VARCHAR);

        public final SqlColumn<String> workUnit = column("work_unit", JDBCType.VARCHAR);

        public final SqlColumn<Long> personType = column("person_type", JDBCType.BIGINT);

        public final SqlColumn<Long> doseStatus = column("dose_status", JDBCType.BIGINT);

        public final SqlColumn<String> firstdoseId = column("firstDose_id", JDBCType.VARCHAR);

        public final SqlColumn<String> firstdoseName = column("firstDose_name", JDBCType.VARCHAR);

        public final SqlColumn<String> firstdoseUnit = column("firstDose_unit", JDBCType.VARCHAR);

        public final SqlColumn<Long> firstdoseTime = column("firstDose_time", JDBCType.BIGINT);

        public final SqlColumn<String> latestdoseId = column("latestDose_id", JDBCType.VARCHAR);

        public final SqlColumn<String> latestdoseName = column("latestDose_name", JDBCType.VARCHAR);

        public final SqlColumn<String> latestdoseUnit = column("latestDose_unit", JDBCType.VARCHAR);

        public final SqlColumn<Long> latestdoseTime = column("latestDose_time", JDBCType.BIGINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public VaccinationPerson() {
            super("vaccination_person");
        }
    }
}