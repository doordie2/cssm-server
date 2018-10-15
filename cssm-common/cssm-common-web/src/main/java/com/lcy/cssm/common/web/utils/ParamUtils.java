package com.lcy.cssm.common.web.utils;

import com.lcy.cssm.common.base.constant.CommonConstant;
import com.lcy.cssm.common.base.constant.UrlTypeConstant;
import com.lcy.cssm.common.base.constant.UserOsConstant;
import com.lcy.cssm.common.base.constant.aliyun.AliyunBucketEnum;
import com.lcy.cssm.common.core.bean.ApplicationContextProvider;
import com.lcy.cssm.support.user.dto.UserInfoDTO;
import com.lcy.cssm.support.user.result.UserLikesResult;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 参数辅助类
 *
 * @author 赵天增
 */
public class ParamUtils {

    private static UserFacade userFacade = ApplicationContextProvider.getBean(UserFacade.class);


    /**
     * 截取字符串指定字符数长度
     * @param value  目标字符串
     * @param length  字符个数
     * @return
     */
    public static String subByStrByte(String value, int length) {

        String valueTemp = "";

        // 指定的长度下，考虑字符的全角半角，最后的汉字。
        if (value.getBytes().length > length) {
            for (char c : value.toCharArray()) {
                if (valueTemp.getBytes().length <= length) {
                    valueTemp += c;

                    if (valueTemp.getBytes().length == length) {
                        break;

                    } else if (valueTemp.getBytes().length > length) {
                        char[] charTemp = valueTemp.toCharArray();
                        valueTemp = "";

                        for (int i = 0; i < charTemp.length - 1; i++) {
                            valueTemp += charTemp[i];
                        }
                        break;
                    }
                }
            }
            value = valueTemp;
        }
        return value;
    }

    public static String letterToH(String letter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < letter.length(); i++) {
            char c = letter.charAt(i);
            sb.append("%");
            sb.append(Integer.toHexString(c));
        }
        return sb.toString();
    }

    /**
     * 获取id
     */
    public static Integer getUserId(HttpServletRequest request) {
        return Integer.parseInt(String.valueOf(request.getAttribute("userId")));
    }

    /**
     * 根据token获取userId
     * @param token
     * @return
     */
    public static Integer getUserIdByToken(String token) {
        Integer userId = null;
        if (StringUtils.isNotBlank(token)) {
            UserInfoDTO userInfoDTO = userFacade.getUserInfoByToken(token);
            if (userInfoDTO != null) {
                userId = userInfoDTO.getUserId();
            }
        }
        return userId;
    }

    public static String getAppType(HttpServletRequest request){
        String userOs = request.getHeader("user-os");
        String appType = UrlTypeConstant.WECHAT;
        if(StringUtils.equals(userOs, UserOsConstant.ANDROID)||StringUtils.equals(userOs, UserOsConstant.IOS)){
            appType = UrlTypeConstant.NATIVE;
        }
        return appType;
    }

    /**
     * 组装用户头像
     * @param userLikesResult
     * @return
     */
    public static String setUserAvatar(UserLikesResult userLikesResult) {
        String avatar = AliyunBucketEnum.BUCKET_COMMON.getUrl() + userLikesResult.getUserAvatar();
        for (String url : CommonConstant.URL_FILTER) {
            if(StringUtils.isNotBlank(userLikesResult.getUserAvatar())){
                if (userLikesResult.getUserAvatar().startsWith(url)) {
                    avatar = userLikesResult.getUserAvatar();
                }
            }else{
                avatar = CommonConstant.USER_DEFAULT_PROFILE;
            }
        }
        return avatar;
    }

    /**
     * 组装用户头像
     * @param userLikesResult
     * @return
     */
    public static String setUserAvatarV2(com.lcy.cssm.support.user.result.v2.UserLikesResult userLikesResult) {
        String avatar = AliyunBucketEnum.BUCKET_COMMON.getUrl() + userLikesResult.getUserAvatar();
        for (String url : CommonConstant.URL_FILTER) {
            if(StringUtils.isNotBlank(userLikesResult.getUserAvatar())){
                if (userLikesResult.getUserAvatar().startsWith(url)) {
                    avatar = userLikesResult.getUserAvatar();
                }
            }else{
                avatar = AliyunBucketEnum.BUCKET_COMMON.getUrl()+CommonConstant.USER_DEFAULT_PROFILE;
            }
        }
        return avatar;
    }

    /**
     * 组装用户头像
     * @param userLikesResult
     * @return
     */
    public static String setUserAvatarV3(com.lcy.cssm.support.user.result.v3.UserLikesResult userLikesResult) {
        String avatar = AliyunBucketEnum.BUCKET_COMMON.getUrl() + userLikesResult.getUserAvatar();
        for (String url : CommonConstant.URL_FILTER) {
            if(StringUtils.isNotBlank(userLikesResult.getUserAvatar())){
                if (userLikesResult.getUserAvatar().startsWith(url)) {
                    avatar = userLikesResult.getUserAvatar();
                }
            }else{
                avatar =AliyunBucketEnum.BUCKET_COMMON.getUrl()+ CommonConstant.USER_DEFAULT_PROFILE;
            }
        }
        return avatar;
    }





    public static String setUserAvatar(String userAdatar) {
        String avatar = AliyunBucketEnum.BUCKET_COMMON.getUrl() + userAdatar;
        for (String url : CommonConstant.URL_FILTER) {
            if(StringUtils.isNotBlank(userAdatar)){
                if (userAdatar.startsWith(url)) {
                    avatar = userAdatar;
                }
            }else{
                avatar = AliyunBucketEnum.BUCKET_COMMON.getUrl()+CommonConstant.USER_DEFAULT_PROFILE;
            }
        }
        return avatar;
    }


    /**
     * 获取分页时的offset
     * @param page
     * @param pageSize
     * @return
     */
    public static Integer getOffset(Integer page,Integer pageSize) {
        page = (page == null || page == 0) ? 1 : page;
        return (page - 1) * pageSize;
    }

    /**
     * 获取分页时的limit
     * @param pageSize
     * @return
     */
    public static Integer getLimit(Integer pageSize) {
        return pageSize < 1 ? 10 : pageSize;
    }


    public static  String getUserAgentNum(HttpServletRequest request){
        String agentNum = null;
        Map<String, String> headerInfo = (Map<String, String>) request.getAttribute("headerInfo");
        if(StringUtils.isNotBlank(headerInfo.get("agent-num"))){
            agentNum = headerInfo.get("agent-num");
        }
        return agentNum;
    }


    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }


}
