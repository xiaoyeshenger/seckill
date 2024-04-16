package com.zy.seckill.gateway.exception;

import com.zy.seckill.gateway.enums.ResponseStatus;
import com.zy.seckill.gateway.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    /**
     * 参数异常
     * */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVo illegalArgumentException(IllegalArgumentException ex) {
        return ResultVo.fail(ResponseStatus.FAIL.setCode(400).setMsg(ex.getMessage()));
    }

    /**
     * 处理系统异常
     * */
    @ExceptionHandler(Exception.class)
    public ResultVo handlerException(Exception e){
        log.error(e.getMessage(),e);
        return ResultVo.error();
    }
}
