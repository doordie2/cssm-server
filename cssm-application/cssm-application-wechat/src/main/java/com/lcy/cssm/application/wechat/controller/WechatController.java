package com.lcy.cssm.application.wechat.controller;

import com.aliyun.oss.OSSClient;
import com.lcy.cssm.application.wechat.wechat.Articles;
import com.lcy.cssm.application.wechat.wechat.Item;
import com.lcy.cssm.application.wechat.wechat.ReplyTextMessage;
import com.lcy.cssm.application.wechat.wechat.ReplyTuwenMessage;
import com.lcy.cssm.common.core.redis.RedisClient;
import com.lcy.cssm.common.web.annotation.PageSessionCheck;
import com.lcy.cssm.common.web.base.BaseController;
import com.lcy.cssm.common.web.wechat.AdvancedUtil;
import com.lcy.cssm.common.web.wechat.JsParam;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: lcy
 * @Date: 2018/5/23 10:25
 * @Description:
 */
@Controller
public class WechatController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(WechatController.class);


    private static Pattern BR_PATTERN = Pattern.compile("<br/?>");


    @Autowired
    private OSSClient ossClient;

    @Autowired
    private RedisClient redisClient;

    /**
     * 获取JS_ticket
     */
    @RequestMapping(value = "/wechat/fetchWechatTicket", method = RequestMethod.GET)
    @ResponseBody
    public JsParam getJsTicket(@RequestParam("url") String url) throws IOException {
        String appToken = redisClient.get("appToken");
        if (StringUtils.isBlank(appToken)) {
            appToken = AdvancedUtil.appAccessToken(wechatAppid, wechatSecret);
            //过期时间100分钟;
            redisClient.setEx("appToken", LocalDateTime.now().getSecond() + 50 * 60 * 2, appToken);
        }
        String jsTicket = redisClient.get("jsTicket");
        if (StringUtils.isBlank(jsTicket)) {
            logger.info("缓存失效，用微信接口获取jsticket！");
            jsTicket = AdvancedUtil.getJsTicket(appToken);
            //过期时间100分钟;
            redisClient.setEx("jsTicket", LocalDateTime.now().getSecond() + 50 * 60 * 2, jsTicket);
        }
        if (StringUtils.isNotBlank(jsTicket)) {
            return AdvancedUtil.getJsSign(wechatAppid, jsTicket, url);
        }
        return null;
    }


    @RequestMapping(value = "/wechat/MP_verify_0qjBBz3ta83aHb1i.txt", method = RequestMethod.GET)
    @PageSessionCheck
    public String getMPVerifyTest() {
        return "MP_verify_0qjBBz3ta83aHb1i.txt";
    }




    /**
     * 回复图文消息
     *
     * @param fromUserName 发送方帐号（open_id）
     * @param toUserName   公众帐号
     * @return
     */
    private static String getReplyTuwenMessage(Articles articles, String fromUserName, String toUserName) {
        ReplyTuwenMessage we = new ReplyTuwenMessage();
        we.setArticles(articles);
        we.setMessageType("news");
        we.setFuncFlag("0");
        we.setCreateTime(Long.toString(System.currentTimeMillis()));
        we.setToUserName(fromUserName);
        we.setFromUserName(toUserName);
        we.setArticleCount(articles.getItem().size());
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("xml", ReplyTuwenMessage.class);
        xstream.aliasField("ToUserName", ReplyTuwenMessage.class, "toUserName");
        xstream.aliasField("FromUserName", ReplyTuwenMessage.class, "fromUserName");
        xstream.aliasField("CreateTime", ReplyTuwenMessage.class, "createTime");
        xstream.aliasField("MsgType", ReplyTuwenMessage.class, "messageType");

        xstream.aliasField("ArticleCount", ReplyTuwenMessage.class, "articleCount");
        xstream.aliasField("FuncFlag", ReplyTuwenMessage.class, "funcFlag");
        xstream.aliasField("Articles", ReplyTuwenMessage.class, "Articles");

        xstream.aliasField("item", Articles.class, "item");
        xstream.addImplicitCollection(Articles.class, "item");
        xstream.alias("item", Item.class);
        xstream.aliasField("Title", Item.class, "title");
        xstream.aliasField("Description", Item.class, "description");
        xstream.aliasField("PicUrl", Item.class, "picUrl");
        xstream.aliasField("Url", Item.class, "url");
        return xstream.toXML(we);
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


    private String  getFromContent(String content){

        Matcher m_html = BR_PATTERN.matcher(content);
        content = m_html.replaceAll(""); // 过滤html标签
        StringBuffer replyMsg = new StringBuffer();
        String regex = "<p.*?>(.*?)</p>";
        Pattern pattern = Pattern.compile(regex);
        Matcher m=pattern.matcher(content);
        List<String> list = new ArrayList<>();
        while(m.find()){
            list.add(m.group(1));
        }
        for (int i=0;i<list.size();i++) {
            replyMsg.append(list.get(i));
            if(i!=list.size()-1){
                replyMsg.append("\n");
            }
        }
        return replyMsg.toString();
    }

    private String getDefaultMsgType(String msgType){
        char[] chars = msgType.toCharArray();
        chars[0]-=32;
        return "default"+String.valueOf(chars);
    }

}
