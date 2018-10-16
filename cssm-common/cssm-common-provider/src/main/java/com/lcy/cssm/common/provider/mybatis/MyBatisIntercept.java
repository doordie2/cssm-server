package com.lcy.cssm.common.provider.mybatis;


import com.alibaba.fastjson.JSONObject;
import com.lcy.cssm.common.base.constant.UserTypeEnum;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.*;

/**
 * @author : lcy
 * @create : 2018-01-08 10:29
 * 描述 ：mybatis 操作拦截器
 * sql直接拷贝hhttp://phncz310.iteye.com/blog/2251712
 */
@Intercepts(
        {
                @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        }
)
public class MyBatisIntercept extends HandlerInterceptorAdapter implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisIntercept.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getHeader("adminid") != null) {
            MDC.put("operationId", String.valueOf(request.getHeader("adminid")));
            MDC.put("operationType", UserTypeEnum.ADMIN.getDesc());
        }
        if (request.getHeader("userid") != null) {
            MDC.put("operationId", String.valueOf(request.getHeader("userid")));
            MDC.put("operationType", UserTypeEnum.USER.getDesc());
        }
        return true;
    }


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        // 传入的对象
        Object obj = args[1];
//        HashMap param = (HashMap<String, Object>) obj;
//        param.forEach((k, v) -> {
//            if (v instanceof Map) {
//                Map tempMap = new HashMap();
//                ((Map) v).forEach((pV, pK) -> tempMap.put(camelToUnderline((String) pV), pK));
//                param.replace(k,tempMap);
//            }
//        });

        MappedStatement mappedStatement = (MappedStatement) args[0];
        // 记录执行结果
        Object resultObj = invocation.proceed();
        String name = mappedStatement.getSqlCommandType().name().toUpperCase();
        //执行的sql
        BoundSql boundSql = mappedStatement.getBoundSql(obj);
        Configuration configuration = mappedStatement.getConfiguration();
        String sql;
        try {
            sql = showSql(configuration, boundSql);
        }catch (Exception e){
            sql = "SQL分析出错";
            logger.warn("SQL分析出错 {}",JSONObject.toJSONString(resultObj));
            return resultObj;
        }
        if (name.startsWith("INSERT")) {
            logger.info("{}||{}", sql, sql.substring(sql.toUpperCase().indexOf("INTO") + 4, sql.toUpperCase().indexOf("(")).trim());
        }
        if (name.startsWith("UPDATE")) {
            // 找where和limit中的参数就是条件
            String keywords = sql.substring(sql.toUpperCase().lastIndexOf("WHERE")).toUpperCase();
            if (keywords.contains("LIMIT")) {
                keywords = keywords.substring("WHERE".length(), sql.toUpperCase().lastIndexOf("LIMIT"));
            }
            StringBuilder sb = new StringBuilder();
            if(keywords.contains("AND")){
                for (String key : keywords.split("AND")) {
                    if(key.split("=").length>1){
                        sb.append(key.split("=")[1].trim()).append(",");
                    }

                }
            }
            logger.info("{}||{}||{}", sql, sql.substring(name.length(), sql.toUpperCase().lastIndexOf("SET")).trim(), sb.toString());
        }
        if (name.startsWith("DELETE")) {
            String keywords = sql.substring(sql.toUpperCase().lastIndexOf("WHERE")).toUpperCase();
            if (keywords.contains("LIMIT")) {
                keywords = keywords.substring("WHERE".length(), sql.toUpperCase().lastIndexOf("LIMIT"));
            }
            StringBuilder sb = new StringBuilder();
            for (String key : keywords.split("AND")) {
                if(key.split("=").length>1&&key.split("=")[1]!=null){
                    sb.append(key.split("=")[1].trim()).append(",");
                }
            }
            logger.info("{}||{}||{}", sql, sql.substring(sql.toUpperCase().lastIndexOf("FROM"), sql.toUpperCase().lastIndexOf("WHERE")).trim(), sb.toString());
        }
        if (name.startsWith("SELECT")) {
            logger.info("查询结果 -> {} , {}", sql, JSONObject.toJSONString(resultObj));
        }
        return resultObj;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return value;
    }

    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else {
                        Map map = (Map) metaObject;
                        sql = sql.replaceFirst("\\?", getParameterValue(map.get(propertyName)));
                    }
                }
            }
        }
        return sql;
    }
    private String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
