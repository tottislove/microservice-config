package com.xc.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.xc.datasource.DataSourceType;
import com.xc.datasource.DynamicDataSource;
import com.xc.plugin.SessionFactorySupport;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * 数据源配置
 * Created by xiongying on 16/11/11.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = DatabaseConfiguration.PACKAGE)
public class DatabaseConfiguration implements EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfiguration.class);

    static final String PACKAGE = "com.xc";
    static final String PACKAGES_TO_SCAN = "com.xc.**.domain";

    private Environment environment;
    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
    }

    private DruidDataSource generateDruidDataSource(String pre) throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(propertyResolver.getProperty(pre + "." + "driver-class-name"));
        druidDataSource.setUrl(propertyResolver.getProperty(pre + "." + "url"));
        druidDataSource.setUsername(propertyResolver.getProperty(pre + "." + "username"));
        druidDataSource.setPassword(propertyResolver.getProperty(pre + "." + "password"));
        druidDataSource.setInitialSize(Integer.parseInt(propertyResolver.getProperty(pre + "." + "initialSize")));
        druidDataSource.setMinIdle(Integer.parseInt(propertyResolver.getProperty(pre + "." + "minIdle")));
        druidDataSource.setMaxActive(Integer.parseInt(propertyResolver.getProperty(pre + "." + "maxActive")));
        druidDataSource.setMaxWait(Integer.parseInt(propertyResolver.getProperty(pre + "." + "maxWait")));
        druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(propertyResolver.getProperty(pre + "." + "timeBetweenEvictionRunsMillis")));
        druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(propertyResolver.getProperty(pre + "." + "minEvictableIdleTimeMillis")));
        druidDataSource.setValidationQuery(propertyResolver.getProperty(pre + "." + "validationQuery"));
        druidDataSource.setTestWhileIdle(Boolean.parseBoolean(propertyResolver.getProperty(pre + "." + "testWhileIdle")));
        druidDataSource.setTestOnBorrow(Boolean.parseBoolean(propertyResolver.getProperty(pre + "." + "testOnBorrow")));
        druidDataSource.setTestOnReturn(Boolean.parseBoolean(propertyResolver.getProperty(pre + "." + "testOnReturn")));
        druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(propertyResolver.getProperty(pre + "." + "poolPreparedStatements")));
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(propertyResolver.getProperty(pre + "." + "maxPoolPreparedStatementPerConnectionSize")));
        druidDataSource.setFilters(propertyResolver.getProperty(pre + "." + "filters"));

        Collection connectionInitSqls = new ArrayList();
        connectionInitSqls.add("set names 'utf8mb4'");
        druidDataSource.setConnectionInitSqls(connectionInitSqls);

        logger.info(String.format("DruidDataSource[%s] is initing,url[%s]", pre, propertyResolver.getProperty(pre + "." + "url")));
        return druidDataSource;
    }

    @Bean(name = "masterDataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource masterDataSource() throws SQLException {
        return generateDruidDataSource("master");
    }

    @Bean(name = "slaveDataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource slaveDataSource(@Qualifier("masterDataSource") DruidDataSource masterDataSource) throws SQLException {
        if (StringUtils.isNotBlank(propertyResolver.getProperty("slave" + "." + "url"))) {
            return generateDruidDataSource("slave");
        }
        return masterDataSource;
    }

    @Bean(name = "dataSource")
    @Primary
    public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource, @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.WRITE, masterDataSource);
        if (slaveDataSource == null) {
            targetDataSources.put(DataSourceType.READ, masterDataSource);
            logger.info("slaveDataSource not configured, use the default masterDataSource");
        } else {
            targetDataSources.put(DataSourceType.READ, slaveDataSource);
        }
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);

        return dynamicDataSource;
    }

    @Bean(name = "sessionFactory")
    @Primary
    public LocalSessionFactoryBean localSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan(DatabaseConfiguration.PACKAGES_TO_SCAN);

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);

        return localSessionFactoryBean;
    }

    @Bean(name = "hibernateTransactionManager")
    @Primary
    public HibernateTransactionManager hibernateTransactionManager(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }

    @Bean(name = "persistenceExceptionTranslationPostProcessor")
    @Primary
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean(name = "sessionFactorySupport")
    @Primary
    public SessionFactorySupport sessionFactorySupport(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        SessionFactorySupport support = new SessionFactorySupport();
        support.setSessionFactory(sessionFactory);
        return support;
    }

}
