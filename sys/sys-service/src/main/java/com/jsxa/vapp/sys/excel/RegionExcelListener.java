package com.jsxa.vapp.sys.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.jsxa.vapp.common.mapper.RegionMapper;
import com.jsxa.vapp.common.utils.PinYinUtil;
import com.jsxa.vapp.common.utils.VoPoConverterUtil;
import com.jsxa.vapp.sys.bo.dto.RegionReqDto;
import com.jsxa.vapp.sys.service.RegionService;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Component
public class RegionExcelListener extends AnalysisEventListener<RegionExcelVo> {

    //每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
    private static final int BATCH_COUNT = 1;

    private boolean isFail = false;

    private Field[] fields;

    private Class<T> tClass;

    private List<RegionExcelVo> expertExcelVoList = new ArrayList<>();


    private RegionService service;

    private Validator validator;

    private RegionMapper mapper;

    @Tolerate
    public RegionExcelListener() {}

    public RegionExcelListener(RegionService service, RegionMapper mapper, Validator validator) {
        this.service = service;
        this.mapper = mapper;
        this.validator = validator;
    }


    //每解析一条数据都会调用invoke方法，相当于每行的解析方法,此处可做进一步的参数校验
    @Override
    public void invoke(RegionExcelVo regionExcelVo, AnalysisContext analysisContext) {
        System.out.println("expertExcelVo::::::::::::::" + regionExcelVo);
        Integer currentRowNum = analysisContext.readRowHolder().getRowIndex();
        System.out.println("当前行:::" + currentRowNum);

        expertExcelVoList.add(regionExcelVo);
    }

    //1.参数校验
    private void validate(RegionExcelVo r,Integer rowNum) {
        //(1).通过herbernate校验参数
        hibernateValidate(r,rowNum);

    }

    //(2).hibernate注解 参数校验
    private void hibernateValidate(RegionExcelVo r,Integer rowNum) {
        Set<ConstraintViolation<RegionExcelVo>> validateSet = validator.validate(r, Default.class);
        if (validateSet != null && !validateSet.isEmpty()) {
            ConstraintViolation<RegionExcelVo> constraint = validateSet.stream().findAny().orElse(null);
            String message = constraint.getMessage();
            throw new IllegalArgumentException("表格第"+rowNum+"行，"+message);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        //1.导入数据库
        importDataBase(expertExcelVoList);
    }

    //导入数据到数据库
    @Transactional
    private void importDataBase(List<RegionExcelVo> portExcelVOList) {
        portExcelVOList.forEach(e -> {
            RegionReqDto regionReqDto = VoPoConverterUtil.copyProperties(e, RegionReqDto.class);
            regionReqDto.setCode(e.getCid())
                    .setType(406L)
                    .setValue(PinYinUtil.getPinYinHeadChar(e.getName()))
                    .setParentCode(e.getBelongVid());
            service.addRegion(regionReqDto);
        });
    }
}
