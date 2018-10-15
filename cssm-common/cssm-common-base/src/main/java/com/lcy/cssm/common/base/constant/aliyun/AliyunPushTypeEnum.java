package com.lcy.cssm.common.base.constant.aliyun;

import org.apache.commons.lang3.StringUtils;

/**
 * 推送类别
 * @author 王培
 * @create 2017-08-03 10:45
 **/
public enum AliyunPushTypeEnum {

	PUSH_TYPE_NOTICE("通知", "NOTICE"), PUSH_TYPE_MESSAGE("消息", "MESSAGE");

	/** 枚举值 */
	private String value;

	/** 描述 */
	private String desc;

	private AliyunPushTypeEnum(String desc, String value) {
		this.value = value;
		this.desc = desc;
	}


	public String getValue() {
		return value;
	}

	public static AliyunPushTypeEnum getEnum(String value) {
		AliyunPushTypeEnum resultEnum = null;
		AliyunPushTypeEnum[] enumAry = AliyunPushTypeEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (StringUtils.equals(enumAry[i].getValue(),value)) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

}
