package com.lcy.cssm.common.provider.mybatis;

import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 定制化mybatis配置,Configuration里面的每个参数都可以重新设置
 *
 * @author 王培
 * @create 2017-05-02 21:33
 **/
@Repository
public class MyConfiguration extends Configuration{
    public MyConfiguration(){
        this.mapUnderscoreToCamelCase = true;
        this.logImpl = Log4jImpl.class;
        this.lazyLoadingEnabled = true;
        this.cacheEnabled = true;
        this.multipleResultSetsEnabled = true;
        this.useColumnLabel = true;
        this.useGeneratedKeys = false;
        this.defaultExecutorType = ExecutorType.SIMPLE;
        this.defaultStatementTimeout = 100;
        this.safeRowBoundsEnabled = false;
        this.localCacheScope = LocalCacheScope.SESSION;
        this.jdbcTypeForNull = JdbcType.OTHER;
        this.lazyLoadTriggerMethods = new HashSet(Arrays.asList(new String[]{"equals", "clone", "hashCode", "toString"}));
    }
}
