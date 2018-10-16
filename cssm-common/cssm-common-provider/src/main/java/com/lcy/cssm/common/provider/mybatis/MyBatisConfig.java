package com.lcy.cssm.common.provider.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatis配置类
 *
 * @author 王培
 * @create 2017-04-26 21:15
 **/
@Configuration
@MapperScan(basePackages = "com.lcy.cssm.*")
public class MyBatisConfig {

    //直接写在one,two下面不能被解析，druid（1.1.8版本）解析报错，不知道为什么，这个属性必须放在这里，然后通过@value解析后用set的方式设置到DataSource里面去
    @Value("#{'${spring.datasource.druid.connection-init-sqls}'.split(',')}")
    private List<String> connectionInitSqls;



    /**
     * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
     */
    @Primary
    @Bean
    @ConfigurationProperties(value = "spring.datasource.druid.one",ignoreInvalidFields = true)
    public DruidDataSource dataSourceOne() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setConnectionInitSqls(connectionInitSqls);
        return dataSource;
    }


    @Bean
    @ConfigurationProperties(value = "spring.datasource.druid.two",ignoreInvalidFields = true)
    public DruidDataSource dataSourceTwo() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setConnectionInitSqls(connectionInitSqls);
        return dataSource;
    }




    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("dataSourceOne") DataSource defaultDataSource,
                                        @Qualifier("dataSourceTwo") DataSource proDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.defaultDataSource, defaultDataSource);
        targetDataSources.put(DatabaseType.proDataSource, proDataSource);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(defaultDataSource);// 默认的datasource设置为myTestDbDataSource

        return dataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setPlugins(new Interceptor[]{new MyBatisIntercept()});
        fb.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        fb.setTypeHandlers(new TypeHandler[]{new BooleanTypeHandler()});
        fb.setConfiguration(new MyConfiguration());
        return fb.getObject();
    }
    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
