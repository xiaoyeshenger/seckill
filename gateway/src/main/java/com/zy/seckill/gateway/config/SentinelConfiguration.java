package com.zy.seckill.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayParamFlowItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.util.*;


/**
 * @Author: zhangyong
 * description: 网关限流配置
 * @Date: xxxx-08-31 17:29
 * @Param:
 * @Return:
 */
@Configuration
public class SentinelConfiguration {

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public SentinelConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider, ServerCodecConfigurer serverCodecConfigurer)
    {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }







    /**
     *  不使用: 服务降级操作fallback 在nacos上gateway.yml文件中 spring.cloud.sentinel.scg.fallback.response-body已经进行异常配置
     * 配置限流的异常处理器：SentinelGatewayBlockExceptionHandler(Sentinel自带的异常处理器)
     */
    /*@Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        // Register the block exception handler for Spring Cloud Gateway.
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }*/


    /**
     * 由于sentinel的工作原理其实借助于全局的filter进行请求拦截并计算出是否进行限流、熔断等操作的，增加SentinelGateWayFilter配置
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }


    /**
     * sentinel不仅支持通过硬代码方式进行资源的申明，还能通过注解方式进行声明，
     * 为了让注解生效，需要配置切面类SentinelResourceAspect
     * @return
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    @PostConstruct
    public void doInit() {
       // initSystemRule();
        initCustomizedApis();
        initGatewayRules();
    }

    /**
     * 自定义要限流的api路径
     * 此处定义了2个限流路径:
     * (1).1个全局路径/api/** 名称为 customized_api
     * (2).1个指定接口的路径 /api/order/vaccinationRecord/addVaccinationRecord (注意:也可在路径后加** 表示全部匹配，比如get请求的任何参数)名称为 add_vaccination_record_api
     */
    private void initCustomizedApis() {
        Set<ApiDefinition> definitions = new HashSet<>();
        ApiDefinition api1 = new ApiDefinition("customized_api")
                .setPredicateItems(new HashSet<ApiPredicateItem>()
                {{
                    //匹配路径前缀，即以/api/开头的
                    add(new ApiPathPredicateItem()
                            .setPattern("/api/**")
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX)
                    );
                }});

        ApiDefinition api2 = new ApiDefinition("add_vaccination_record_api")
                .setPredicateItems(new HashSet<ApiPredicateItem>()
                {{
                    //(1).完全匹配路径的
                    add(new ApiPathPredicateItem().setPattern("/api/order/vaccinationRecord/addVaccinationRecord"));
                    //(2).匹配路径前缀，即以/api/inventory/开头的
                    add(new ApiPathPredicateItem()
                            .setPattern("/api/inventory/**")
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX)
                    );
                }});
        definitions.add(api1);
        definitions.add(api2);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }


    /**
     * 自定义网关限流规则（反爬虫机制）
     * 1.对所有api接口通过IP进行限流,每个IP，1秒钟内请求数量大于10，即视为爬虫
     * 2.对疫苗预约接口访问进行限流，每个IP，1秒钟请求数量大于2，则视为爬虫
     */
    private void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("customized_api")
                .setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME)
                .setCount(10)
                .setIntervalSec(1)
                .setParamItem(new GatewayParamFlowItem().setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_CLIENT_IP))
        );
        rules.add(new GatewayFlowRule("add_vaccination_record_api")
                .setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME)
                .setCount(2)
                .setIntervalSec(1)
                .setParamItem(new GatewayParamFlowItem().setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_CLIENT_IP))
        );
        GatewayRuleManager.loadRules(rules);
    }

    /**
     * 系统自适应限流规则
     * */
    private void initSystemRule() {
        List<SystemRule> rules = new ArrayList<>();
        SystemRule rule = new SystemRule();
        // max load is 3
        rule.setHighestSystemLoad(3.0);
        // max cpu usage is 60%
        rule.setHighestCpuUsage(0.6);
        // max avg rt of all request is 10 ms
        rule.setAvgRt(10);
        // max total qps is 20
        rule.setQps(20);
        // max parallel working thread is 10
        rule.setMaxThread(10);

        rules.add(rule);
        SystemRuleManager.loadRules(Collections.singletonList(rule));
    }
}
