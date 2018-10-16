package com.lcy.cssm.common.base.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * 邮件模板状态
 * @author lcy
 * @create 2017-08-03 10:45
 **/
public enum MailTemplateEnum {


	TEST_MAIL_TEMPLATE("testMailTemplate","testMailTemplate.html","2");

	/** 枚举值 */
	private String value;

	/** 描述 */
	private String desc;

	private String type;

	private MailTemplateEnum(String value, String desc, String type) {
		this.value = value;
		this.desc = desc;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public String getValue() {
		return value;
	}

	public static MailTemplateEnum getEnum(String type) {
		MailTemplateEnum resultEnum = null;
		MailTemplateEnum[] enumAry = MailTemplateEnum.values();
		for (int i = 0; i < enumAry.length; i++) {
			if (StringUtils.equals(enumAry[i].getType(),type)) {
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}

}
