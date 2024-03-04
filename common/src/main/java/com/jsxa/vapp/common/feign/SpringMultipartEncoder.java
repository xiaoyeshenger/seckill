package com.jsxa.vapp.common.feign;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.MultipartFormContentProcessor;
import feign.form.spring.SpringFormEncoder;
import feign.form.spring.SpringManyMultipartFilesWriter;
import feign.form.spring.SpringSingleMultipartFileWriter;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

import static feign.form.ContentType.MULTIPART;

/*
 * @Author: zhangyong
 * description: 自定义--》的多个文件上传编码器(支持feign上传form/data 及文件列表)
 * @Date: 2021-07-23 15:33
 * @Param:
 * @Return:
 */
public class SpringMultipartEncoder extends SpringFormEncoder {
    public SpringMultipartEncoder(Encoder delegate) {
        super(delegate);
        MultipartFormContentProcessor processor = (MultipartFormContentProcessor) getContentProcessor(MULTIPART);
        processor.addWriter(new SpringSingleMultipartFileWriter());
        processor.addWriter(new SpringManyMultipartFilesWriter());
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if (bodyType != null && bodyType.equals(MultipartFile[].class)) {
            MultipartFile[] file = (MultipartFile[]) object;
            if(file != null) {
                Map data = Collections.singletonMap(file.length == 0 ? "" : file[0].getName(), object);
                super.encode(data, MAP_STRING_WILDCARD, template);
                return;
            }
        }
        super.encode(object, bodyType, template);
    }
}