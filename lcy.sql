/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : lcy

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-10-16 16:10:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_request_header_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_request_header_log`;
CREATE TABLE `tb_request_header_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(11) DEFAULT NULL,
  `user_os` varchar(16) DEFAULT NULL COMMENT '用户设备的操作系统',
  `os_version` varchar(8) DEFAULT NULL COMMENT '系统版本号',
  `app_version` varchar(8) DEFAULT NULL COMMENT 'app版本号',
  `agent_model` varchar(64) DEFAULT NULL COMMENT '手机型号',
  `user_agent` varchar(512) DEFAULT NULL COMMENT '手机名称',
  `agent_num` varchar(64) DEFAULT NULL COMMENT '设备号',
  `interface_name` varchar(64) DEFAULT NULL COMMENT '请求的接口名称',
  `ip_address` varchar(64) DEFAULT NULL COMMENT 'ip地址',
  `ip_country` varchar(16) DEFAULT NULL COMMENT 'ip地址所在国家',
  `ip_area` varchar(32) DEFAULT NULL COMMENT 'ip地址所在区域',
  `ip_province` varchar(32) DEFAULT NULL COMMENT 'ip地址所在省',
  `ip_city` varchar(32) DEFAULT NULL COMMENT 'ip地址所在城市',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `uuid` varchar(64) DEFAULT NULL,
  `operate_id` varchar(32) DEFAULT NULL COMMENT '操作人（医生或用户）id',
  `cost_time` varchar(10) DEFAULT NULL COMMENT '请求花费时间,单位ms',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1229868 DEFAULT CHARSET=utf8 COMMENT='请求头信息表';

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `avatar` text CHARACTER SET utf8 COMMENT '用户图像',
  `nickname` varchar(32) DEFAULT NULL COMMENT '用户昵称（默认手机号或者微信）',
  `realname` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '真实姓名',
  `gender` varchar(4) CHARACTER SET utf8 DEFAULT '99' COMMENT '性别(01为男 ，02为女 ，99保密）',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `phone` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
  `wechat` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '微信号',
  `password` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码（用手机验证码登入时无需密码）',
  `login_days` int(11) DEFAULT '0' COMMENT '登录天数',
  `app_version` varchar(30) DEFAULT NULL,
  `login_num` int(11) DEFAULT '0' COMMENT '登录次数',
  `introduction` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '个人介绍',
  `inblack` tinyint(1) DEFAULT '1' COMMENT '黑名单(0:被拉黑 1:正常)',
  `e_tags` text COMMENT '英文标签',
  `tags` text COMMENT '中文标签',
  `forbidden` tinyint(1) DEFAULT '1' COMMENT '禁用(0:禁用,1:未禁用)',
  `recommend` tinyint(1) DEFAULT '1' COMMENT '0:推荐   1:未推荐',
  `jointime` date DEFAULT NULL COMMENT '注册时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '有效标志 0有效（默认） 1 无效',
  `openid` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `unionid` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `apptoken` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `recommend_code` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '推荐码',
  `rongyun_token` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `aliyun_push_id` varbinary(64) DEFAULT NULL,
  `app_open_id` varbinary(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `secret_key` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `unique_id` varbinary(64) DEFAULT NULL,
  `is_attention_gzh` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `is_success` varchar(255) DEFAULT '0' COMMENT '转换标识',
  `user_os` varchar(16) DEFAULT NULL COMMENT 'android,iOS,wechat',
  `agent_model` varchar(30) DEFAULT NULL COMMENT '手机型号(注册手机)',
  `level` varchar(8) DEFAULT NULL,
  `province_code` varchar(8) DEFAULT NULL,
  `province_name` varchar(64) DEFAULT NULL,
  `city_code` varchar(8) DEFAULT NULL,
  `city_name` varchar(64) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `moment_code` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  UNIQUE KEY `unionid` (`unionid`) USING BTREE,
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52390 DEFAULT CHARSET=utf8mb4 COMMENT='用户注册信息表';
