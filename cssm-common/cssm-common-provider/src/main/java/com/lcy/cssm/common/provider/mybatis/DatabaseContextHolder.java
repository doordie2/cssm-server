package com.lcy.cssm.common.provider.mybatis;

/**
 * 指定数据源
 *
 * @author lcy
 * @create 2017-04-26 21:15
 **/
public class DatabaseContextHolder {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type){
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType(){
        return contextHolder.get();
    }
}
