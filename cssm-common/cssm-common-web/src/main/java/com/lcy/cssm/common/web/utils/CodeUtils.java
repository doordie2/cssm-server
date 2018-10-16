package com.lcy.cssm.common.web.utils;

import com.lcy.cssm.common.base.filter.CodeResource;
import com.lcy.cssm.common.base.po.BaseDictionaryPO;
import com.lcy.cssm.common.web.annotation.ReturnVO;
import com.lcy.cssm.common.web.annotation.ReturnVOField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * @author : lcy
 * @create : 2017-11-12 15:06
 * 描述 ：
 */
@Service
public class CodeUtils {

    public Object translate(Object object, Object v, ReturnVO vo) {
        if (object == null) {
            return null;
        }
        for (ReturnVOField n : vo.value()) {
            try {
                CodeResource codeResource;
                Field f = object.getClass().getDeclaredField(StringUtils.isNotBlank(n.alias()) ? n.alias() : n.name());
                if ((codeResource = f.getAnnotation(CodeResource.class)) != null) {
                    f.setAccessible(true);
                    try {
                        Field code = object.getClass().getDeclaredField(StringUtils.isNotBlank(n.alias()) ? n.alias() : n.name());
                        code.setAccessible(true);
                        Field vf = v.getClass().getDeclaredField(n.name());
                        vf.setAccessible(true);

                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }


            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return object;
    }
}
