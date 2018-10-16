package com.lcy.cssm.provider.user.mapper;

import com.lcy.cssm.support.user.po.TbUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: lcy
 * @Date: 2018/5/10 17:43
 * @Description: 用户信息的mapper
 */
@Mapper
public interface UserInfoMapper {

    @Select("select * from tb_user limit 1")
    TbUserInfo getUserInfo();
}
