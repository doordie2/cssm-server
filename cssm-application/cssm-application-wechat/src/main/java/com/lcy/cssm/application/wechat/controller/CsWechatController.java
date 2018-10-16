package com.lcy.cssm.application.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.lcy.cssm.application.wechat.wechat.MessageUtil;
import com.lcy.cssm.application.wechat.wechat.ReplyTextMessage;
import com.lcy.cssm.application.wechat.wechat.SignUtil;
import com.lcy.cssm.common.web.annotation.PageSessionCheck;
import com.lcy.cssm.common.web.base.BaseController;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Author: lcy
 * @Date: 2018/5/23 10:25
 * @Description:
 */
@Controller
public class CsWechatController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CsWechatController.class);

    @RequestMapping(value = "/wechat/dispatchcs", method = {RequestMethod.POST, RequestMethod.GET})
    @PageSessionCheck
    public void dispatch(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam("signature") String signature,
                         @RequestParam("timestamp") String timestamp,
                         @RequestParam("nonce") String nonce,
                         @RequestParam(value = "echostr", required = false) String echostr) throws IOException {
        if (StringUtils.isNotBlank(echostr)) {
            PrintWriter out = response.getWriter();
            out.write(echostr);
            out.flush();
            out.close();
        }
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        logger.info("接收到微信发送过来的信息");
        try {
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            logger.info("关注返回的参数={}", JSONObject.toJSONString(requestMap));
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            String creatTime = requestMap.get("CreateTime");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            // text内容
            String content = requestMap.get("Content");
            // event内容
            String event = requestMap.get("Event");
            String eventKey = requestMap.get("EventKey");
            String returnXml = "";
            logger.info("类型={},事件={},内容={}", msgType, event, content);
            StringBuffer replyMsg;
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                switch (msgType) {
                    case "event":
                        switch (event) {
                            case "subscribe":
                                logger.info("关注");
                                // 扫描二维码事件
                                if(StringUtils.equals("qrscene_yunyingmoment",eventKey)){
                                    replyMsg = new StringBuffer();
                                    replyMsg.append("<a href = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxea8ee9dfff485905&redirect_uri=https%3A%2F%2Fgateway.mcilife.com%2Fwechat%2Fauth%3Ftype%3DMOMENT_INDEX&response_type=code&scope=snsapi_userinfo&state='>").append("110万老友欢迎您，点击本文字链接，开始一起交流吧。").append("</a>");
                                    returnXml = getReplyTextMessage(replyMsg.toString(), fromUserName, toUserName);
                                }
                                break;
                            case "SCAN":
                            // 扫描二维码事件
                                if(StringUtils.equals("yunyingmoment",eventKey)){
                                    replyMsg = new StringBuffer();
                                    replyMsg.append("<a href = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxea8ee9dfff485905&redirect_uri=https%3A%2F%2Fgateway.mcilife.com%2Fwechat%2Fauth%3Ftype%3DMOMENT_INDEX&response_type=code&scope=snsapi_userinfo&state='>").append("110万老友欢迎您，点击本文字链接，开始一起交流吧。").append("</a>");
                                    returnXml = getReplyTextMessage(replyMsg.toString(), fromUserName, toUserName);
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
            logger.info("生成的推送信息 ------ {}", returnXml);
            PrintWriter out = response.getWriter();
            out.write(returnXml);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 回复文本消息
     *
     * @param content      内容
     * @param fromUserName 发送方帐号（open_id）
     * @param toUserName   公众帐号
     * @return
     */
    private String getReplyTextMessage(String content, String fromUserName, String toUserName) {
        ReplyTextMessage we = new ReplyTextMessage();
        we.setMessageType("text");
        we.setFuncFlag("0");
        we.setCreateTime(Long.toString(System.currentTimeMillis()));
        we.setContent(content);
        we.setToUserName(fromUserName);
        we.setFromUserName(toUserName);
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("xml", ReplyTextMessage.class);
        xstream.aliasField("ToUserName", ReplyTextMessage.class, "toUserName");
        xstream.aliasField("FromUserName", ReplyTextMessage.class, "fromUserName");
        xstream.aliasField("CreateTime", ReplyTextMessage.class, "createTime");
        xstream.aliasField("MsgType", ReplyTextMessage.class, "messageType");
        xstream.aliasField("Content", ReplyTextMessage.class, "content");
        xstream.aliasField("FuncFlag", ReplyTextMessage.class, "funcFlag");
        return xstream.toXML(we);
    }

}
