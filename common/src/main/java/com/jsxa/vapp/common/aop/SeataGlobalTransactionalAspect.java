package com.jsxa.vapp.common.aop;

import com.jsxa.vapp.common.annotation.SeataExp;
import com.jsxa.vapp.common.utils.ObjUtil;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


//service层操作日志记录处理

/**
 * seata全局事务aop
 * 一般的微服务都会使用@RestControllerAdvice全局异常捕获对接口出现的异常进行捕获，包装成统一的数据结构响应给前端。
 * 如果A服务通过远程调用到B服务，A服务作为全局事务的入口，当B服务内部出现异常，被B服务的全局异常捕获到返回，A服务得到的响应结果并不是一个Exception，
 * 可能是一个经过B服务包装的JSON对象{"code": 500,"msg":"method error"}。此时A服务会认为B服务的异常已经被处理，异常没有被A服务的全局事务感知到，
 * 所以导致全局事务未能回滚，解决办法如下，有三种(此处使用方式二)：
 * 方式一：RPC接口不配置全局异常
 * 方式二：利用AOP切面解决
 * 方式三：程序代码各自判断RPC响应码是否正常，再抛出异常让调用方事务感知该异常
 */
@Slf4j
@Aspect
@Component
public class SeataGlobalTransactionalAspect {


    //@Before("execution(* com.jsxa.vapp.inventory.service.impl.VaccineReleaseServiceImpl.reduceDock(..))")
    @Before("@annotation(seataExp)")
    public void before(JoinPoint joinPoint, SeataExp seataExp) throws TransactionException {
        //MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        //Method method = signature.getMethod();
        //log.info("拦截到需要分布式事务的方法," + method.getName());
        //此处可用redis或者定时任务来获取一个key判断是否需要关闭分布式事务
    }

    @AfterThrowing(throwing = "e", pointcut = "@annotation(seataExp)")
    public void doRecoveryActions(Throwable e, SeataExp seataExp) throws TransactionException {
        log.info("方法执行异常-执行事务回滚:{}", e.getMessage());
        if (!ObjUtil.isEmpty(RootContext.getXID())){
            GlobalTransactionContext.reload(RootContext.getXID()).rollback();
        }
    }

    @AfterReturning(value = "@annotation(seataExp)", returning = "result")
    public void afterReturning(JoinPoint point, SeataExp seataExp, Object result) throws TransactionException {
        log.info("方法执行结束-执行事务提交:{}", result);
        if (!ObjUtil.isEmpty(RootContext.getXID())) {
            GlobalTransactionContext.reload(RootContext.getXID()).commit();
        }

    }
}
