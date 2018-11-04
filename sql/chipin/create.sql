-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: lottery
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chipin_log`
--

LOCK TABLES `chipin_log` WRITE;
/*!40000 ALTER TABLE `chipin_log` DISABLE KEYS */;
INSERT INTO `chipin_log` VALUES (1,2,'天蓬元帅',NULL,NULL,4,NULL,'1',NULL,'0',NULL,'2018-10-20 14:54:44',NULL,NULL,NULL,'执行下注功能','500',''),(2,2,'天蓬元帅',NULL,NULL,4,NULL,'1',NULL,'0',NULL,'2018-10-20 15:04:45',NULL,NULL,NULL,'下注号码匹配了开奖号码，取消执行下注功能','500',''),(3,2,'天蓬元帅',NULL,NULL,4,NULL,'1',NULL,'0',NULL,'2018-10-20 15:14:46',NULL,NULL,NULL,'下注号码匹配了开奖号码，取消执行下注功能','500',''),(4,2,'天蓬元帅',NULL,NULL,4,NULL,'1',NULL,'0',NULL,'2018-10-20 15:24:47',NULL,NULL,NULL,'下注号码匹配了开奖号码，取消执行下注功能','500',''),(5,2,'天蓬元帅',NULL,NULL,4,NULL,'1',NULL,'0',NULL,'2018-10-20 15:34:48',NULL,NULL,NULL,'下注号码匹配了开奖号码，取消执行下注功能','500',''),(6,2,'天蓬元帅',NULL,NULL,4,NULL,'1',NULL,'0',NULL,'2018-10-20 15:44:49',NULL,NULL,NULL,'下注号码匹配了开奖号码，取消执行下注功能','500',''),(7,2,'天蓬元帅',NULL,NULL,4,NULL,'1',NULL,'0',NULL,'2018-10-20 15:54:50',NULL,NULL,NULL,'下注号码匹配了开奖号码，取消执行下注功能','500',''),(8,2,'天蓬元帅',NULL,NULL,4,NULL,'1',NULL,'0',NULL,'2018-10-20 16:04:50',NULL,NULL,NULL,'下注号码匹配了开奖号码，取消执行下注功能','500',''),(9,2,'天蓬元帅',NULL,NULL,4,NULL,'1',NULL,'0',NULL,'2018-10-20 16:14:51',NULL,NULL,NULL,'执行下注功能','500',''),(10,4,'kai',NULL,NULL,6,NULL,'0.10',NULL,'0','20181021078','2018-10-21 10:56:53',NULL,NULL,NULL,'{\"Status\":5,\"Data\":\"请登录。\"}','2991',''),(11,4,'kai',NULL,NULL,6,NULL,'0.10',NULL,'0','20181021080','2018-10-21 11:12:54',NULL,NULL,NULL,'{\"Status\":2,\"Data\":\"请至少选择一个号码！\"}','2991',''),(12,4,'kai',NULL,NULL,6,NULL,'0.10',NULL,'0','20181021080','2018-10-21 11:16:31',NULL,NULL,NULL,'{\"Status\":2,\"Data\":\"请至少选择一个号码！\"}','2991',''),(13,4,'kai',NULL,NULL,6,'[{\"bet_no\":\"0013\",\"bet_money\":\"1\",\"dict_no_type_id\":11},{\"bet_no\":\"0014\",\"bet_money\":\"1\",\"dict_no_type_id\":11},{\"bet_no\":\"0015\",\"bet_money\":\"1\",\"dict_no_type_id\":11}]','0.10',NULL,'3','20181021080','2018-10-21 11:19:02',NULL,NULL,NULL,'{\"Status\":1,\"Data\":{\"CompletedStatus\":1,\"LackStatus\":0,\"FinalBetCount\":3,\"FinalMoney\":3.00000}}','2991',''),(14,4,'kai',NULL,NULL,6,'[{\"bet_no\":\"0013\",\"bet_money\":\"1\",\"dict_no_type_id\":11},{\"bet_no\":\"0014\",\"bet_money\":\"1\",\"dict_no_type_id\":11},{\"bet_no\":\"0015\",\"bet_money\":\"1\",\"dict_no_type_id\":11}]','0.10',NULL,'3','20181021081','2018-10-21 11:25:18',NULL,NULL,NULL,'{\"Status\":1,\"Data\":{\"CompletedStatus\":1,\"LackStatus\":0,\"FinalBetCount\":3,\"FinalMoney\":3.00000}}','2988',''),(15,4,'kai',NULL,NULL,6,'[{\"bet_no\":\"0013\",\"bet_money\":\"1\",\"dict_no_type_id\":11},{\"bet_no\":\"0014\",\"bet_money\":\"1\",\"dict_no_type_id\":11},{\"bet_no\":\"0015\",\"bet_money\":\"1\",\"dict_no_type_id\":11}]','0.10',NULL,'3','20181021083','2018-10-21 11:42:48',NULL,NULL,NULL,'{\"Status\":1,\"Data\":{\"CompletedStatus\":1,\"LackStatus\":0,\"FinalBetCount\":3,\"FinalMoney\":3.00000}}','2985',''),(16,4,'kai',NULL,NULL,6,'[{\"bet_no\":\"0013\",\"bet_money\":\"1\",\"dict_no_type_id\":11},{\"bet_no\":\"0014\",\"bet_money\":\"1\",\"dict_no_type_id\":11},{\"bet_no\":\"0015\",\"bet_money\":\"1\",\"dict_no_type_id\":11}]','0.10',NULL,'3','20181021085','2018-10-21 12:00:08',NULL,NULL,NULL,'{\"Status\":1,\"Data\":{\"CompletedStatus\":1,\"LackStatus\":0,\"FinalBetCount\":3,\"FinalMoney\":3.00000}}','2982',''),(17,4,'kai',NULL,NULL,6,'[{\"bet_no\":\"0013\",\"bet_money\":\"1\",\"dict_no_type_id\":11},{\"bet_no\":\"0014\",\"bet_money\":\"1\",\"dict_no_type_id\":11},{\"bet_no\":\"0015\",\"bet_money\":\"1\",\"dict_no_type_id\":11}]','0.10',NULL,'3','20181021086','2018-10-21 12:17:27',NULL,NULL,NULL,'{\"Status\":1,\"Data\":{\"CompletedStatus\":1,\"LackStatus\":0,\"FinalBetCount\":3,\"FinalMoney\":3.00000}}','2979',''),(18,4,'kai',NULL,NULL,6,'[{\"bet_no\":\"0013\",\"bet_money\":\"1\",\"dict_no_type_id\":11},{\"bet_no\":\"0014\",\"bet_money\":\"1\",\"dict_no_type_id\":11},{\"bet_no\":\"0015\",\"bet_money\":\"1\",\"dict_no_type_id\":11}]','0.10',NULL,'3','20181021088','2018-10-21 12:32:12',NULL,NULL,NULL,'{\"Status\":1,\"Data\":{\"CompletedStatus\":1,\"LackStatus\":0,\"FinalBetCount\":3,\"FinalMoney\":3.00000}}','2973',''),(19,4,'kai',NULL,NULL,6,'[{\"bet_no\":\"0013\",\"bet_money\":\"1\",\"dict_no_type_id\":11},{\"bet_no\":\"0014\",\"bet_money\":\"1\",\"dict_no_type_id\":11},{\"bet_no\":\"0015\",\"bet_money\":\"1\",\"dict_no_type_id\":11}]','0.10',NULL,'3','20181021089','2018-10-21 12:46:45',NULL,NULL,NULL,'{\"Status\":1,\"Data\":{\"CompletedStatus\":1,\"LackStatus\":0,\"FinalBetCount\":3,\"FinalMoney\":3.00000}}','2970',''),(20,4,'kai',NULL,NULL,6,'[{\"bet_no\":\"0013\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11},{\"bet_no\":\"0014\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11},{\"bet_no\":\"0015\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11}]','0.10',NULL,'3','20181021090','2018-10-21 12:51:26',NULL,NULL,NULL,'{\"Status\":1,\"Data\":{\"CompletedStatus\":1,\"LackStatus\":0,\"FinalBetCount\":3,\"FinalMoney\":0.3000000}}','2967',''),(21,4,'kai',NULL,NULL,6,'[{\"bet_no\":\"0013\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11},{\"bet_no\":\"0014\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11},{\"bet_no\":\"0015\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11}]','0.10',NULL,'3',NULL,'2018-10-21 13:08:59',NULL,NULL,NULL,'{\"Status\":2,\"Data\":\"期数不能为空！\"}','2966.7',''),(22,4,'kai',NULL,NULL,6,'[{\"bet_no\":\"0013\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11},{\"bet_no\":\"0014\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11},{\"bet_no\":\"0015\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11}]','0.10',NULL,'3','20181021093','2018-10-21 13:27:43',NULL,NULL,NULL,'{\"Status\":1,\"Data\":{\"CompletedStatus\":1,\"LackStatus\":0,\"FinalBetCount\":3,\"FinalMoney\":0.3000000}}','2966.7',''),(23,4,'kai',NULL,NULL,6,'[{\"bet_no\":\"0013\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11},{\"bet_no\":\"0014\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11},{\"bet_no\":\"0015\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11}]','0.10',NULL,'3','20181021094','2018-10-21 13:38:17',NULL,NULL,NULL,'{\"Status\":1,\"Data\":{\"CompletedStatus\":1,\"LackStatus\":0,\"FinalBetCount\":3,\"FinalMoney\":0.3000000}}','2966.4',''),(24,4,'kai',NULL,NULL,6,'[{\"bet_no\":\"0013\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11},{\"bet_no\":\"0014\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11},{\"bet_no\":\"0015\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11}]','0.10',NULL,'3','20181021095','2018-10-21 13:48:18',NULL,NULL,NULL,'{\"Status\":1,\"Data\":{\"CompletedStatus\":1,\"LackStatus\":0,\"FinalBetCount\":3,\"FinalMoney\":0.3000000}}','2966.1',''),(25,4,'kai',NULL,NULL,6,'[{\"bet_no\":\"0013\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11},{\"bet_no\":\"0014\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11},{\"bet_no\":\"0015\",\"bet_money\":\"0.10\",\"dict_no_type_id\":11}]','0.10',NULL,'3','20181021096','2018-10-21 13:58:20',NULL,NULL,NULL,'{\"Status\":1,\"Data\":{\"CompletedStatus\":1,\"LackStatus\":0,\"FinalBetCount\":3,\"FinalMoney\":0.3000000}}','2965.8','');
/*!40000 ALTER TABLE `chipin_log` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lottery`
--

