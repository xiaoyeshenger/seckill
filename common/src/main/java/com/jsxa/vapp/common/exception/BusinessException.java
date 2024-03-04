package com.jsxa.vapp.common.exception;

import com.jsxa.vapp.common.enums.ResponseStatus;
import lombok.Data;


/*
 * @Author: zhangyong
 * description: 自定义业务异常，用于处理用户请求时，业务错误时抛出
 * @Date: 2021-09-07 14:52
 * @Param:
 * @Return:
 */
@Data
public class BusinessException extends RuntimeException {

    private ResponseStatus resStatus;

    public BusinessException(ResponseStatus resStatus) {
        //不调用父类Throwable的fillInStackTrace()方法生成栈追踪信息，提高应用性能
        //构造器之前调用必须在第一行
        super(resStatus.getMsg(), null, false, false);
        this.resStatus = resStatus;
    }

}
