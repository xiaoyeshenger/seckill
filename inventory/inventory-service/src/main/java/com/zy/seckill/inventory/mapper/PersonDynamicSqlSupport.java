package com.zy.seckill.inventory.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PersonDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Person person = new Person();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = person.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = person.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> sex = person.sex;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> age = person.age;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> mobile = person.mobile;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> idNumber = person.idNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> openId = person.openId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> address = person.address;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> workUnit = person.workUnit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> personType = person.personType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> doseStatus = person.doseStatus;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> firstdoseId = person.firstdoseId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> firstdoseName = person.firstdoseName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> firstdoseUnit = person.firstdoseUnit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> firstdoseTime = person.firstdoseTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> latestdoseId = person.latestdoseId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> latestdoseName = person.latestdoseName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> latestdoseUnit = person.latestdoseUnit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> latestdoseTime = person.latestdoseTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createTime = person.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> updateTime = person.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Person extends SqlTable {
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

        public Person() {
            super("person");
        }
    }
}