LOCK TABLES `lottery` WRITE;
/*!40000 ALTER TABLE `lottery` DISABLE KEYS */;
INSERT INTO `lottery` VALUES (1,'/Member/DoLogin?_=','/Member/DoLogin?_=','/drawno/GetCurrentPeriodStatus?_=','/Member/GetMemberPrint?_=','金玖网站','金玖投注网站','2018-09-01 14:51:53',NULL,NULL,'2018-10-09 14:23:14',1,'/Member/AcceptAgreement?_=','/drawno/GetCurrentPeriodStatus?_=','/DrawNo/GetDrawNoTable?pageindex=1&_=','http://k1.cq6055.xyz,http://k2.cq6055.xyz,http://k3.cq6055.xyz,http://k4.cq6055.xyz,http://k5.cq6055.xyz,http://k6.cq6055.xyz,http://k7.cq6055.xyz,http://k8.cq6055.xyz','jinJiuAuthent','/Member/BatchBet','/Member/UpLoadBetNos'),(2,'/Member/DoLogin?_=','/Member/DoLogin?_=','/drawno/GetCurrentPeriodStatus?_=','/Member/GetMemberPrint?_=','六六六网站','六六六投注网站','2018-10-09 14:34:47',NULL,NULL,'2018-10-21 08:21:35',1,'/Member/AcceptAgreement?_=','/drawno/GetCurrentPeriodStatus?_=','/DrawNo/GetDrawNoTable?pageindex=1&_=','http://k1.cq718718.xyz,http://k2.cq718718.xyz,http://k3.cq718718.xyz,http://k4.cq718718.xyz,http://k5.cq718718.xyz,http://k6.cq718718.xyz,http://k7.cq718718.xyz,http://k8.cq718718.xyz','liuLiuLiuAuthent','/Member/BatchBet','/Member/UpLoadBetNos');
/*!40000 ALTER TABLE `lottery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sysconfig`
--

DROP TABLE IF EXISTS `sysconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sysconfig` (
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
-- Dumping data for table `sysconfig`
--

LOCK TABLES `sysconfig` WRITE;
/*!40000 ALTER TABLE `sysconfig` DISABLE KEYS */;
INSERT INTO `sysconfig` VALUES (1,'LOTTERY','KEY','asdWE3ac',NULL,'2018-08-11 14:29:27',NULL,'2018-08-11 14:29:27',1),(2,'C','LOTTERY','4',NULL,'2018-08-31 14:10:28',NULL,'2018-08-31 14:10:28',1),(3,'LOTTERY','JINJIU','http://k1.cq6055.xyz,http://k2.cq6055.xyz,http://k3.cq6055.xyz,http://k4.cq6055.xyz,http://k5.cq6055.xyz,http://k6.cq6055.xyz,http://k7.cq6055.xyz,http://k8.cq6055.xyz',NULL,'2018-08-31 14:10:28',NULL,'2018-08-31 14:10:28',1),(4,'TEMP','LOTTERY','D:\\temp',NULL,'2018-10-15 12:51:09',NULL,NULL,1);
/*!40000 ALTER TABLE `sysconfig` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='投注任务';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,1,1,800,100,4000,2.00,'2018-10-16 16:15:21',NULL,NULL,'2018-10-16 16:15:21',1,0,'','3','','往期号码规则,往期号码规则','periodNumberChipinRule','','',0,0),(4,2,1,800,300,10,1.00,'2018-10-20 08:48:15',NULL,NULL,'2018-10-17 15:34:49',1,0,'D:\\temp/2/4/2/242.txt','3','','往期号码规则','periodNumberChipinRule','天蓬元帅','金玖网站',1,0),(5,1,1,800,400,10,1.00,'2018-10-20 07:19:30',NULL,NULL,'2018-10-17 15:38:40',1,0,'D:\\temp/1/5/1/151.txt','3','','往期号码规则','periodNumberChipinRule','齐天大圣','金玖网站',0,0),(6,4,2,3600,10,10,0.10,'2018-10-21 13:55:48',NULL,NULL,'2018-10-21 13:38:16',1,0,'D:\\temp/2/6/4/264.txt','1','','默认规则','defaultChipinRule','kai','六六六网站',0,0);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'齐天大圣','王某','e10adc3949ba59abbe56e057f20f883e','13503098948','1','COMMON','2018-10-14 15:29:26',NULL,NULL,'2018-09-01 15:16:25',1,2),(2,'天蓬元帅','猪八戒','e10adc3949ba59abbe56e057f20f883e','','1','COMMON','2018-10-14 15:29:26',NULL,NULL,'2018-10-14 14:55:50',1,2),(3,'admin','管理员','e10adc3949ba59abbe56e057f20f883e','','1','ADMIN','2018-10-14 15:42:03',NULL,NULL,'2018-10-14 15:21:16',1,1),(4,'kai','恺','e10adc3949ba59abbe56e057f20f883e','','1','COMMON','2018-10-21 08:25:37',NULL,NULL,'2018-10-21 08:25:02',1,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_lottery`
--

LOCK TABLES `user_lottery` WRITE;
/*!40000 ALTER TABLE `user_lottery` DISABLE KEYS */;
INSERT INTO `user_lottery` VALUES (1,1,1,'yy0055','aaa1111','2018-10-09 14:50:11',NULL,NULL,NULL,'齐天大圣','金玖网站',1),(3,2,2,'yy00555','aaa1111','2018-10-20 09:19:05',NULL,NULL,NULL,'天蓬元帅','六六六网站',1),(4,4,2,'yy0055','aaa1111','2018-10-21 08:43:17',NULL,NULL,NULL,'kai','六六六网站',1);
/*!40000 ALTER TABLE `user_lottery` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `user_lottery_rule`
--

LOCK TABLES `user_lottery_rule` WRITE;
/*!40000 ALTER TABLE `user_lottery_rule` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_lottery_rule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-31 10:18:12
