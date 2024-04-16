package com.zy.seckill.common.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @Author: zhangyong
 * description: jwt过滤器(只需要有token即可正常访问接口,因为gateway已经验证过一次，无需再次验证token)
 * @Date: 2019-12-11 12:35
 * @Param:
 * @Return:
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private static final String TOKEN = "Authorization";

    //是否允许通过,向向shiro登录注册成功后即可再允许访问接口
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        /*HttpServletRequest hr = (HttpServletRequest)request;

        String requestURI = hr.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        log.info("step1 ---> 当前请求的路径:{}",requestURI);
        log.info("step2 ---> 当前请求的ip地址:{}",remoteAddr);*/

        if (isLoginAttempt(request, response)) {
            return executeLogin(request, response);
        }
        return false;
    }

    //允许登录的条件(此处为，有token即可登录)
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(TOKEN);
        return token != null;
    }

    //向shiro登录注册用户信息(每次shiro就会去读取用户信息的角色和权限列表，以比对是否具有接口或资源访问权限，没有即抛出异常，系统通过全局异常增强器捕捉该异常即可)
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(TOKEN);
        JwtToken jwtToken = new JwtToken(token);
        getSubject(request, response).login(jwtToken);
        return true;
    }


    //跨域支持
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
        httpServletResponse.setStatus(HttpStatus.OK.value());
        return false;
        }
        return super.preHandle(request, response);
    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");//先从nginx自定义配置获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-real-ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
