package com.lcy.cssm.common.web.interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mcilife.zlnsh.common.base.constant.CommonConstant;
import com.mcilife.zlnsh.common.base.constant.aliyun.AliyunBucketEnum;
import com.mcilife.zlnsh.common.base.filter.*;
import com.mcilife.zlnsh.common.base.util.DateUtils;
import com.mcilife.zlnsh.common.web.annotation.ReturnVO;
import com.mcilife.zlnsh.common.web.annotation.ReturnVOCheckV3;
import com.mcilife.zlnsh.common.web.annotation.ReturnVOField;
import com.mcilife.zlnsh.common.web.utils.CodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author : 王培
 * @create : 2018-08-22 14:57
 * 描述 ： 动态设置返回参数，最新版本用，
 */
@Aspect
@Component
public class ReturnVOAspectV3 {
    private static Logger logger = LoggerFactory.getLogger(ReturnVOAspectV3.class);
    @Autowired
    private CodeUtils codeUtils;

    @Value("${environment}")
    protected String environment;

    @Pointcut("@annotation(com.mcilife.zlnsh.common.web.annotation.ReturnVOCheckV3)")
    public void controllerInteceptor() {

    }

    @Around("controllerInteceptor()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        // 如果非Map类或者长度为0，则直接返回处理结果
        if (!(result instanceof Map) || ((Map) result).size() == 0) {
            return result;
        }
        Map<String, Object> requestMap = (Map<String, Object>) result;
        Map<String, Object> resultMap = new HashMap<>();
        // 如果注解
        ReturnVOCheckV3 returnVOCheck = ((MethodSignature) pjp.getSignature()).getMethod().getAnnotation(ReturnVOCheckV3.class);
        ReturnVO[] vos = returnVOCheck.value();

        for (ReturnVO vo : vos) {
            requestMap.forEach((key, value) -> {

                if (value == null) {
                    resultMap.put(key, "");
                    return;
                }

                List tempList = new ArrayList();
                if (value instanceof Collection) {
                    ((Collection) value).forEach((v) -> {
                        if(StringUtils.equals(v.getClass().getName(),vo.srcClass().getName())){
                            tempList.add(codeUtils.translate(setField(v, vo), v, vo));
                        }

                    });
                    if(!tempList.isEmpty()){
                        resultMap.put(key, tempList);
                    }
                } else if (value instanceof Number || value instanceof String) {
                    resultMap.put(key, value);
                } else {
                    if(StringUtils.equals(value.getClass().getName(),vo.srcClass().getName())) {
                        resultMap.put(key, codeUtils.translate(setField(value, vo), value, vo));
                    }
                }

            });
        }
        return resultMap;
    }

