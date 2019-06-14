/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : wl_config

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 15/06/2019 01:56:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cc_app
-- ----------------------------
DROP TABLE IF EXISTS `cc_app`;
CREATE TABLE `cc_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(255) NOT NULL COMMENT '应用名称',
  `app_desc` varchar(512) NOT NULL DEFAULT '' COMMENT '应用描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(255) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_name` (`app_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用';

-- ----------------------------
-- Table structure for cc_app_user
-- ----------------------------
DROP TABLE IF EXISTS `cc_app_user`;
CREATE TABLE `cc_app_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_env_user` (`app_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用关联用户';

-- ----------------------------
-- Table structure for cc_config
-- ----------------------------
DROP TABLE IF EXISTS `cc_config`;
CREATE TABLE `cc_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `env_id` int(11) NOT NULL COMMENT '环境id',
  `config_key` varchar(255) NOT NULL COMMENT '配置key',
  `config_value` varchar(255) NOT NULL COMMENT '配置value',
  `config_desc` varchar(512) NOT NULL COMMENT '描述',
  `status` tinyint(4) NOT NULL COMMENT '状态, -1:删除, 0:已发布, 1:新增, 2:修改',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_env_key` (`app_id`,`env_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置项';

-- ----------------------------
-- Table structure for cc_config_version
-- ----------------------------
DROP TABLE IF EXISTS `cc_config_version`;
CREATE TABLE `cc_config_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `env_id` int(11) NOT NULL COMMENT '环境id',
  `version` bigint(20) NOT NULL COMMENT '版本',
  `config_key` varchar(255) NOT NULL COMMENT '配置key',
  `config_value` varchar(255) NOT NULL COMMENT '配置value',
  `config_desc` varchar(512) NOT NULL COMMENT '描述',
  `change_type` tinyint(4) NOT NULL COMMENT '变更类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_env_key` (`app_id`,`env_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置项(已发布)';

-- ----------------------------
-- Table structure for cc_env
-- ----------------------------
DROP TABLE IF EXISTS `cc_env`;
CREATE TABLE `cc_env` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `env_name` varchar(255) NOT NULL COMMENT '环境名称',
  `env_desc` varchar(512) NOT NULL DEFAULT '' COMMENT '环境描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(255) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_env_name` (`env_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='环境';

-- ----------------------------
-- Table structure for cc_version
-- ----------------------------
DROP TABLE IF EXISTS `cc_version`;
CREATE TABLE `cc_version` (
  `id` bigint(20) NOT NULL,
  `app_id` int(11) NOT NULL COMMENT '应用id',
  `env_id` int(11) NOT NULL COMMENT '环境id',
  `publish_desc` varchar(512) DEFAULT NULL COMMENT '发布说明',
  `publish_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版本号';

SET FOREIGN_KEY_CHECKS = 1;
