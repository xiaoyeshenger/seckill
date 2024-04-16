package com.zy.seckill.gateway.vo;


import com.zy.seckill.gateway.enums.ResponseStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;


@Data
@Builder
@Accessors(chain=true)
@AllArgsConstructor
public class ResultVo<T>{

    @ApiModelProperty(value = "响应状态码，200表示成功")
    private int code = ResponseStatus.OK.getCode();

    /**
     * 响应消息
     * */
    @ApiModelProperty(value = "响应状态信息")
    private String msg = ResponseStatus.OK.getMsg();
    /**
     * 响应中的数据
     * */
    @ApiModelProperty(value = "响应数据")
    private T data;

    private ResultVo() {

    }

    private ResultVo(ResponseStatus ResponseStatus) {
        this.code = ResponseStatus.getCode();;
        this.msg = ResponseStatus.getMsg();
    }

    private ResultVo(T data) {
        this.data = data;
    }



    /**
     * 业务处理成功,无数据返回
     * */
    public static ResultVo ok() {
        return new ResultVo();
    }

    /**
     * 业务处理成功，有数据返回
     * */
    public static <T> ResultVo ok(T data) {
        return new ResultVo(data);
    }

    /**
     * 业务处理失败
     * */
    public static ResultVo fail(ResponseStatus ResponseStatus) {
        return new ResultVo(ResponseStatus);
    }


    /**
     * 系统错误
     * */
    public static ResultVo error() {
        return new ResultVo(ResponseStatus.ERROR);
    }
}

