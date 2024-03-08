package com.jsxa.vapp.order.rocksDB;

import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.order.bo.vo.RocksDBVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.rocksdb.RocksDBException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 测试rocksdb 
 */
@SuppressWarnings("DuplicatedCode")
@Api(tags = "RocksDB")
@RestController
@RequestMapping(value = "/order/rocksdb")
public class RocksDBController {

    @ApiOperation("列族，创建（如果不存在）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cfName", value = "列族", required = true),
    })
    @PostMapping("/cf")
    public ResultVo<Map<String, Object>> cfAdd(String cfName) throws RocksDBException {
        RocksDBUtil.cfAddIfNotExist(cfName);
        return ResultVo.ok(cfName);
    }

    @ApiOperation("列族，删除（如果存在）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cfName", value = "列族", required = true),
    })
    @DeleteMapping("/cf")
    public ResultVo<Map<String, Object>> cfDelete(String cfName) throws RocksDBException {
        RocksDBUtil.cfDeleteIfExist(cfName);
        return ResultVo.ok(cfName);
    }

    @ApiOperation("列族名（查询所有）")
    @GetMapping("/cf-all")
    public ResultVo<Map<String, Object>> cfAll() {
        Set<String> cfNames = RocksDBUtil.columnFamilyHandleMap.keySet();
        ResultVo<Map<String, Object>> response = ResultVo.ok(cfNames);
        return response;
    }

    @ApiOperation("增")
    @PostMapping("/put")
    public ResultVo<Map<String, Object>> put(@RequestBody RocksDBVo rocksDBVo) throws RocksDBException {
        RocksDBUtil.put(rocksDBVo.getCfName(), rocksDBVo.getKey(), rocksDBVo.getValue());
        return ResultVo.ok(rocksDBVo);
    }

    @ApiOperation("增（批量）")
    @PostMapping("/batch-put")
    public ResultVo<Map<String, Object>> batchPut(@RequestBody List<RocksDBVo> rocksDBVos) throws RocksDBException {
        Map<String, String> map = new HashMap<>();
        for (RocksDBVo rocksDBVo : rocksDBVos) {
            map.put(rocksDBVo.getKey(), rocksDBVo.getValue());
        }
        RocksDBUtil.batchPut(rocksDBVos.get(0).getCfName(), map);
        ResultVo<Map<String, Object>> response = ResultVo.ok(rocksDBVos);
        return response;
    }

    @ApiOperation("删")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cfName", value = "列族", required = true),
            @ApiImplicitParam(name = "key", value = "键", required = true),
    })
    @DeleteMapping("/delete")
    public ResultVo<Map<String, Object>> delete(String cfName, String key) throws RocksDBException {
        String value = RocksDBUtil.get(cfName, key);
        RocksDBUtil.delete(cfName, key);
        RocksDBVo rocksDBVo = RocksDBVo.builder().cfName(cfName).key(key).value(value).build();
        return ResultVo.ok(rocksDBVo);
    }

    @ApiOperation("查")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cfName", value = "列族", required = true),
            @ApiImplicitParam(name = "key", value = "键", required = true),
    })
    @GetMapping("/get")
    public ResultVo<Map<String, Object>> get(String cfName, String key) throws RocksDBException {
        String value = RocksDBUtil.get(cfName, key);
        RocksDBVo rocksDBVo = RocksDBVo.builder().cfName(cfName).key(key).value(value).build();
        return ResultVo.ok(rocksDBVo);
    }

    @ApiOperation("查（多个键值对）")
    @PostMapping("/multiGetAsList")
    public ResultVo<Map<String, Object>> multiGetAsList(@RequestBody List<RocksDBVo> rocksDBVos) throws RocksDBException {
        List<RocksDBVo> list = new ArrayList<>();
        String cfName = rocksDBVos.get(0).getCfName();
        List<String> keys = new ArrayList<>(rocksDBVos.size());
        for (RocksDBVo rocksDBVo : rocksDBVos) {
            keys.add(rocksDBVo.getKey());
        }
        Map<String, String> map = RocksDBUtil.multiGetAsMap(cfName, keys);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            RocksDBVo rocksDBVo = RocksDBVo.builder().cfName(cfName).key(entry.getKey()).value(entry.getValue()).build();
            list.add(rocksDBVo);
        }
        ResultVo<Map<String, Object>> response = ResultVo.ok(list);
        return response;
    }

    @ApiOperation("查所有键值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cfName", value = "列族", required = true),
    })
    @GetMapping("/get-all")
    public ResultVo<Map<String, Object>> getAll(String cfName) throws RocksDBException {
        List<RocksDBVo> rocksDBVos = new ArrayList<>();
        Map<String, String> all = RocksDBUtil.getAll(cfName);
        for (Map.Entry<String, String> entry : all.entrySet()) {
            RocksDBVo rocksDBVo = RocksDBVo.builder().cfName(cfName).key(entry.getKey()).value(entry.getValue()).build();
            rocksDBVos.add(rocksDBVo);
        }
        ResultVo<Map<String, Object>> response = ResultVo.ok(rocksDBVos);
        return response;
    }

    @ApiOperation("分片查（键）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cfName", value = "列族", required = true),
    })
    @GetMapping("/get-keys")
    public ResultVo<Map<String, Object>> getKeysFrom(String cfName) throws RocksDBException {
        List<String> data = new ArrayList<>();
        List<String> keys;
        String lastKey = null;
        while (true) {
            keys = RocksDBUtil.getKeysFrom(cfName, lastKey);
            if (keys.isEmpty()) {
                break;
            }
            lastKey = keys.get(keys.size() - 1);
            data.addAll(keys);
            keys.clear();
        }
        ResultVo<Map<String, Object>> response = ResultVo.ok(data);
        return response;
    }

    @ApiOperation("查（所有键）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cfName", value = "列族", required = true),
    })
    @GetMapping("/get-all-key")
    public ResultVo<Map<String, Object>> getAllKey(String cfName) throws RocksDBException {
        List<String> allKey = RocksDBUtil.getAllKey(cfName);
        ResultVo<Map<String, Object>> response = ResultVo.ok(allKey);
        return response;
    }

    @ApiOperation("查总条数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cfName", value = "列族", required = true),
    })
    @GetMapping("/get-count")
    public ResultVo<Map<String, Object>> getCount(String cfName) throws RocksDBException {
        int count = RocksDBUtil.getCount(cfName);
        return ResultVo.ok(count);
    }
}