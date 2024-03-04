package com.jsxa.vapp.common.listener;

import org.springframework.stereotype.Component;

import javax.xml.bind.Marshaller;
import java.lang.reflect.Field;

/*
 * @Author zhangyong
 * @Description // 该监听器主要用来解决jaxb无法渲染字段为null的属性
 * 在将Java类转换为xml片段时,默认jaxb会过滤属性值为null的属性,通过该监听器的{@link #beforeMarshal(Object)}方法，在渲染前
 *     通过将属性赋值为空字符串来达到在生成的xml片段中包含该属性的功能
 * @Date 上午 9:48 2019/5/27 0027
 * @Param
 * @return
 **/
@Component
public class MarshallerListener extends Marshaller.Listener{

    public static final String BLANK_CHAR = "";

    @Override
    public void beforeMarshal(Object source) {
        super.beforeMarshal(source);
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            //获取字段上注解<pre name="code" class="java">
            try {
                if (f.getType() == String.class && f.get(source) == null) {
                    f.set(source, BLANK_CHAR);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}