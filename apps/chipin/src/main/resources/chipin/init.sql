-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: chipin
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chipin_config`
--

DROP TABLE IF EXISTS `chipin_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chipin_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `cfg_key_suffix` varchar(64) DEFAULT NULL COMMENT 'key名后缀',
  `cfg_key_prefix` varchar(64) DEFAULT NULL COMMENT 'key名前缀',
  `cfg_value` varchar(256) DEFAULT NULL COMMENT 'key的值',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL,
  `visibility` tinyint(4) DEFAULT '1' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chipin_log`
--

DROP TABLE IF EXISTS `chipin_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chipin_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `lottery_user_id` bigint(20) DEFAULT NULL COMMENT '投注用户ID',
  `lottery_user_name` varchar(255) DEFAULT NULL COMMENT '投注用户名',
  `task_id` bigint(20) DEFAULT NULL COMMENT '任务ID',
  `bets` varchar(5000) DEFAULT NULL COMMENT '投注内容',
  `bet_money` varchar(10) DEFAULT NULL COMMENT '单注金额',
  `total_bet_money` varchar(10) DEFAULT NULL COMMENT '全部金额',
  `bet_count` varchar(10) DEFAULT NULL COMMENT '投注码数量',
  `period_no` varchar(20) DEFAULT NULL COMMENT '期数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `results` varchar(255) DEFAULT '' COMMENT '结果',
  `account_balance` varchar(50) DEFAULT '' COMMENT '账户余额',
  `execute_rule_name` varchar(50) DEFAULT '' COMMENT '执行规则名称',
  `bets_content` tinyint(4) DEFAULT NULL COMMENT '投注内容',
  `bets_size` tinyint(4) DEFAULT NULL COMMENT '投注长度',
  `chipin_time_scope` varchar(100) DEFAULT '' COMMENT '时间范围',
  `next_chipin_time` varchar(100) DEFAULT '' COMMENT '下次投注时间',
  `visibility` tinyint(4) DEFAULT '1' COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lottery`
--

DROP TABLE IF EXISTS `lottery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lottery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `login_url` varchar(128) DEFAULT NULL COMMENT '登录链接',
  `chip_in_url` varchar(128) DEFAULT NULL COMMENT '下注链接',
  `periods_url` varchar(128) DEFAULT NULL COMMENT '期数链接',
  `account_url` varchar(128) DEFAULT NULL COMMENT '账户链接',
  `name` char(20) DEFAULT NULL COMMENT '网站名称',
  `intro` varchar(128) DEFAULT NULL COMMENT '介绍',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `visibility` tinyint(4) DEFAULT '1' COMMENT '是否删除',
  `agreement_url` varchar(128) DEFAULT '' COMMENT '同意链接',
  `current_period_status_url` varchar(128) DEFAULT '' COMMENT '当前期数状态',
  `draw_no_table_url` varchar(128) DEFAULT NULL,
  `root_url` varchar(500) DEFAULT '' COMMENT '根链接',
  `authent_class_name` varchar(50) DEFAULT '' COMMENT '认证类名称',
  `batch_bet_url` varchar(128) DEFAULT '' COMMENT '批量下注链接',
  `upload_bet_nos_url` varchar(128) DEFAULT '' COMMENT '文件上传链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `migration`
--

DROP TABLE IF EXISTS `migration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `migration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `file_mdkey` varchar(255) DEFAULT NULL COMMENT '文件key',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `lottery_id` bigint(20) DEFAULT NULL COMMENT '投注网站ID',
  `account_max_limit` decimal(10,0) DEFAULT NULL COMMENT '最大金额',
  `account_min_limit` decimal(10,0) DEFAULT NULL COMMENT '最小金额',
  `interval_time` int(11) DEFAULT NULL COMMENT '任务间隔时间',
  `money` decimal(10,2) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `visibility` tinyint(4) DEFAULT '1' COMMENT '是否删除',
  `status` tinyint(4) DEFAULT '0' COMMENT '任务状态',
  `chipin_file_path` varchar(100) DEFAULT '' COMMENT '下注文件路径',
  `rule_id` varchar(10) DEFAULT '' COMMENT '规则ID',
  `rule_detail` varchar(500) DEFAULT '' COMMENT '规则细节',
  `rule_desc` varchar(20) DEFAULT '' COMMENT '规则描述',
  `rule_name` varchar(30) DEFAULT '' COMMENT '规则名称',
  `user_name` varchar(255) DEFAULT '' COMMENT '用户名称',
  `lottery_name` varchar(255) DEFAULT '' COMMENT '投注网站名称',
  `period_num` tinyint(4) DEFAULT '0' COMMENT '期数数量',
  `interval_periods` tinyint(4) DEFAULT '0' COMMENT '间隔期数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='投注任务';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `true_name` varchar(64) DEFAULT NULL COMMENT '真实名',
  `pwd` varchar(128) DEFAULT NULL COMMENT '密码',
  `phone` char(20) DEFAULT NULL COMMENT '电话',
  `sex` enum('0','1') DEFAULT '1' COMMENT '性别',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `visibility` tinyint(4) DEFAULT '1' COMMENT '是否删除',
  `role_id` tinyint(4) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_lottery`
--

DROP TABLE IF EXISTS `user_lottery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_lottery` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `lottery_id` int(11) DEFAULT NULL COMMENT '网站ID',
  `login_user` varchar(255) DEFAULT NULL COMMENT '登录名',
  `login_pwd` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `user_name` varchar(64) DEFAULT '' COMMENT '用户名',
  `lottery_name` varchar(64) DEFAULT '' COMMENT '网站名',
  `visibility` tinyint(4) DEFAULT '1' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_lottery_rule`
--

DROP TABLE IF EXISTS `user_lottery_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_lottery_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `lottery_id` bigint(20) DEFAULT NULL COMMENT '网站ID',
  `rule_id` bigint(20) DEFAULT NULL COMMENT '规则ID',
  `rule_name` varchar(50) DEFAULT NULL COMMENT '规则名称',
  `start_period` varchar(20) DEFAULT NULL COMMENT '开始期数',
  `end_period` varchar(20) DEFAULT NULL COMMENT '结束期数',
  `interval_periods` tinyint(4) DEFAULT NULL COMMENT '间隔期数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-20 15:45:32
