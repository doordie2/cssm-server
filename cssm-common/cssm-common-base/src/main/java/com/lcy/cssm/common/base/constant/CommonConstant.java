package com.lcy.cssm.common.base.constant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 常量
 *
 * @author zxq
 * @create 2016-12-22
 */
public interface CommonConstant {
    /**
     * 类型常量
     **/
    String TYPE_1 = "1";

    /**
     * 数字常量
     **/
    String NUMBER_ZERO = "0";
    String NUMBER_ONE = "1";
    String NUMBER_TWO = "2";
    String NUMBER_THREE = "3";
    String NUMBER_FOUR = "4";
    String NUMBER_SIX = "6";
    String NUMBER_SEVEN = "7";
    String NUMBER_NINE = "9";
    String NUMBER_TEN = "10";
    String NUMBER_FIFTEEN = "15";
    String NUMBER_THIRTY_TWO = "32";
    String NUMBER_SIXTY = "60";
    String NUMBER_ONE_HUNDRED = "100";
    String NUMBER_TWO_HUNDRED = "200";
    String NUMBER_TWO_THOUSAND = "2000";
    String NUMBER_THREE_THOUSAND = "3000";
    String NUMBER_TEN_THOUSAND = "10000";
    String NUMBER_ONE_MINUS = "-1";

    String SPECIAL_NUMBER_12345678 = "12345678";
    String SPECIAL_NUMBER_87654321 = "87654321";
    /**
     * 整形常量
     */
    Integer ZERO = 0;
    Integer ONE = 1;
    Integer TWO = 2;
    Integer THREE = 3;
    Integer FOUR = 4;
    Integer FIVE = 5;
    Integer SIX = 6;
    Integer SEVEN = 7;
    Integer EIGHT = 8;
    Integer NINE = 9;
    Integer TEN = 10;
    Integer FIFTEEN = 15;
    Integer THIRTY_TWO = 32;
    Integer SIXTY = 60;
    Integer ONE_HUNDRED = 100;
    Integer TWO_HUNDRED = 200;
    Integer TWO_THOUSAND = 2000;
    Integer THREE_THOUSAND = 3000;
    Integer TEN_THOUSAND = 10000;
    Integer DAY_SECONDS=24*3600;

    String BLANK = "";
    /**
     * 系统级别加密的sceretKey
     **/
    String AUTH_COMSUMER_SECRET_KEY = "733828MTIzNDU2CShFp1468889281801r9uV0aajI10";

    /**
     * boolean 常量
     **/
    Boolean TRUE = true;
    Boolean FALSE = false;

    String DEFAULT_PASSWORD = "123456";

    String DEFAULT = "default";

    String RECORD = "record";

    /**
     * 分隔符
     **/
    String SEPARATOR_1 = ",";
    String SEPARATOR_2 = "-";
    /**
     * 用.分割必须加上\\
     */
    String SEPARATOR_3 = "\\.";

    String M_STATE_TOP="top";

    String M_STATE_HOT="hot";

    /**
     * 空白字符串
     **/
    String ELLIPSIS = "";

    /**
     * 省略字符串
     */
    String STRING_BLANK = "...";


    /**
     * 默认头像
     **/
    String ADMIN_DEFAULT_PROFILE = "defaultAdminProfile.png";

    String USER_DEFAULT_PROFILE = "common/default-avatar.png";

    String MALE_DOCTOR_DEFAULT_PROFILE = "picture/doctor/default/head/portrait_man.png";

    String FEMALE_DOCTOR_DEFAULT_PROFILE = "picture/doctor/default/head/portrait_woman.png";

    String CONTENT_DEFAULT_COVERMAP="common/default_covermap.png";

    String CONTENT_DEFAULT_SONG_COVERMAP="common/default_song_covermap.png";

    /**
     * 头像缩放比例
     **/
    String HEAD_PORTRAIT_SUFFIX = "@80h_80w_1e_1c";

    /**
     * 短信签名
     */
    String SMS_SINGLE_NAME = "每次科技";

    /**
     * 短信代码---代理商申请审核通过提醒
     */
    String SMS_CODE_AGENCY_APPLY_SUCCESS = "SMS_117200070";
    String SMS_CODE_AGENCY_APPLY_FAIDED = "SMS_117110082";