    private Object setField(Object value, ReturnVO vo) {
        try {
            Object tempObject = vo.targetClass().newInstance();
            for (ReturnVOField voField : vo.value()) {
                if (Objects.equals(value.getClass(), vo.srcClass())) {
                    if (StringUtils.isBlank(voField.name())) {
                        continue;
                    }
                    Field field = value.getClass().getDeclaredField(voField.name());
                    field.setAccessible(true);
                    Field temField = tempObject.getClass().getDeclaredField(StringUtils.isNotBlank(voField.alias()) ? voField.alias() : voField.name());
                    temField.setAccessible(true);
                    //如果需要转换
                    if (voField.isConversion()) {
                        //获取vo的类型
                        if (temField.getType().equals(String.class)) {
                            //获取要转化的类型
                            if (field.get(value) instanceof Integer) {
                                if (field.get(value) != null) {
                                    temField.set(tempObject, String.valueOf(field.get(value)));
                                }
                                continue;
                            }
                        }
                        if(temField.getType().equals(boolean.class)){
                            if (field.get(value) instanceof Integer) {
                                if (field.get(value) != null) {
                                    temField.set(tempObject, true);
                                }else{
                                    temField.set(tempObject, false);
                                }
                                continue;
                            }
                        }
                    }else if(StringUtils.isNotBlank(voField.index())){
                        //获取vo的类型
                        if (temField.getType().equals(String.class)) {
                            //获取要转化的类型
                            if (field.getType().equals(String.class)) {
                                if (StringUtils.isNotBlank((String)field.get(value))) {
                                    String[] split = String.valueOf(field.get(value)).split(",");
                                    if(split.length>Integer.valueOf(voField.index())){
                                        temField.set(tempObject, split[Integer.valueOf(voField.index())]);
                                    }else{
                                        //默认取index=0
                                        temField.set(tempObject, split[0]);
                                    }
                                }
                                continue;
                            }
                        }
                    } else if(temField.getAnnotation(CodeResource.class) != null){
                        if (temField.get(tempObject) != null) {
                            temField.set(tempObject, field.get(value));
                        }
                    }else if(temField.getAnnotation(InteractionTime.class) != null){
                        temField.set(tempObject, DateUtils.getInteractionTime((Date) field.get(value)));
                    }else if(temField.getAnnotation(AgeResource.class) != null){
                        if(field.get(value)!=null){
                            temField.set(tempObject, DateUtils.getAgeByBirth((Date) field.get(value)));
                        }
                    }else if(temField.getAnnotation(JsonObject.class) != null){
                        temField.set(tempObject, JSON.parse((String) field.get(value)));
                    }else if(temField.getAnnotation(JsonStringToMap.class) != null){
                        JsonStringToMap jsonStringToMap = temField.getAnnotation(JsonStringToMap.class);
                        if(jsonStringToMap.parseUrl()){
                            JSONObject jsonObject =  JSONObject.parseObject((String) field.get(value));
                            if(jsonObject.getString("url")!=null&&StringUtils.equals(CommonConstant.DEFAULT,jsonObject.getString("url"))){
                                JSONObject newJsonObject = new JSONObject();
                                newJsonObject.put("url",AliyunBucketEnum.getEnum(jsonObject.getString("bucket")).getUrl()+environment+"/"+jsonObject.getString("prefix")+jsonObject.getInteger("id"));
                                temField.set(tempObject,  newJsonObject);
                            }else{
                                temField.set(tempObject,  JSONObject.parseObject((String) field.get(value)));
                            }
                        }else{
                            temField.set(tempObject,  JSONObject.parseObject((String) field.get(value)));
                        }
                    }else if(temField.getAnnotation(NotNullToOne.class) != null){
                        if(field.get(value)!=null){
                            temField.set(tempObject, CommonConstant.NUMBER_ONE);
                            continue;
                        }
                    }else if(temField.getAnnotation(NullToZero.class) != null){
                        if(field.get(value)==null){
                            temField.set(tempObject, CommonConstant.NUMBER_ZERO);
                            continue;
                        }
                    }else if(temField.getAnnotation(StringToList.class) != null){
                        StringToList stringToList = temField.getAnnotation(StringToList.class);
                        if(field.get(value)!=null){
                            String val = (String)field.get(value);
                            temField.set(tempObject, Arrays.asList(val.split(stringToList.separator())));
                            continue;
                        }
                    }else{
                        if (field.get(value) != null && field.get(value) instanceof Date && StringUtils.isNotBlank(voField.format())) {
                            temField.set(tempObject, DateUtils.formatDate((Date) field.get(value), voField.format()));
                            continue;
                        }
                        if (field.get(value) != null && field.get(value) instanceof String && StringUtils.isNotBlank(voField.format())) {
                            String result = String.format("%.2f",Double.valueOf(String.valueOf(field.get(value))));
                            temField.set(tempObject, result);
                            continue;
                        }
                        if (field.get(value) == null && StringUtils.isNotBlank(voField.defaultValue())) {
                            temField.set(tempObject, voField.defaultValue());
                            continue;
                        }
                        if(field.get(value) != null && StringUtils.isNotBlank(voField.prefix())){
                            String var = voField.bucket().getUrl();
                            if(StringUtils.isNotBlank(voField.prefix())){
                                var = var+environment+"/"+voField.prefix();
                            }
                            var = var+field.get(value);
                            temField.set(tempObject, var);
                            continue;
                        }
                        if (field.getType().equals(Boolean.class) && field.get(value) == null) {
                            temField.set(tempObject, false);
                            continue;
                        }
                        if (field.getType().equals(String.class) && field.get(value) == null) {
                            temField.set(tempObject, "");
                            continue;
                        }
                        if (field.getType().equals(Integer.class) && field.get(value) == null) {
                            temField.set(tempObject, 0);
                            continue;
                        }
                        temField.set(tempObject, field.get(value));
                    }
                }
            }
            return tempObject;
        } catch (IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}

