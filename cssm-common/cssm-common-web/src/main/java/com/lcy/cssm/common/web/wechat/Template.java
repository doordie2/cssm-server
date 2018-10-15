package com.lcy.cssm.common.web.wechat;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Author: 王培
 * @Date: 2018/6/25 09:49
 * @Description:
 */
public class Template {


    // 消息接收方
    private String toUser;
    // 模板id
    private String templateId;
    // 模板消息详情链接
    private String url;
    // 参数列表
    private List<TemplateParam> data;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TemplateParam> getData() {
        return data;
    }

    public void setData(List<TemplateParam> data) {
        this.data = data;
    }

    public String toJSON() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        buffer.append(String.format("\"touser\":\"%s\"", this.toUser)).append(",");
        buffer.append(String.format("\"template_id\":\"%s\"", this.templateId)).append(",");
        buffer.append(String.format("\"url\":\"%s\"", this.url)).append(",");
        buffer.append("\"data\":{");
        TemplateParam param = null;
        for (int i = 0; i < this.data.size(); i++) {
            param = data.get(i);
            // 判断是否追加逗号
            buffer.append("\""+param.getName() + "\":" + "{\"value\":\"" + param.getValue() + "\"");
            if (StringUtils.isNotBlank(param.getColor())) {
                buffer.append(",\"color\":" + "\"" + param.getColor() + "\"");
            }
            if (i < this.data.size() - 1) {
                buffer.append("},");
            } else {
                buffer.append("}");
            }
        }
        buffer.append("}");
        buffer.append("}");
        return buffer.toString();

    }
}
