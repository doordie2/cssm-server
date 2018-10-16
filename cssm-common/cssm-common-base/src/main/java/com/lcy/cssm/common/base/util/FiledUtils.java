package com.lcy.cssm.common.base.util;

import com.google.common.collect.Lists;
import com.lcy.cssm.common.base.constant.CommonConstant;
import com.lcy.cssm.common.base.constant.aliyun.AliyunBucketEnum;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author : lcy
 * @create : 2017-10-26 16:54
 * 描述 ：bean工具类
 */
public class FiledUtils {
    /**
     * 查询类中的空字段
     *
     * @param source 要查询的类
     * @return 空字段数据
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 将数据转化成用逗号分隔的字符串
     */
    public static <T, R> String getCommaString(List<T> list, Function<T, R> f) {
        if (list == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (T i : list) {
            sb.append(f.apply(i));
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 将数据转化成用逗号分隔的字符串
     */
    public static <T, R> String getCommaStringSet(List<T> list, Function<T, R> f) {
        if (list == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Set set = new HashSet();
        for (T i : list) {
            set.add(f.apply(i));
        }
        for (Object i : set) {
            sb.append(i);
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 将数据特定参数提取成set
     */
    public static <T, R> Set getCommaSet(List<T> list, Function<T, R> f) {
        if (list == null) {
            return new HashSet();
        }
        Set set = new HashSet();
        for (T i : list) {
            set.add(f.apply(i));
        }
        return set;
    }

    /**
     * 合并数据
     *
     * @param target 目标数组
     * @param source 资源数据，用于提取特定参数塞到目标数组的对象里
     * @param o      因为java类型擦除的缘故，如果不想有空指针需要将source的类型传进来
     */
    public static <T, R, B, A> List<T> concatList(List<T> target, Function<? super T, ? extends A> targetKey,
                                                  List<R> source, Function<? super R, ? extends B> sourceKey, Class<R> o,
                                                  Transform<R, T> toTransform) {
        Map objectRMap = source.stream().collect(groupingBy(sourceKey));
        target.forEach((tar) -> {
            if (toTransform != null) {
                List<R> rs = (List<R>) objectRMap.get(targetKey.apply(tar));
                if (rs != null && rs.size() > 0) {
                    try {
                        toTransform.transform(rs.get(0), tar);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (o != null) {
                        try {
                            toTransform.transform((R) o.newInstance(), tar);
                        } catch (InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
        return target;
    }

    /**
     * 合并数据
     *
     * @param target 目标数组
     * @param source 资源数据，用于提取特定参数塞到目标数组的对象里
     */
    public static <T, R, B, A> List<T> concatList(List<T> target, Function<? super T, ? extends A> targetKey,
                                                  List<R> source, Function<? super R, ? extends B> sourceKey,
                                                  Transform<R, T> toTransform) {

        return concatList(target, targetKey, source, sourceKey, null, toTransform);
    }

    /**
     * 数组拷贝
     */
    public static <T, O> List<O> copyArrayList(List<T> source, Class<O> c, Transform<T, O> toTransform) {
        List list = new ArrayList();
        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<>();
        }
        for (T t : source) {
            Object o;
            try {
                o = c.newInstance();
                BeanUtils.copyProperties(t, o);
                if (toTransform != null) {
                    toTransform.transform(t, (O) o);
                }
                list.add(o);
            } catch (InstantiationException | IllegalAccessException | ParseException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    public static <O> O mapToBean(Map<String, Object> map,Class<O> c) throws IllegalAccessException, InstantiationException {
        O o = c.newInstance();
        BeanMap beanMap = BeanMap.create(o);
        beanMap.putAll(map);
        return o;
    }



    public static <T, O> List<O> copyArrayList(List<T> source, Class<O> c) {
        return FiledUtils.copyArrayList(source, c, null);
    }

    public static <T, O> O copyProperties(T source, Class<O> c, Transform<T, O> toTransform) {
        Object o = null;
        try {
            o = c.newInstance();
            if (source == null) {
                return (O) o;
            }
            BeanUtils.copyProperties(source, o);
            if (toTransform != null) {
                toTransform.transform(source, (O) o);
            }
        } catch (InstantiationException | IllegalAccessException | ParseException e) {
            e.printStackTrace();
        }
        return (O) o;
    }

    public static <T, O> O copyNPEProperties(T source, Class<O> c, Transform<T, O> toTransform) {
        Object o = null;
        try {
            o = c.newInstance();
            if (source == null) {
                return null;
            }
            BeanUtils.copyProperties(source, o);
            if (toTransform != null) {
                toTransform.transform(source, (O) o);
            }
        } catch (ParseException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return (O) o;
    }

    public static <T, O> O copyNPEProperties(T source, Class<O> c) {
        return FiledUtils.copyNPEProperties(source, c, null);
    }

    public static <T, O> O copyProperties(T source, Class<O> c) {
        return FiledUtils.copyProperties(source, c, null);
    }

    public static String concatUrl(String soruce, AliyunBucketEnum aliyunBucketEnum, String split) {
        if (StringUtils.isBlank(soruce) || aliyunBucketEnum == null) {
            return CommonConstant.BLANK;
        }
        String[] soruceArray = soruce.split(split);
        StringBuffer resultStr = new StringBuffer();
        if (soruceArray.length > 0) {
            for (String src : soruceArray) {
                if (!src.startsWith("http")) {
                    if (src.startsWith("/")) {
                        resultStr.append("https://img.iplusmed.com" + src + split);
                    } else {
                        resultStr.append(aliyunBucketEnum.getUrl() + src + split);
                    }
                } else {
                    resultStr.append(src + split);
                }
            }
            resultStr.deleteCharAt(resultStr.length() - 1);
        }
        return resultStr.toString();
    }

    public static String subUrl(String soruce, AliyunBucketEnum aliyunBucketEnum, String split) {
        if (StringUtils.isBlank(soruce) || aliyunBucketEnum == null) {
            return CommonConstant.BLANK;
        }
        String[] soruceArray = soruce.split(split);
        StringBuffer resultStr = new StringBuffer();
        if (soruceArray.length > 0) {
            for (String src : soruceArray) {
                if (src.contains(aliyunBucketEnum.getUrl())) {
                    resultStr.append(src.replace(aliyunBucketEnum.getUrl(), CommonConstant.BLANK) + split);
                } else if (src.contains("https://img.iplusmed.com")) {
                    resultStr.append(src.replace("https://img.iplusmed.com", CommonConstant.BLANK) + split);
                } else {
                    resultStr.append(src + split);
                }
            }
            resultStr.deleteCharAt(resultStr.length() - 1);
        }
        return resultStr.toString();
    }


    /**
     * 将数据转化成List
     *
     * @param list
     * @param f
     * @param <T>
     * @param <R>
     * @return
     * @author : 杨兴梅
     */
    public static <T, R> List<R> getCommaList(List<T> list, Function<T, R> f) {
        if (list == null) {
            return new ArrayList<>();
        }
        List<R> lists = Lists.newArrayList();
        for (T i : list) {
            lists.add(f.apply(i));
        }
        return lists;
    }


    /**
     * 将指定格式日期的数据转化成List
     * @param list
     * @param f
     * @param format
     * @param <T>
     * @param <R>
     * @return
     * @author : 杨兴梅
     */
    public static <T, R> List<R> getCommaDateList(List<T> list, Function<T, R> f,String format) {
        if (list == null) {
            return new ArrayList<>();
        }
        List<R> lists = Lists.newArrayList();
        for (T i : list) {
            R r=f.apply(i);
            if(r instanceof Date){
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
                simpleDateFormat.format(r);
            }
            lists.add(r);
        }
        return lists;
    }
}
