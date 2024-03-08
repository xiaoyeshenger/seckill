package com.jsxa.vapp.sys.init;

import com.jsxa.vapp.common.bo.po.*;
import com.jsxa.vapp.common.cache.*;
import com.jsxa.vapp.common.mapper.*;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.utils.IdWorker;
import com.jsxa.vapp.common.utils.ObjUtil;
import com.jsxa.vapp.common.utils.SHA256Util;
import com.jsxa.vapp.sys.bo.po.PageTemplate;
import com.jsxa.vapp.sys.bo.po.ParamSetting;
import com.jsxa.vapp.sys.bo.po.SysSetting;
import com.jsxa.vapp.sys.mapper.PageTemplateDynamicSqlSupport;
import com.jsxa.vapp.sys.mapper.PageTemplateMapper;
import com.jsxa.vapp.sys.mapper.ParamSettingMapper;
import com.jsxa.vapp.sys.mapper.SysSettingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/*
 * @Author: zhangyong
 * description: 系统初始化
 * @Date: 2021-02-01 10:24
 * @Param:
 * @Return:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SysInit {

    //登录密码加盐
    @Value("${sys.pwd.salt}")
    private String pwdSalt;


    private final RedisService redisService;

    private final SysSettingMapper sysSettingMapper;

    private final ParamSettingMapper paramSettingMapper;

    private final UserMapper userMapper;

    private final RoleMapper roleMapper;

    private final UserRoleMapper userRoleMapper;

    private final DataDictMapper dataDictMapper;

    private final PageTemplateMapper pageTemplateMapper;

    private final DictCache dictCache;



    //(2).系统启动后，加载系统配置
    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void initSysConfig() {

        log.info("step1 ---> 开始同步全局系统设置信息到Redis");
        //--1.从数据库中查询所有的系统设置信息
        List<SysSetting> sysSettingList = sysSettingMapper.selectByExample()
                .build()
                .execute();
        sysSettingList.forEach(ss -> {
            redisService.hmSet(RedisKey.SYS_SETTING_KEY, ss.getSettingKey(), ss.getSettingValue());
        });

        log.info("step2 ---> 开始同步数据字典ID和NAME到Redis");
        //--3.从数据库中查询所有的字典信息
        List<DataDict> dataDictList = dataDictMapper.selectByExample()
                .build()
                .execute();
        dataDictList.forEach(dataDict -> {
            //字典ID作为唯一键
            redisService.hmSet(RedisKey.SYS_DATADICT_KEY, String.valueOf(dataDict.getId()), dataDict.getName());

            //父级ID+名称组成唯一键
            redisService.set(RedisKey.SYS_DATADICT_NAME + ":" + String.valueOf(dataDict.getParentId())+ ":" + dataDict.getName(), dataDict.getId());
        });

        //--4.重新加载所有的数据字典
        log.info("step3 ---> 开始同步数据字典到本地缓存");
        dictCache.reloadDataDict();


        //--7.初始化用户信息
        //a.超级管理员的角色
        log.info("step10 ---> 开始初始化超级管理员及权限信息");
        Role role = roleMapper.selectByExampleOne()
                .where(RoleDynamicSqlSupport.useType, isEqualTo(17L))
                .and(RoleDynamicSqlSupport.name, isEqualTo("超级管理员"))
                .and(RoleDynamicSqlSupport.roleKey, isEqualTo("SuperAdmin"))
                .build()
                .execute();
        if (ObjUtil.isEmpty(role)) {
            role = Role.builder()
                    .id(new IdWorker().nextId())
                    .name("超级管理员")
                    .roleKey("SuperAdmin")
                    .useType(17L)
                    .status((byte)1)
                    .delFlag((byte)0)
                    .createTime(System.currentTimeMillis())
                    .build();
            roleMapper.insert(role);
        }

        //b.超级管理员的用户信息
        User user = userMapper.selectByExampleOne()
                .where(UserDynamicSqlSupport.username, isEqualTo("admin"))
                .and(UserDynamicSqlSupport.name, isEqualTo("超级管理员"))
                .and(UserDynamicSqlSupport.roleKey, isEqualTo("SuperAdmin"))
                .build()
                .execute();
        if (ObjUtil.isEmpty(user)) {
            String pwd = "jskjad999666";
            user = User.builder()
                    .id(new IdWorker().nextId())
                    .name("超级管理员")
                    .username("admin")
                    .roleKey("SuperAdmin")
                    .password(SHA256Util.getSHA256Salt(pwd, pwdSalt))
                    .userSex((byte)1)
                    .regType(8L)
                    .status((byte)1)
                    .delFlag((byte)0)
                    .createTime(System.currentTimeMillis())
                    .build();
            userMapper.insert(user);
        }

        //c.用户-角色中间表
        Long userId = user.getId();
        Long roleId = role.getId();
        UserRole userRole = userRoleMapper.selectByExampleOne()
                .where(UserRoleDynamicSqlSupport.userId, isEqualTo(userId))
                .and(UserRoleDynamicSqlSupport.roleId, isEqualTo(roleId))
                .build()
                .execute();
        if (ObjUtil.isEmpty(userRole)) {
            userRole =  UserRole.builder()
                    .userId(userId)
                    .roleId(roleId)
                    .createTime(System.currentTimeMillis())
                    .build();
            userRoleMapper.insert(userRole);
        }
    }
}
