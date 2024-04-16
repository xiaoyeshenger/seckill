package com.zy.seckill.common.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/*
 * @Author: zhangyong
 * description: shiro 配置,设置过滤器，白名单等
 * @Date: 2019-12-11 11:51
 * @Param:
 * @Return:
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        //1.初始化ShiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //2.设置 securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //3.设置自定义的 JwtFilter
        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //4.配置放行名单，其余所有请求都要经过JwtFilter过滤器处理( filterChainDefinitionMap.put("/**", "jwt");需要放到最后，因为这是顺序验证的)
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //(1).外部调用(此处和gateway全局拦截放行名单一致)
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs/**", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
        filterChainDefinitionMap.put("/sys/auth/**", "anon");
        filterChainDefinitionMap.put("/sys/user/register", "anon");
        filterChainDefinitionMap.put("/sys/user/updateRegister", "anon");
        filterChainDefinitionMap.put("/sys/region/getAllChildListByParentCode/**", "anon");
        filterChainDefinitionMap.put("/sys/dataDict/getDataDict", "anon");
        filterChainDefinitionMap.put("/sys/dataDict/getDataDictTreeById/**", "anon");
        filterChainDefinitionMap.put("/sys/park/getCompetentParkList", "anon");
        filterChainDefinitionMap.put("/dw/**", "anon");
        filterChainDefinitionMap.put("/ext/**", "anon");
        //放行首页WebSocket
        filterChainDefinitionMap.put("/ws/**", "anon");


        //放行仓库和订单接口...测试使用
        filterChainDefinitionMap.put("/inventory/**", "anon");
        filterChainDefinitionMap.put("/order/**", "anon");


        //(2).内部调用(微服务之间的调用)
        //--1.iotPer开头的代表内部调用(全部放行)
        filterChainDefinitionMap.put("/vapp/**", "anon");

        //(3).其余的所有请求都要经过JwtFilter过滤器处理(即必须有token才能访问)
        filterChainDefinitionMap.put("/**", "jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
 }

    //注册SecurityManager
    @Bean
    public SecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //配置 SecurityManager，并注入 shiroRealm
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    //注册ShiroRealm
     @Bean
     public ShiroRealm shiroRealm() {
        return new ShiroRealm();
     }

    //开启shiro注解
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
     return authorizationAttributeSourceAdvisor;
    }
}

