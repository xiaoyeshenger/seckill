package com.jsxa.vapp.inventory.webSocket;

import com.jsxa.vapp.common.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;


@Slf4j
@Configuration
public class WebSocketConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
        return tomcatServletWebServerFactory -> tomcatServletWebServerFactory.addContextCustomizers((TomcatContextCustomizer) context -> {
            context.setCookieProcessor(new LegacyCookieProcessor());
        });
    }

    /**
     * @Author zhangyong
     * @Description //注册webSocket
     * @Date 下午 5:14 2019/7/24 0024
     * @Param
     * @return
     */
    @Bean
    public WebSocketConfigurer webSocketConfigurer(BeanFactory beanFactory) {
        return registry -> {
            //(1)竞价大厅实时数据(添加拦截器，取出webSocket url中的参数)
            registry.addHandler(SpringUtil.getBean(WebSocketService.class), "/ws/{type}/{key}").setAllowedOrigins("*")
                    .addInterceptors(new WebSocketInterceptor());
            registry.addHandler(SpringUtil.getBean(WebSocketService.class), "/ws/{type}/{key}").withSockJS();
        };
    }
}
