package com.jsxa.vapp.common.annotation;


import java.lang.annotation.*;


/**
 * @author zhangyong
 * @description //自定义seata 异常感知注解(需要使用seata全局异常的 分支事务 都需要打上该注解，具体见com.jsxa.vapp.common.aop.SeataGlobalTransactionalAspect)
 * @date 2023/3/23 11:25
 * @param
 * @return
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SeataExp {}
