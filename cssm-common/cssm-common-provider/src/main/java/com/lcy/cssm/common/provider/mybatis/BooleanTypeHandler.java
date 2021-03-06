package com.lcy.cssm.common.provider.mybatis;


import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 自定义Boolean类型转换器
 * @author lcy
 * @create 2016-12-01 18:45
 **/
@SuppressWarnings("rawtypes")
public class BooleanTypeHandler implements TypeHandler {

	/**
	 * 转换器
	 * @param arg0
	 * @param arg1
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Object getResult(ResultSet arg0, int arg1) throws SQLException {
		int num = arg0.getInt(arg1);
		Boolean rt = Boolean.FALSE;
		if (num == 1){
			rt = Boolean.TRUE;
		}
		return rt; 
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.CallableStatement, int)
	 */
	@Override
	public Object getResult(CallableStatement arg0, int arg1)
			throws SQLException {
		Boolean b = arg0.getBoolean(arg1);
		return b == true ? 1 : 0;
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.TypeHandler#setParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
	 */
	@Override
	public void setParameter(PreparedStatement arg0, int arg1, Object arg2,
			JdbcType arg3) throws SQLException {
		Boolean b = (Boolean) arg2;
		int value = (Boolean) b == true ? 1 : 0;
		arg0.setInt(arg1, value);
	}

	@Override
	public Object getResult(ResultSet arg0, String arg1) throws SQLException {
		int num = arg0.getInt(arg1);
		Boolean rt = Boolean.FALSE;
		if (num == 1){
			rt = Boolean.TRUE;
		}
		return rt; 
	}

}
