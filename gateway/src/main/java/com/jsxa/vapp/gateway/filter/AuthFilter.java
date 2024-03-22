package com.jsxa.vapp.gateway.filter;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jsxa.vapp.gateway.redis.RedisKey;
import com.jsxa.vapp.gateway.enums.ResponseStatus;
import com.jsxa.vapp.gateway.redis.RedisService;
import com.jsxa.vapp.gateway.utils.JwtTokenUtil;
import com.jsxa.vapp.gateway.utils.ObjUtil;
import com.jsxa.vapp.gateway.vo.ResultVo;
import com.jsxa.vapp.gateway.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Pattern;


/*
 * @Author: zhangyong
 * description: 身份认证过滤器
 * @Date: 2021-08-31 17:21
 * @Param:
 * @Return:
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    //白名单
    @Value("${sys.filter.whitelists:" +
            "/api/app/v2/api-docs," +
            "/api/sys/v2/api-docs," +
            "/api/bid/v2/api-docs," +
            "/api/event/v2/api-docs"
            )
    private String whitelists;

    @Value("${sys.user.onlyOneLogin}")
    private Integer onlyOneLogin;

    private final RedisService redisService;

    private final JwtTokenUtil jwtTokenUtil;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //request和response
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        ServerHttpResponse serverHttpResponse = exchange.getResponse();

        //当前请求路径
        String path = serverHttpRequest.getPath().toString();
        log.info("当前请求路径:{}", path);

/*        //测试请求参数
        if(path.equals("/api/car/addCar")){
            Mono<Void> post_body = DataBufferUtils.join(exchange.getRequest().getBody())
                    .flatMap(dataBuffer -> {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        try {
                            String bodyString = new String(bytes, "utf-8");
                            log.info(bodyString);//打印请求参数
                            exchange.getAttributes().put("POST_BODY", bodyString);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        DataBufferUtils.release(dataBuffer);
                        Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
                            DataBuffer buffer = exchange.getResponse().bufferFactory()
                                    .wrap(bytes);
                            return Mono.just(buffer);
                        });

                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(
                                exchange.getRequest()) {
                            @Override
                            public Flux<DataBuffer> getBody() {
                                return cachedFlux;
                            }
                        };
                        return chain.filter(exchange.mutate().request(mutatedRequest)
                                .build());
                    });
        }*/

        //从请求头中获取语言信息，如果没有，默认为""(空字符串)
        String reqLanguage = serverHttpRequest.getHeaders().getFirst("language");
        if (ObjUtil.isEmpty(reqLanguage)) {
            reqLanguage = "";
        }
        serverHttpRequest = serverHttpRequest.mutate().header("language", reqLanguage).build();


        //系统认证、东沃车辆道闸接口正则匹配--->放行
        boolean sys = Pattern.matches("/api/sys/auth[^\\s]*", path);
        boolean userRegister = Pattern.matches("/api/sys/user/register", path);
        boolean userUpdateRegister = Pattern.matches("/api/sys/user/updateRegister", path);
        boolean region = Pattern.matches("/api/sys/region/getAllChildListByParentCode[^\\s]*", path);
        boolean dataDict = Pattern.matches("/api/sys/dataDict/getDataDict", path);
        boolean reloadDataDict = Pattern.matches("/api/sys/dataDict/reloadDataDict", path);
        boolean dataDictTreeById = Pattern.matches("/api/sys/dataDict/getDataDictTreeById[^\\s]*", path);
        boolean getCompetentParkList = Pattern.matches("/api/sys/park/getCompetentParkList", path);
        boolean mattressRecordDataStatisticsByDeviceId = Pattern.matches("/api/accessAlarm/alarmInfo/mattressRecordDataStatisticsByDeviceId", path);
        boolean waterMeterRecordDataStatisticsByDeviceId = Pattern.matches("/api/accessAlarm/alarmInfo/waterMeterRecordDataStatisticsByDeviceId", path);
        boolean dw = Pattern.matches("/api/dw[^\\s]*", path);
        boolean ext = Pattern.matches("/api/ext[^\\s]*", path);
        boolean ws = Pattern.matches("/api/ws[^\\s]*", path);//首页ws
        boolean perception = Pattern.matches("/api/perception/[^\\s]*", path);//设备ws
        boolean underlyingWs = Pattern.matches("/api/underlying[^\\s]*", path);//底层设备数据ws
        boolean iot = Pattern.matches("/api/accessAlarm/iot/[^\\s]*", path);//物联感知设备
        boolean alarmImage = Pattern.matches("/api/accessAlarm/image/[^\\s]*", path);//物联感知视频设备图片
        boolean gridInfo = Pattern.matches("/api/device/device/getGridInfoListByCommunityCode/[^\\s]*", path);//物联感知网格
        boolean getDeviceByUUID = Pattern.matches("/api/device/device/getDeviceByUUID/[^\\s]*", path);//物联感知设备信息



        //白名单接口、开放接口放行
        Set<String> whiteList = this.getWhiteList();
        if (whiteList.contains(path)) {
            return chain.filter(exchange.mutate().request(serverHttpRequest).build());
        }
        if (path.contains("getOpenIdByJsCode")) {
            return chain.filter(exchange.mutate().request(serverHttpRequest).build());
        }

        //系统认证、东沃车辆道闸使用的相关接口放行
        if (sys
                || iot
                || alarmImage
                || dw
                || ext
                || ws
                || perception
                || underlyingWs
                || userRegister
                || userUpdateRegister
                || region
                || gridInfo
                || dataDict
                //|| reloadDataDict
                || dataDictTreeById
                || getCompetentParkList
                || mattressRecordDataStatisticsByDeviceId
                || waterMeterRecordDataStatisticsByDeviceId
                || getDeviceByUUID
            ){
            return chain.filter(exchange.mutate().request(serverHttpRequest).build());
        }

        //未在白名单的执行token校验
        String[] segments = path.split("/");
        if (!whiteList.contains(segments[1])) {
            //(1).token为空
            String token = serverHttpRequest.getHeaders().getFirst("Authorization");
            if (ObjUtil.isEmpty(token)) {
                //serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
                return getVoidMono(serverHttpResponse, ResponseStatus.TOKEN_MISSING);
            }

            UserVo userVo;
            try {
                userVo = jwtTokenUtil.getUserDetailsFromToken(token);
            } catch (IllegalArgumentException e) {
                return getVoidMono(serverHttpResponse, ResponseStatus.TOKEN_INVALID);
            }

            if (ObjUtil.isEmpty(userVo)) {
                return getVoidMono(serverHttpResponse, ResponseStatus.TOKEN_INVALID);
            }

            //(2).redis中没有该账户的token
            Long id = userVo.getId();
            String userId = String.valueOf(id);
            if (!redisService.hasKey(RedisKey.USER_TOKEN_KEY+userId)) {
                return getVoidMono(serverHttpResponse, ResponseStatus.TOKEN_TIMEOUT);
            }

            //(3).是否只允许同时只能有一个账户登录---本次请求的token和redis中的是否一致,不一致，说明redis中存的是最新登录的信息,而请求的token已失效(在其他地方登录了)
            if(onlyOneLogin == 1){
                String redisToken = (String) redisService.get(RedisKey.USER_TOKEN_KEY+userId);
                if (!token.equals(redisToken)) {
                    return getVoidMono(serverHttpResponse, ResponseStatus.TOKEN_TIMEOUT);
                }
            }



            // 认证通过,追加请求头用户信息
            String userInfo = JSONObject.toJSONString(userVo);
            //userInfo包含中文,先编码，再解码
            String encode = URLEncoder.encode(userInfo);
            Consumer<HttpHeaders> httpHeaders = httpHeader -> {
                httpHeader.set("userInfo", encode);
                httpHeader.set("Authorization", token);
            };
            ServerHttpRequest shReq = serverHttpRequest
                    .mutate()
                    .headers(httpHeaders)
                    .header("language", reqLanguage)
                    .build();
            exchange.mutate().request(shReq).build();
            return chain.filter(exchange);
        }

        return chain.filter(exchange);
    }


    private Mono<Void> getVoidMono(ServerHttpResponse serverHttpResponse, ResponseStatus responseStatus) {
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        ResultVo resultVo = ResultVo.builder()
                .code(responseStatus.getCode())
                .msg(responseStatus.getMsg())
                .build();
        DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JSON.toJSONString(resultVo, SerializerFeature.WriteMapNullValue).getBytes());
        return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }

        @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 请求白名单
     *
     * @return
     */
    private Set<String> getWhiteList() {
        //String whitelists = this.systemPropertiesConfig.getWhitelist();
        if (StringUtils.isEmpty(whitelists)) {
            return new HashSet<>();
        }
        Set<String> whiteList = new HashSet<>();
        String[] whiteArray = whitelists.split(",");
        for (int i = 0; i < whiteArray.length; i++) {
            whiteList.add(whiteArray[i]);
        }
        return whiteList;
    }
}
