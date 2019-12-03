package com.topshow.configuration;

import com.topshow.configuration.RootConfig;
import com.topshow.jdbc.PackagesSqlSessionFactoryBean;
import java.io.IOException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Configuration
@ComponentScan(basePackages = { "com.topshow.*" }, excludeFilters = {
        @Filter(type = FilterType.ANNOTATION, classes = { Controller.class,
                ControllerAdvice.class }) })
@PropertySource({ "classpath:jdbc.properties", "classpath:merchant.properties" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@MapperScan(basePackages = { "com.topshow.mapper" })
public class RootConfig {
    private static Logger logger = LogManager.getLogger(RootConfig.class.getName());

    @Autowired
    @Qualifier("mysqlTestDataSource")
    private BasicDataSource mysqlTestDataSource;

    @Autowired
    @Qualifier("mysqlConfigDataSource")
    private BasicDataSource mysqlConfigDataSource;

    @Autowired
    @Qualifier("mysqlDeveDataSource")
    private BasicDataSource mysqlDeveDataSource;

    @Autowired
    @Qualifier("mysqlDataSource")
    private BasicDataSource mysqlDataSource;

    @Bean(destroyMethod = "close", name = { "mysqlConfigDataSource" })
    public BasicDataSource mysqlConfigDataSource(@Value("${jdbc.config.driver}") String driverClassName,
            @Value("${jdbc.config.url}") String url, @Value("${jdbc.config.user}") String user,
            @Value("${jdbc.config.password}") String password) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driverClassName);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }

    @Bean(destroyMethod = "close", name = { "mysqlTestDataSource" })
    public BasicDataSource mysqlTestDataSource(@Value("${jdbc.mysql.test.driver}") String driverClassName,
            @Value("${jdbc.mysql.test.url}") String url, @Value("${jdbc.mysql.test.user}") String user,
            @Value("${jdbc.mysql.test.password}") String password) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driverClassName);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }

    @Bean(destroyMethod = "close", name = { "mysqlDeveDataSource" })
    public BasicDataSource mysqlDeveDataSource(@Value("${jdbc.mysql.deve.driver}") String driverClassName,
            @Value("${jdbc.mysql.deve.url}") String url, @Value("${jdbc.mysql.deve.user}") String user,
            @Value("${jdbc.mysql.deve.password}") String password) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driverClassName);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }

    @Bean(destroyMethod = "close", name = { "mysqlDataSource" })
    public BasicDataSource mysqlDataSource(@Value("${jdbc.mysql.driver}") String driverClassName,
            @Value("${jdbc.mysql.url}") String url, @Value("${jdbc.mysql.user}") String user,
            @Value("${jdbc.mysql.password}") String password) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driverClassName);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }


    @Bean(name = { "sqlSessionFactory" })
    public SqlSessionFactory sqlSessionFactoryBean() {
        try {
            PackagesSqlSessionFactoryBean sqlSessionFactoryBean = new PackagesSqlSessionFactoryBean();
            sqlSessionFactoryBean
                    .setConfigLocation((new DefaultResourceLoader()).getResource("classpath:/mybatis-config.xml"));
            sqlSessionFactoryBean.setDataSource(this.mysqlTestDataSource);
            sqlSessionFactoryBean.setTypeAliasesPackage("com.topshow.**.entity");
            PathMatchingResourcePatternResolver mapperLocations = new PathMatchingResourcePatternResolver();
            Resource[] resources = mapperLocations.getResources("classpath:/com/topshow/*/mapper/*Mapper.xml");
            sqlSessionFactoryBean.setMapperLocations(resources);
            logger.info("mybatis sqlSessionFactoryBean success create ");
            return sqlSessionFactoryBean.getObject();
        } catch (IOException e) {
            logger.error("mybatis resolver mapper*xml is error");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            logger.error("mybatis sqlSessionFactoryBean create error");
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(mysqlTestDataSource);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
