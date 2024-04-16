package com.zy.seckill.common.service.impl;

import com.zy.seckill.common.redis.RedisKey;
import com.zy.seckill.common.redis.RedisService;
import com.zy.seckill.common.utils.ObjUtil;
import com.zy.seckill.common.service.AccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
@RequiredArgsConstructor
public class AccessServiceImpl implements AccessService {

    private final RedisService redisService;


    @Override
    public String getAccessUrl(String url, String folderName) {

        if (ObjUtil.isEmpty(folderName)) {
            throw new IllegalArgumentException("访问的文件夹不能为空");
        }

        String accessUrl = redisService.hmGet(RedisKey.SYS_SETTING_KEY, RedisKey.FILE_ACCESS_PREFIX) + url;

        if (ObjUtil.isEmpty(url)) {
            accessUrl = redisService.hmGet(RedisKey.SYS_SETTING_KEY, RedisKey.FILE_ACCESS_PREFIX) + folderName + "/default.jpg";
        }

        return accessUrl;
    }

    @Override
    public String getRelativeUrl(String url, String folderName) {
        if (!url.contains(folderName.trim())) {
            throw new IllegalArgumentException("文件夹名称不对");
        }
        String relativeUrl = url.substring(url.indexOf(folderName.trim()));
        return relativeUrl;
    }

}
