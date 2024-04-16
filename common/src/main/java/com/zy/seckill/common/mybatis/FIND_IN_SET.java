package com.zy.seckill.common.mybatis;

import org.mybatis.dynamic.sql.AbstractSingleValueCondition;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class  FIND_IN_SET<T> extends AbstractSingleValueCondition<T> {


    protected FIND_IN_SET(Supplier<T> valueSupplier) {
        super(valueSupplier);
    }

    protected FIND_IN_SET(Supplier<T> valueSupplier, Predicate<T> predicate) {
        super(valueSupplier, predicate);
    }


    public static <T> FIND_IN_SET<T> of(Supplier<T> valueSupplier) {
        return new FIND_IN_SET(valueSupplier);
    }

    @Override
    public String renderCondition(String columnName, String placeholder) {
        return "find_in_set(" +  securitySql(placeholder) + "," + columnName + ")";
    }

    /*
     * @Author: xxx
     * description:
     * @Date: xxxx-09-30 11:26
     * @Param:
     * @Return:
     */
    private static String securitySql(String sql) {
        if(null == sql) {
            return null;
        }
        return sql.replace(";", "");
    }
}
