package com.jsxa.vapp.common.config;

import com.alibaba.nacos.common.utils.StringUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.jdbc.DataSourceHealthContributorAutoConfiguration;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;
/**
 * 增加该类，以免springboot集成sharding jdbc后报错:
 * org.springframework.dao.InvalidDataAccessApiUsageException:
 * ConnectionCallback; isValid; nested exception is java.sql.SQLFeatureNotSupportedException: isValid
 */
@Configuration
public class DataSourceHealthConfig extends DataSourceHealthContributorAutoConfiguration {

    @Value("${spring.datasource.ds-0.validation-query:select 1}")
    private String defaultQuery;


    public DataSourceHealthConfig(Map<String, DataSource> dataSources, ObjectProvider<DataSourcePoolMetadataProvider> metadataProviders) {
        super(dataSources, metadataProviders);
    }

    @Override
    protected AbstractHealthIndicator createIndicator(DataSource source) {
        DataSourceHealthIndicator indicator = (DataSourceHealthIndicator) super.createIndicator(source);
        if (!StringUtils.hasText(indicator.getQuery())) {
            indicator.setQuery(defaultQuery);
        }
        return indicator;
    }
}
