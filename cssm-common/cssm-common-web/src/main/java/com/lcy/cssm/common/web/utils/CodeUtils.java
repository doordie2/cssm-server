package com.lcy.cssm.common.web.utils;

import com.mcilife.zlnsh.api.base.facade.v3.DictionaryFacade;
import com.mcilife.zlnsh.common.base.filter.CodeResource;
import com.mcilife.zlnsh.common.base.po.BaseDictionaryPO;
import com.mcilife.zlnsh.common.web.annotation.ReturnVO;
import com.mcilife.zlnsh.common.web.annotation.ReturnVOField;
import com.mcilife.zlnsh.support.base.dto.v3.DictionaryDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * @author : 赵天增
 * @create : 2017-11-12 15:06
 * 描述 ：
 */
@Service
public class CodeUtils {
    @Autowired
    private DictionaryFacade dictionaryFacade;

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
                        switch (codeResource.type()) {
                            case "dictionary":
                                DictionaryDTO dictionaryDTO = dictionaryFacade.getDictionaryByRootAndParentAndChild(codeResource.root(),codeResource.parent(),String.valueOf(vf.get(v)));
                                BaseDictionaryPO baseDictionaryPO = new BaseDictionaryPO();
                                if (dictionaryDTO != null) {
                                    if(StringUtils.equals(codeResource.valueType(),"value")){
                                        code.set(object, dictionaryDTO.getValue());
                                    }else{
                                        baseDictionaryPO.setKey(dictionaryDTO.getChild());
                                        baseDictionaryPO.setValue(dictionaryDTO.getValue());
                                        code.set(object, baseDictionaryPO);
                                    }
                                }else{
                                    code.set(object, null);
                                }

                            default:
                                break;
                        }
                    } catch (NoSuchFieldException | IllegalAccessException e) {
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
