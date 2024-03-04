package com.jsxa.vapp.sys.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.jsxa.vapp.common.bo.po.SysFile;
import com.jsxa.vapp.common.mapper.SysFileDynamicSqlSupport;
import com.jsxa.vapp.common.mapper.SysFileMapper;
import com.jsxa.vapp.common.utils.*;
import com.github.pagehelper.PageHelper;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.validator.FieldDupValidator;
import com.jsxa.vapp.sys.bo.dto.SysFileExcelExportReqDto;
import com.jsxa.vapp.sys.bo.dto.SysFileReqDto;
import com.jsxa.vapp.sys.bo.dto.SysFilePageReqDto;
import com.jsxa.vapp.sys.excel.SysFileExcelListener;
import com.jsxa.vapp.sys.excel.SysFileExcelVo;

import com.jsxa.vapp.sys.service.SysFileService;
import org.springframework.stereotype.Service;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/*
 * @Author zhangyong
 * @Description //SysFileService接口实现类
 * @Date 2022/07/29 11:14
 * @Param
 * @return
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class SysFileServiceImpl implements SysFileService {

    private final CacheUtil cacheUtil;

    private final MinioUtil minioUtil;

    private final SysFileMapper sysFileMapper;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;


    @Override
    @Transactional
    public Map<String, Object> addSysFile(String userInfo,SysFileReqDto sysFileReqDto,MultipartHttpServletRequest request) {

        //1.参数校验
        //字典是否存在
        cacheUtil.getDataDictName(sysFileReqDto.getType());

        String fileKey = sysFileReqDto.getFileKey();
        SysFile sysFileByFileKey = sysFileMapper.selectByExampleOne()
                .where(SysFileDynamicSqlSupport.fileKey, isEqualTo(fileKey))
                .build()
                .execute();
        if(!ObjUtil.isEmpty(sysFileByFileKey)){
            throw new IllegalArgumentException("名称为:"+fileKey+"的fileKey已存在");
        }


        //2.设置参数
        //(1)复制SysFileReqDto中的请求参数到SysFile
        SysFile sysFile = VoPoConverterUtil.copyProperties(sysFileReqDto, SysFile.class);

        //获取到文件
        MultipartFile imgFile = request.getFile("sysFile");
        if (ObjUtil.isEmpty(imgFile)) {
            throw new IllegalArgumentException("文件不能为空");
        }

        String suffix = FileUtil.getSuffix(imgFile.getOriginalFilename());
        String bucketName = "sysFile".toLowerCase();
        if (!minioUtil.existsBucket(bucketName)) {
            minioUtil.createBucket(bucketName);
        }
        String fileName = UUIDUtil.getUUID(28) + suffix;
        minioUtil.upload(bucketName,imgFile,fileName);
        sysFile.setAccessPath("/"+ bucketName +"/" + fileName);
        sysFile.setFileName(fileName).setStorePath(bucketName);
        sysFile.setOriFileName(imgFile.getOriginalFilename());


        //设置其他属性
        sysFile.setId(new IdWorker().nextId()).setCreateTime(System.currentTimeMillis());

        //3.保存
        sysFileMapper.insert(sysFile);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加系统文件信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteSysFileById(String userInfo,Long id){
        SysFile sysFile = sysFileMapper.selectByPrimaryKey(id);
        if(sysFile == null){
            throw new IllegalArgumentException("id为:"+id+"的系统文件信息不存在");
        }

        sysFileMapper.deleteByExample()
                    .where(SysFileDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除系统文件成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateSysFile(String userInfo,SysFileReqDto sysFileReqDto,MultipartHttpServletRequest request) {

        //1.参数校验
        //字典是否存在
        cacheUtil.getDataDictName(sysFileReqDto.getType());

        //sysFile是否存在
        SysFile sysFile = sysFileMapper.selectByPrimaryKey(sysFileReqDto.getId());
        if(ObjUtil.isEmpty(sysFile)){
            throw new IllegalArgumentException("id为:"+sysFileReqDto.getId()+"的系统文件不存在");
        }


        //是否修改了fileKey
        String reqFileKey = sysFileReqDto.getFileKey();
        String fileKey = sysFile.getFileKey();
        if(!reqFileKey.equals(fileKey)){
            SysFile sysFileByFileKey = sysFileMapper.selectByExampleOne()
                    .where(SysFileDynamicSqlSupport.fileKey, isEqualTo(reqFileKey))
                    .build()
                    .execute();
            if(!ObjUtil.isEmpty(sysFileByFileKey)){
                throw new IllegalArgumentException("名称为:"+reqFileKey+"的fileKey已存在");
            }
        }

        //2.如果文件不为空,更新文件
        MultipartFile imgFile = request.getFile("sysFile");
        if (!ObjUtil.isEmpty(imgFile)) {
            String suffix = FileUtil.getSuffix(imgFile.getOriginalFilename());
            String bucketName = "sysFile".toLowerCase();
            if (!minioUtil.existsBucket(bucketName)) {
                minioUtil.createBucket(bucketName);
            }
            String fileName = UUIDUtil.getUUID(28) + suffix;
            minioUtil.upload(bucketName,imgFile,fileName);
            sysFile.setAccessPath("/"+ bucketName +"/" + fileName);
            sysFile.setFileName(fileName).setStorePath(bucketName);
            sysFile.setOriFileName(imgFile.getOriginalFilename());
        }

        //3.更新SysFile
        //(1)复制SysFileDto中的请求参数到SysFile
        VoPoConverterUtil.beanConverterNotNull(sysFileReqDto, sysFile);


        //4.保存
        sysFileMapper.updateByPrimaryKey(sysFile);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新系统文件信息成功");
        return resultMap;
    }

        @Override
        @Transactional
        public Map<String, Object> insertOrUpdateSysFile(SysFileReqDto sysFileReqDto) {

            //1.参数校验
            //字典是否存在
            cacheUtil.getDataDictName(sysFileReqDto.getType());

            //sysFile是否存在
            SysFile sysFile = sysFileMapper.selectByPrimaryKey(sysFileReqDto.getId());
            if(ObjUtil.isEmpty(sysFile)){
                throw new IllegalArgumentException("id为:"+sysFileReqDto.getId()+"的系统文件不存在");
            }

            //3.更新SysFile
            //(1)复制SysFileDto中的请求参数到SysFile
            VoPoConverterUtil.beanConverterNotNull(sysFileReqDto, sysFile);


            //4.保存
            sysFileMapper.updateByPrimaryKey(sysFile);

            //5.返回结果
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","更新系统文件信息成功");
            return resultMap;
        }

    @Override
    public Map<String, Object> getSysFileById(String userInfo,Long id){
        SysFile e = sysFileMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的系统文件信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
                attr.put("id", e.getId());
                attr.put("name", e.getName());
                attr.put("type", e.getType());
                attr.put("fileName", e.getFileName());
                attr.put("fileKey", e.getFileKey());
                attr.put("accessPath", minioUtil.getEndpointUrl() + e.getAccessPath());
                attr.put("storePath", e.getStorePath());
                attr.put("orderNum", e.getOrderNum());
                attr.put("activeFlag", e.getActiveFlag());
                attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getSysFileListPageVo(String userInfo,SysFilePageReqDto sysFilePageReqDto){

        //1.设置分页条件
        PageHelper.startPage(sysFilePageReqDto.getPageNum(), sysFilePageReqDto.getPageSize());

        //2.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SysFile>>>.QueryExpressionWhereBuilder builder = sysFileMapper.selectByExample().where();

        //3.根据查询条件封装查询命令
        //(1).关键字查询
        String name = sysFilePageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
            builder.and(SysFileDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        String fileKey = sysFilePageReqDto.getFileKey();
        if(!ObjUtil.isEmpty(fileKey)){
            builder.and(SysFileDynamicSqlSupport.fileKey, isEqualTo(fileKey));
        }

        Long type = sysFilePageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
            builder.and(SysFileDynamicSqlSupport.type, isEqualTo(type));
        }

        //(2).排序
        builder.orderBy(SysFileDynamicSqlSupport.orderNum.descending());
        //(3).查询
        List<SysFile> list = builder.build().execute();

        //4.构建pageVo
        PageVo<SysFile> pageVo = new PageVo<>(list);

        //5.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                            attr.put("id", e.getId());
                            attr.put("name", e.getName());
                            attr.put("type", e.getType());
                            attr.put("fileName", e.getFileName());
                            attr.put("fileKey", e.getFileKey());
                            attr.put("accessPath", minioUtil.getEndpointUrl() + e.getAccessPath());
                            attr.put("storePath", e.getStorePath());
                            attr.put("orderNum", e.getOrderNum());
                            attr.put("activeFlag", e.getActiveFlag());
                            attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }

    @Override
    public SysFile getSysFileByFileKey(String fileKey) {
        SysFile sysFile = sysFileMapper.selectByExampleOne()
                .where(SysFileDynamicSqlSupport.fileKey, isEqualTo(fileKey))
                .build()
                .execute();
        return sysFile;
    }

    @Override
    public void downloadTemplateExcel(String userInfo,HttpServletResponse response){
        //1.查询到该对象的导入模板
        SysFile sysFile = sysFileMapper.selectByExampleOne()
                .where(SysFileDynamicSqlSupport.fileKey, isEqualTo("sysFileTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(sysFile)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(sysFile.getStorePath(),sysFile.getFileName(),response);
    }

    @Override
    public Map<String, Object> importByExcel(String userInfo,MultipartHttpServletRequest request){

        //1.文件是否为空
        MultipartFile excelFile = request.getFile("xls");
        if (ObjUtil.isEmpty(excelFile)) {
            throw new IllegalArgumentException("文件不能为空");
        }

        //2.清空-->字段重复校验map
        fieldDupValidator.resetFieldSetMap();

        //3.获取excel表格每行的内容
        ExcelReader excelReader = null;
        InputStream in = null;
        SysFileExcelListener sysFileExcelListener = new SysFileExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, SysFileExcelVo.class, sysFileExcelListener).sheet().doRead();
        } catch (IOException ex) {
            ex.getStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (excelReader != null) {
                excelReader.finish();
            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "导入系统文件成功");
        return resultMap;
    }

    @Override
    public void exportToExcel(String userInfo,SysFileExcelExportReqDto sysFileExcelExportReqDto,HttpServletResponse response){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SysFile>>>.QueryExpressionWhereBuilder builder = sysFileMapper.selectByExample().where();
        String fileKey = sysFileExcelExportReqDto.getFileKey();
        if(!ObjUtil.isEmpty(fileKey)){
            builder.and(SysFileDynamicSqlSupport.fileKey, isEqualTo(fileKey));
        }

        Long type = sysFileExcelExportReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
            builder.and(SysFileDynamicSqlSupport.type, isEqualTo(type));
        }
        List<SysFile> sysFileList = builder.build().execute();

        //2.列表转为导出对象列表
        int order = 1;
        List<SysFileExcelVo> sysFileExcelVoList = new ArrayList<>();
        for (int i = 0; i <sysFileList.size() ; i++) {
            SysFile sysFile = sysFileList.get(i);
            SysFileExcelVo sysFileExcelVo = VoPoConverterUtil.copyProperties(sysFile, SysFileExcelVo.class);
            sysFileExcelVo.setOrder(order++)
                          .setTypeCn(cacheUtil.getDataDictName(sysFile.getType()))
                          .setAccessPath(minioUtil.getEndpointUrl() + sysFileExcelVo.getAccessPath());
            sysFileExcelVoList.add(sysFileExcelVo);
        }

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "系统文件_";
            String fileName = sheetName.concat(DateUtil.getCurrentTimeStrring()).concat(".xlsx");

            //(2).response输出文件流格式
            //response.setContentType("APPLICATION/OCTET-STREAM");
            //response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));

            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            //(3).获取到输出流
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.将输出流写入到response,直接响应给浏览器
        EasyExcel.write(outputStream)
                .head(SysFileExcelVo.class)
                .sheet("Sheet1")
                .doWrite(sysFileExcelVoList);
    }
}