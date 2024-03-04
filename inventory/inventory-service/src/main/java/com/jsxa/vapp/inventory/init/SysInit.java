package com.jsxa.vapp.inventory.init;

import com.alibaba.fastjson.JSONObject;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.utils.ObjUtil;
import com.jsxa.vapp.inventory.bo.po.VaccinationPerson;
import com.jsxa.vapp.inventory.bo.po.VaccinationSite;
import com.jsxa.vapp.inventory.bo.po.Vaccine;
import com.jsxa.vapp.inventory.bo.po.VaccineRelease;
import com.jsxa.vapp.inventory.mapper.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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


    private final RedisService redisService;

    private final VaccinationPersonMapper vaccinationPersonMapper;

    private final VaccinationSiteMapper vaccinationSiteMapper;

    private final VaccineMapper vaccineMapper;

    private final VaccineReleaseMapper vaccineReleaseMapper;



    //(1).系统启动后，加载系统配置
    @EventListener(ContextRefreshedEvent.class)
    @Transactional(rollbackFor = Exception.class)
    public void initSysConfig() {

        log.info("step1 ---> 开始同步接种人员信息到Redis");
        List<VaccinationPerson> vaccinationPersonList = vaccinationPersonMapper.selectByExample()
                .build()
                .execute();
        vaccinationPersonList.forEach(e -> {
            redisService.hmSet("VaccinationPerson", String.valueOf(e.getOpenId()), JSONObject.toJSONString(e));
        });

        log.info("step2 ---> 开始同步接种地点信息到Redis");
        List<VaccinationSite> vaccinationSiteList = vaccinationSiteMapper.selectByExample()
                .build()
                .execute();
        vaccinationSiteList.forEach(e -> {
            redisService.hmSet("VaccinationSite", String.valueOf(e.getId()), JSONObject.toJSONString(e));
        });


        log.info("step3 ---> 开始同步疫苗信息到Redis");
        List<Vaccine> vaccineList = vaccineMapper.selectByExample()
                .build()
                .execute();
        vaccineList.forEach(e -> {
            redisService.hmSet("Vaccine", String.valueOf(e.getId()), JSONObject.toJSONString(e));
        });


        log.info("step4 ---> 开始同步 启用成功的疫苗发放信息到Redis(status=1的疫苗发放记录)");
        VaccineRelease release = vaccineReleaseMapper.selectByExampleOne()
                .where(VaccineReleaseDynamicSqlSupport.status, isEqualTo((byte)1))
                .build()
                .execute();
        if(!ObjUtil.isEmpty(release)){
            redisService.hmSet("VaccineRelease", String.valueOf(release.getId()), JSONObject.toJSONString(release));
        }
    }
}
