package com.jsxa.vapp.order.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.utils.*;
import com.jsxa.vapp.common.validator.FieldDupValidator;
import com.jsxa.vapp.inventory.bo.po.VaccinationPerson;
import com.jsxa.vapp.inventory.bo.po.VaccinationSite;
import com.jsxa.vapp.inventory.bo.po.Vaccine;
import com.jsxa.vapp.inventory.bo.po.VaccineRelease;
import com.jsxa.vapp.order.bo.dto.VaccinationRecordPageReqDto;
import com.jsxa.vapp.order.bo.dto.VaccinationRecordReqDto;
import com.jsxa.vapp.order.bo.po.VaccinationRecord;
import com.jsxa.vapp.order.excel.VaccinationRecordExcelListener;
import com.jsxa.vapp.order.excel.VaccinationRecordExcelVo;
import com.jsxa.vapp.order.feign.InventoryServiceFeignClient;
import com.jsxa.vapp.order.mapper.VaccinationRecordDynamicSqlSupport;
import com.jsxa.vapp.order.mapper.VaccinationRecordMapper;
import com.jsxa.vapp.order.service.OrderService;
import com.jsxa.vapp.order.service.VaccinationRecordService;
import io.seata.core.context.RootContext;
import io.seata.core.model.BranchType;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @Author zhangyong
 * @Description //VaccinationRecordService接口实现类
 * @Date 2021/02/27 15:20
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    private final VaccinationRecordMapper vaccinationRecordMapper;


    /**
     * 处理订单
     * 注意: 调用inventory微服务扣减库存和将订单信息保存到数据库是在2个不同的微服务中进行操作,
     *      所以需要使用分布式事务Seata或rocketmq的事务消息保证2个动作的事务性,
     *      此处使用seata的AT模式，使用方法为在需要开始分布式事务的方法或类上开启全局事务@GlobalTransactional
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public void processOrder(VaccinationRecord vaccinationRecord) {

        //获取当前事务的XID
        String xid = RootContext.getXID();
        //BranchType branchType = RootContext.getBranchType();
        //String branchTypeName = RootContext.getBranchType();
        log.info("Order--------------> threadId:{},threadName:{},Seata_XID:{}",Thread.currentThread().getId(),Thread.currentThread().getName(),xid);

        //(1).查询到该订单对应的放苗活动id
        Long vaccineReleaseId = vaccinationRecord.getVaccineReleaseId();

        //(2).调用inventory微服务扣减库存(库存扣减采用乐观锁version版本的机制),判断库存微服务响应码是否正常，不正常则抛出异常让seata分布式事务感知该异常以便做出回滚
        Map<String, Object> resultMap = inventoryServiceFeignClient.reduceDock(vaccineReleaseId);
        Integer code = (Integer) resultMap.get("code");
        if(code != 200){
            throw new IllegalArgumentException("调用扣减库存失败");
        }

        //(3).将订单信息保存到数据库
        vaccinationRecordMapper.insert(vaccinationRecord);

    }
}