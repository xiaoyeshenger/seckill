package com.jsxa.vapp.common.controllerAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsxa.vapp.common.annotation.Encrypt;
import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.utils.AESUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/*
 * @Author: zhangyong
 * description: 数据返回时的加密
 * @Date: 2021-08-31 10:26
 * @Param:
 * @Return:
 */
@ControllerAdvice
public class EncryptResponse implements ResponseBodyAdvice<ResultVo> {

    //接口加密的key
    @Value("${spring.encrypt.key:}")
    private String encryptKey;

    private ObjectMapper om = new ObjectMapper();

    //supports：这个方法用来判断什么样的接口需要加密，参数 returnType 表示返回类型，
    // 我们这里的判断逻辑就是方法是否含有 @Encrypt 注解，如果有，表示该接口需要加密处理，
    // 如果没有，表示该接口不需要加密处理。
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    //这个方法会在数据响应之前执行，也就是我们先对响应数据进行二次处理，处理完成后，
    // 才会转成 json 返回。我们这里的处理方式很简单，RespBean 中的 status 是状态码就不用加密了，
    // 另外两个字段重新加密后重新设置值即可。
    @Override
    public ResultVo beforeBodyWrite(ResultVo body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        byte[] keyBytes = encryptKey.getBytes();
        try {
            //可以选择加密/不加密 msg
            /*if (body.getMsg()!=null) {
                body.setMsg(AESUtil.encrypt(body.getMsg().getBytes(),keyBytes));
            }*/
            if (body.getData() != null) {
                body.setData(AESUtil.encrypt(om.writeValueAsBytes(body.getData()), keyBytes));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }
}
