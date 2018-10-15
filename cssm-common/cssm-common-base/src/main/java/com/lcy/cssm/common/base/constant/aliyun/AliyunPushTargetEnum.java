package com.lcy.cssm.common.base.constant.aliyun;

import org.apache.commons.lang3.StringUtils;

/**
 * 推送target类型
 * @author 王培
 * @create 2017-08-03 10:45
 **/
public enum AliyunPushTargetEnum {

	PUSH_TARGET_DEVICE("设备", "DEVICE"), PUSH_TARGET_ALL("全部", "ALL");

	/** 枚举值 */
	private String value;

	/** 描述 */
	private String desc;

	private AliyunPushTargetEnum(String desc, String value) {
		this.value = value;
		this.desc = desc;
	}


	public String getValue() {
		return value;
	}

	public static AliyunPushTargetEnum getEnum(String value) {
		AliyunPushTargetEnum resultEnum = null;
		AliyunPushTargetEnum[] enumAry = AliyunPushTargetEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (StringUtils.equals(enumAry[i].getValue(),value)) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

}
