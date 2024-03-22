package com.jsxa.vapp.gateway.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsxa.vapp.gateway.vo.UserVo;
import io.jsonwebtoken.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/*
 * @Author: zhangyong
 * description: JWT工具类
 * @Date: 2021-08-31 17:18
 * @Param:
 * @Return:
 */

@Component
@ConditionalOnProperty(prefix = "jwt", name = "secret")
@Slf4j
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new IllegalArgumentException("token校验失败");
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取用户信息
     */
    public UserVo getUserDetailsFromToken(String token) {

        if(isTokenExpired(token)){
            return null;
        }
        UserVo userVo;
        try {
            Claims claims = getClaimsFromToken(token);
            userVo = new ObjectMapper().readValue(claims.getSubject(), UserVo.class);
            return userVo;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new IllegalArgumentException("token校验失败");
        }
    }


    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生成token
     */
    @SneakyThrows
    public String generateToken(UserVo userVo) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(CLAIM_KEY_USERNAME, new ObjectMapper().writeValueAsString(userVo));
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    @SneakyThrows
    public String generateTokenByMap(Map<String, Object> map) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(CLAIM_KEY_USERNAME, new ObjectMapper().writeValueAsString(map));
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否可以被刷新
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

}
