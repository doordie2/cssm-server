package com.lcy.cssm.provider.monitor.mapper;

import com.lcy.cssm.support.monitor.po.TbRequestHeaderLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 请求头日志mapper
 *
 * @auther 王培
 * @create 2017-09-30 12:25
 */
@Mapper
public interface RequestHeaderLogMapper {

    void insertRequestHeaderLog(@Param("tbRequestHeaderLog") TbRequestHeaderLog tbRequestHeaderLog);
}
