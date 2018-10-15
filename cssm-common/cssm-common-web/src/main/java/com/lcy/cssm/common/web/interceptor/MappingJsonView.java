package com.lcy.cssm.common.web.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.lcy.cssm.common.base.exceptions.*;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12-12-30
 * Time: 下午6:05
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class MappingJsonView extends AbstractView implements HandlerExceptionResolver {

    Logger slflogger = LoggerFactory
            .getLogger(MappingJsonView.class);
    /**
     * Default content type. Overridable as bean property.
     */
    public static final String DEFAULT_CONTENT_TYPE = "application/json";

    private boolean prefixJson = false;

    private Set<String> modelKeys;

    private boolean disableCaching = true;

    public MappingJsonView() {
        setContentType(DEFAULT_CONTENT_TYPE);
        setExposePathVariables(false);
    }

    /**
     * Indicates whether the JSON output by this view should be prefixed with <tt>"{} && "</tt>.
     * Default is false.
     * <p>Prefixing the JSON string in this manner is used to help prevent JSON Hijacking.
     * The prefix renders the string syntactically invalid as a script so that it cannot be hijacked.
     * This prefix does not affect the evaluation of JSON, but if JSON validation is performed
     * on the string, the prefix would need to be ignored.
     */
    public void setPrefixJson(boolean prefixJson) {
        this.prefixJson = prefixJson;
    }

    /**
     * Set the attribute in the model that should be rendered by this view.
     * When set, all other model attributes will be ignored.
     */
    public void setModelKey(String modelKey) {
        this.modelKeys = Collections.singleton(modelKey);
    }

    /**
     * Set the attributes in the model that should be rendered by this view.
     * When set, all other model attributes will be ignored.
     */
    public void setModelKeys(Set<String> modelKeys) {
        this.modelKeys = modelKeys;
    }

    /**
     * Return the attributes in the model that should be rendered by this view.
     */
    public Set<String> getModelKeys() {
        return this.modelKeys;
    }

    /**
     * Set the attributes in the model that should be rendered by this view.
     * When set, all other model attributes will be ignored.
     *
     * @deprecated use {@link #setModelKeys(Set)} instead
     */
    @Deprecated
    public void setRenderedAttributes(Set<String> renderedAttributes) {
        this.modelKeys = renderedAttributes;
    }

    /**
     * Return the attributes in the model that should be rendered by this view.
     *
     * @deprecated use {@link #getModelKeys()} instead
     */
    @Deprecated
    public Set<String> getRenderedAttributes() {
        return this.modelKeys;
    }

    /**
     * Disables caching of the generated JSON.
     * <p>Default is {@code true}, which will prevent the client from caching the generated JSON.
     */
    public void setDisableCaching(boolean disableCaching) {
        this.disableCaching = disableCaching;
    }

    @Override
    protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(getContentType());
        response.setCharacterEncoding("utf-8");
        if (this.disableCaching) {
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
            response.addDateHeader("Expires", 1L);
        }
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        Object value = filterModel(model);
        response.getOutputStream().write(JSONObject.toJSONString(value).getBytes("utf-8"));
    }

    /**
     * Filters out undesired attributes from the given model.
     * The return value can be either another {@link Map} or a single value object.
     * <p>The default implementation removes {@link BindingResult} instances and entries
     * not included in the {@link #setRenderedAttributes renderedAttributes} property.
     *
     * @param model the model, as passed on to {@link #renderMergedOutputModel}
     * @return the object to be rendered
     */
    protected Object filterModel(Map<String, Object> model) {
        Map<String, Object> result = new HashMap<String, Object>(model.size());
        Set<String> renderedAttributes = (!CollectionUtils.isEmpty(this.modelKeys) ? this.modelKeys
                : model.keySet());
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            if (!(entry.getValue() instanceof BindingResult)
                    && renderedAttributes.contains(entry.getKey())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result.size() == 1 ? result.values().iterator().next() : result;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {

        ModelAndView mv = new ModelAndView();
        CommonResult commonResult = new CommonResult();
        response.setStatus(200);
        commonResult.setSuccess(false);
        //所有异常都返回200，在前台解析
        if (ex instanceof McException) {
            ResultCode resultCode = ((McException) ex).getResultCode();
            Object[] params = ((McException) ex).getParams();
            String desc = resultCode.getDesc();
            if (Objects.nonNull(params)){
                for (Object ob : params) {
                    desc = desc.replaceFirst("\\{}", ob.toString());
                }
            }
            commonResult.setResultCode(resultCode.getCode());
            commonResult.setResultDesc(desc);
            slflogger.info("捕获应用异常 request={} exception={} uuid={}", request.getServletPath(), resultCode.getType().getCode() + resultCode.getCode() + "|" + resultCode.getDesc(),MDC.get("threadUuid"));
        } else if (ex instanceof McFeignException) {
            commonResult.setResultCode(((McFeignException) ex).getErrorCode());
            commonResult.setResultDesc(((McFeignException) ex).getErrorDesc());
            slflogger.info("捕获应用异常 request={} exception={} uuid={}", request.getServletPath(), commonResult.getResultCode() + "|" + commonResult.getResultDesc(),MDC.get("threadUuid"));
        } else {
            commonResult.setResultCode(CommonResultCode.COMMON_FAIL_ERROR.getCode());
            commonResult.setResultDesc(CommonResultCode.COMMON_FAIL_ERROR.getDesc());
            slflogger.error("未知异常 request=" + request.getServletPath(), ex);
        }
        mv.addObject(commonResult);
        mv.setView(this);
        return mv;
    }
}
