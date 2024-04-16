package com.zy.seckill.inventory.service.impl;

import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.inventory.bo.dto.ProductReqDto;
import com.zy.seckill.inventory.bo.dto.ProductPageReqDto;
import com.zy.seckill.inventory.bo.po.Product;
import com.zy.seckill.inventory.mapper.ProductMapper;
import com.zy.seckill.inventory.mapper.ProductDynamicSqlSupport;
import com.zy.seckill.inventory.excel.ProductExcelListener;
import com.zy.seckill.inventory.excel.ProductExcelVo;
import com.zy.seckill.common.utils.*;
import com.zy.seckill.common.validator.FieldDupValidator;
import com.zy.seckill.inventory.service.ProductService;
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


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author zhangyong
 * @Description //ProductService接口实现类
 * @Date xxxx/02/27 14:20
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final MinioUtil minioUtil;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final ProductMapper productMapper;


    @Override
    @Transactional
    public Map<String, Object> addProduct(ProductReqDto productReqDto) {

        //1.参数校验

        //2.设置参数
        //(1)复制ProductReqDto中的请求参数到Product
        Product product = VoPoConverterUtil.copyProperties(productReqDto, Product.class);

        //(2)设置其他属性
        product.setId(new IdWorker().nextId()).setStatus((byte)1).setCreateTime(System.currentTimeMillis());

        //3.保存
        productMapper.insert(product);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加商品信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteProductById(Long id){
        Product product = productMapper.selectByPrimaryKey(id);
        if(product == null){
            throw new IllegalArgumentException("id为:"+id+"的商品信息不存在");
        }

        productMapper.deleteByExample()
                    .where(ProductDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除商品成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateProduct(ProductReqDto productReqDto) {
        //1.参数校验

        //2.判断Product是否存在
        Product product = productMapper.selectByPrimaryKey(productReqDto.getId());
        if(ObjUtil.isEmpty(product)){
            throw new IllegalArgumentException("id为:"+ productReqDto.getId()+"的商品不存在");
        }

        //3.更新Product
        //(1)复制ProductDto中的请求参数到Product
        VoPoConverterUtil.beanConverterNotNull(productReqDto, product);

        //4.保存
        productMapper.updateByPrimaryKey(product);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新商品信息成功");
        return resultMap;
    }

        @Override
        @Transactional
        public Map<String, Object> insertOrUpdateProduct(ProductReqDto productReqDto) {
            //1.参数校验

            //2.判断Product是否存在
            Product product = productMapper.selectByPrimaryKey(productReqDto.getId());
            if(ObjUtil.isEmpty(product)){
                throw new IllegalArgumentException("id为:"+ productReqDto.getId()+"的商品不存在");
            }

            //3.更新Product
            //(1)复制ProductDto中的请求参数到Product
            VoPoConverterUtil.beanConverterNotNull(productReqDto, product);

            //4.保存
            productMapper.updateByPrimaryKey(product);

            //5.返回结果
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","更新商品信息成功");
            return resultMap;
        }

    @Override
    public Map<String, Object> getProductById(Long id){
        Product e = productMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的商品信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("orgId", e.getOrgId());
        attr.put("orgName", e.getOrgName());
        attr.put("name", e.getName());
        attr.put("manufacturer", e.getManufacturer());
        attr.put("batchNumber", e.getBatchNumber());
        attr.put("stock", e.getStock());
        attr.put("oosUrl", e.getOosUrl());
        attr.put("orderNum", e.getOrderNum());
        attr.put("status", e.getStatus());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getProductListPageVo(ProductPageReqDto productPageReqDto){

        //1.查询列表
        List<Product> list = queryListByPageReqDto(productPageReqDto,true);

        //2.构建pageVo
        PageVo<Product> pageVo = new PageVo<>(list);

        //3.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("orgId", e.getOrgId());
                    attr.put("orgName", e.getOrgName());
                    attr.put("name", e.getName());
                    attr.put("manufacturer", e.getManufacturer());
                    attr.put("batchNumber", e.getBatchNumber());
                    attr.put("stock", e.getStock());
                    attr.put("oosUrl", e.getOosUrl());
                    attr.put("orderNum", e.getOrderNum());
                    attr.put("status", e.getStatus());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }

    @Override
    public void downloadTemplateExcel(HttpServletResponse response){
        //1.查询到该对象的导入模板
        /*Product Product = ProductMapper.selectByExampleOne()
                .where(ProductDynamicSqlSupport.fileKey, isEqualTo("ProductTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(Product)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(Product.getStorePath(),Product.getName(),response);*/
    }

    @Override
    public Map<String, Object> importByExcel(MultipartHttpServletRequest request){

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
        ProductExcelListener productExcelListener = new ProductExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, ProductExcelVo.class, productExcelListener).sheet().doRead();
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
    public void exportToExcel(ProductPageReqDto productPageReqDto, HttpServletResponse response){
        //1.查询列表
        List<Product> productList = queryListByPageReqDto(productPageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<ProductExcelVo> productExcelVoList = productList.stream().map(e -> {
            ProductExcelVo productExcelVo = VoPoConverterUtil.copyProperties(e, ProductExcelVo.class);
            return productExcelVo;
        }).collect(Collectors.toList());

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "商品";
            String fileName = sheetName.concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx");

            //(2).response输出文件流格式
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));

            //(3).获取到输出流
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.将输出流写入到response,直接响应给浏览器
        EasyExcel.write(outputStream)
                .head(ProductExcelVo.class)
                .sheet("Sheet1")
                .doWrite(productExcelVoList);
    }

    private List<Product> queryListByPageReqDto(ProductPageReqDto productPageReqDto, Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Product>>>.QueryExpressionWhereBuilder builder = productMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        /*String name = ProductPageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
        builder.and(ProductDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long type = ProductPageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
        builder.and(ProductDynamicSqlSupport.type, isEqualTo(type));
        }

        Long startTime = ProductPageReqDto.getStartTime();
        Long endTime = ProductPageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
        builder.and(ProductDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
        builder.and(ProductDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
            builder.and(ProductDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
            builder.and(ProductDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
            }
        }*/

        //3.排序
        builder.orderBy(ProductDynamicSqlSupport.createTime.descending());

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(productPageReqDto.getPageNum(), productPageReqDto.getPageSize());
        }

        List<Product> list = builder.build().execute();
        return list;
    }
}