    String MOMENT_LIKE_AVATARS_KEY="moment_%s_like_avatars";


    /**
     * like查询的字符串
     */
    String LIKE = "%";
    /**
     * 逗号分隔符
     */
    String COMMA_SEPARATOR = ",";

    /**
     * 微信支付参数
     **/
//    String WXPAY_BUSSINESS_NO = "1233554402";
//    String WXPAY_BUSINESS_SECRET_KEY = "tcfb06c505d485ua54e1c8300f0d5624";
//    String WXPAY_APPID = "wx541da45793175de8";
//    String WXPAY_APP_SECRET_KEY = "0cfb06c505d4855a54e1c8300f0d5604";

    String WXPAY_CREATE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    String WXPAY_REFUND_ORDER_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**
     * 邮件 发送方账号
     */
    String SEND_MAIL = "code@mcilife.com";

    String ZLNSH_LOGO = "https://mcresource.mcilife.com/common/zlnsh-logo.png";
    String ACTIVITY_NOTIFY_LOGO = "https://mcresource.mcilife.com/common/activity_notify.png";

    HashSet<String> URL_FILTER = new HashSet(){{
        add("http://thirdwx.qlogo.cn/");
        add("http://appattach.mcilife.com/");
        add("http://wx.qlogo.cn/");
        add("http://ouk8oqj7n.bkt.clouddn.com/");
        add("http://www.mcilife.com/");
    }};

    List<String> SPECIAL_SYMBOL = new ArrayList<String>(){{
        add("_");
    }};


    List<String> WHITE_LIST = new ArrayList<String>(){{
        add("18368867083");
        add("18758302914");
        add("18758302984");
        add("13647086184");
        add("18167114732");
        add("15158099918");
        add("13295711605");
        add("13375714673");
        add("15158099918");
        add("17756228505");
        //无效号码，测试号
        add("13300000001");
        add("13300000002");
        add("13300000003");
        add("13300000004");
        add("13300000005");
        add("13300000006");
        add("13300000007");
        add("13300000008");
        add("13300000009");
        add("13300000010");
        add("13300000011");
        add("13300000012");
        add("13300000013");
        add("13300000014");
        add("13300000015");
        add("13300000016");
        add("13300000017");
        add("13300000018");
        add("13300000019");
        add("13300000020");
        add("13300000021");
        add("13300000022");
        add("13300000023");
        add("13300000024");
        add("13300000025");
        add("13300000026");
        add("13300000027");
        add("13300000028");
        add("13300000029");
        add("13300000030");
        add("13300000031");
        add("13300000032");
        add("13300000033");
        add("13300000034");
        add("13300000035");
        add("13300000036");
        add("13300000037");
        add("13300000038");
        add("13300000039");
        add("13300000040");
        add("13300000041");
        add("13300000042");
        add("13300000043");
        add("13300000044");
        add("13300000045");
        add("13300000046");
        add("13300000047");
        add("13300000048");
        add("13300000049");
        add("13300000050");
        add("13300000051");
        add("13300000052");
        add("13300000053");
        add("13300000054");
        add("13300000055");
        add("13300000056");
        add("13300000057");
        add("13300000058");
        add("13300000059");
        add("13300000060");
        add("13300000061");
        add("13300000062");
        add("13300000063");
        add("13300000064");
        add("13300000065");
        add("13300000066");
        add("13300000067");
        add("13300000068");
        add("13300000069");
        add("13300000070");
        add("13300000071");
        add("13300000072");
        add("13300000073");
        add("13300000074");
        add("13300000075");
        add("13300000076");
        add("13300000077");
        add("13300000078");
        add("13300000079");
        add("13300000080");
        add("13300000081");
        add("13300000082");
        add("13300000083");
        add("13300000084");
        add("13300000085");
        add("13300000086");
        add("13300000087");
        add("13300000088");
        add("13300000089");
        add("13300000090");
        add("13300000091");
        add("13300000092");
        add("13300000093");
        add("13300000094");
        add("13300000095");
        add("13300000096");
        add("13300000097");
        add("13300000098");
        add("13300000099");
        add("13300000100");



        add("13330000001");
        add("13330000002");
        add("13330000003");
        add("13330000004");
        add("13330000005");

    }};

}
