package com.zy.seckill.common.shardingJdbc;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 自定义分表规则类
 * </p>
 *
 * @author zhengqingya
 * @description 复合分片算法
 * @date xxxx/11/1 11:19
 */
@Slf4j
@Component(value = "complexKeysShardingAlgorithm")
public class MyTableComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<String> {

    @Override
    public Collection<String> doSharding(Collection<String> tableNameList, ComplexKeysShardingValue<String> complexKeysShardingValue) {
        log.info("[复合分片算法] complexKeysShardingValue: [{}]", complexKeysShardingValue);
        Set<String> tableNameResultList = new LinkedHashSet<>();

        Long id = (Long) this.getShardingValue(complexKeysShardingValue, "id");
        String suffix = String.valueOf(id % 10);
        for (String tableNameItem : tableNameList) {
            if (tableNameItem.endsWith(suffix)) {
                tableNameResultList.add(tableNameItem);
                return tableNameResultList;
            }
        }
        return tableNameList;
    }

    private Object getShardingValue(ComplexKeysShardingValue<String> shardingValues, final String key) {
        Map<String, Collection<String>> columnNameAndShardingValuesMap = shardingValues.getColumnNameAndShardingValuesMap();
        if (columnNameAndShardingValuesMap.containsKey(key)) {
            return columnNameAndShardingValuesMap.get(key).toArray()[0];
        }
        return null;
    }
}
