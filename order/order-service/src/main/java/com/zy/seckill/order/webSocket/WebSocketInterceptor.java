package com.zy.seckill.order.webSocket;

import com.zy.seckill.common.bo.vo.UserVo;
import com.zy.seckill.common.redis.RedisKey;
import com.zy.seckill.common.redis.RedisService;
import com.zy.seckill.common.utils.JwtTokenUtil;
import com.zy.seckill.common.utils.ObjUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: zhangyong
 * description: WebSocketInterceptor拦截器，目的是拦截 webSocket请求的 url,
 *              取出其中的请求参数(具体转发路径见配置webSocketConfigurer
 * @Date: 2020-03-11 10:39
 * @Param:
 * @Return:
 */
@Component
@Slf4j
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {


    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private RedisService redisService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        //1.校验请求头
        //(1).token为空
        /*String token = request.getHeaders().getFirst("Authorization");
        if (ObjUtil.isEmpty(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return super.beforeHandshake(request, response, wsHandler, attributes);
        }

        UserVo userVo;
        try {
            userVo = jwtTokenUtil.getUserDetailsFromToken(token);
        } catch (IllegalArgumentException e) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return super.beforeHandshake(request, response, wsHandler, attributes);
        }

        if (ObjUtil.isEmpty(userVo)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return super.beforeHandshake(request, response, wsHandler, attributes);
        }

        //(2).redis中没有该账户的token
        Long id = userVo.getId();
        String userId = String.valueOf(id);
        if (!redisService.hasKey(RedisKey.USER_TOKEN_KEY+userId)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return super.beforeHandshake(request, response, wsHandler, attributes);
        }*/


        //2.获取restful风格中的请求参数(ws://localhost:950/api/ws/bid/1501815782786256896)
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        Map paramMap = (Map) servletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        //3.取出webSocket中的参数(参数由WebSocketConfig配置,此处参数是key)，并封装进attributes，这样在WsService中就可以取出参数使用
        String key = (String) paramMap.get("key");
        if(ObjUtil.isEmpty(key)){
            throw new IllegalArgumentException("key不能为空");
        }

        String type = (String) paramMap.get("type");
        if(ObjUtil.isEmpty(type)){
            throw new IllegalArgumentException("type不能为空");
        }

        //4.保存到attributes
        attributes.put("type",type);
        attributes.put("key",key);
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

}