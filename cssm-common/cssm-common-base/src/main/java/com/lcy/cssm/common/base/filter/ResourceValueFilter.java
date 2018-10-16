package com.lcy.cssm.common.base.filter;

import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.BeforeFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.lcy.cssm.common.base.constant.CommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;


/**
 * @author: lcy
 * @create : 2017/11/11
 * 描述：
 */
public class ResourceValueFilter {
    private static final Logger logger = LoggerFactory.getLogger(ResourceValueFilter.class);

    private ResourceValueFilter() {
        new ResourceBeforeFilter();
    }

    public static SerializeFilter[] getInstance() {
        return ThisResourceValueFilter.instance;
    }

    private static class ThisResourceValueFilter {
        private static SerializeFilter[] instance = new SerializeFilter[]{
                new ResourceBeforeFilter(), new ResourceAfterFilter()
        };
    }

    private static class ResourceAfterFilter extends AfterFilter {
        @Override
        public void writeAfter(Object object) {

        }
    }

    private static class ResourceBeforeFilter extends BeforeFilter {
        @Override
        public void writeBefore(Object object) {
            if (object != null) {
                for (Field f : object.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    try {
                        UrlResource testJson;
                        if ((testJson = f.getAnnotation(UrlResource.class)) != null) {
                            String src = (String) f.get(object);
                            if (src != null) {
                                boolean filtered=false;
                                for (String url : CommonConstant.URL_FILTER) {
                                    if (src.startsWith(url)) {
                                        filtered=true;
                                        break;
                                    }
                                }
                                // 如果包含有逗号直接分割，needSplit是强制需要分割，都会转换成数组的形式返回
                                if (StringUtils.contains(src, testJson.split()) || testJson.needSplit()) {
                                    String[] images = StringUtils.split(src, testJson.split());
                                    String[] formatImages = new String[images.length];
                                    for (int i = 0; i < images.length; i++) {
                                        formatImages[i] = testJson.url().getUrl() + images[i];
                                    }
                                    if(testJson.front()){
                                        writeKeyValue(f.getName(), formatImages[0]);
                                        f.set(object, null);
                                    }else{
                                        writeKeyValue(f.getName(), formatImages);
                                    }
                                    f.set(object, null);
                                } else {
                                    if (StringUtils.isNotBlank(src)) {
                                        if(!filtered){
                                            src=testJson.url().getUrl() + src;
                                        }
                                        writeKeyValue(f.getName(), src);
                                        f.set(object, null);
                                    }
                                }
                            }
                        }


                    } catch (Exception e) {
                        logger.warn(e.getMessage());
                    }
                }
            }
        }
    }
}
