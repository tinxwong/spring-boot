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
-- Dumping data for table `chipin_config`
--

LOCK TABLES `chipin_config` WRITE;
/*!40000 ALTER TABLE `chipin_config` DISABLE KEYS */;
INSERT INTO `chipin_config` VALUES (1,'LOTTERY','KEY','asdWE3ac',NULL,'2018-08-10 22:29:27',NULL,'2018-08-10 22:29:27',1),(3,'LOTTERY','JINJIU','http://k1.cq6055.xyz,http://k2.cq6055.xyz,http://k3.cq6055.xyz,http://k4.cq6055.xyz,http://k5.cq6055.xyz,http://k6.cq6055.xyz,http://k7.cq6055.xyz,http://k8.cq6055.xyz',NULL,'2018-08-30 22:10:28',NULL,'2018-08-30 22:10:28',1),(4,'TEMP','LOTTERY','D:\\temp',NULL,'2018-10-14 20:51:09',NULL,NULL,1);
/*!40000 ALTER TABLE `chipin_config` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `chipin_log`
--

LOCK TABLES `chipin_log` WRITE;
/*!40000 ALTER TABLE `chipin_log` DISABLE KEYS */;
INSERT INTO `chipin_log` VALUES (26,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216074','2018-12-16 10:11:03',NULL,NULL,NULL,'下注成功','2764.8','',NULL,1,'{start=2018-12-16 18:21:30, end=2018-12-16 18:29:00}','1970-01-19 05:09:15',1),(27,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216075','2018-12-16 10:23:23',NULL,NULL,NULL,'下注成功','2764.7','',NULL,1,'{start=2018-12-16 18:31:30, end=2018-12-16 18:39:00}','1970-01-19 05:09:16',1),(28,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216076','2018-12-16 10:34:11',NULL,NULL,NULL,'下注成功','2764.6','',NULL,1,'{start=2018-12-16 18:41:30, end=2018-12-16 18:49:00}','1970-01-19 05:09:17',1),(29,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216077','2018-12-16 10:48:36',NULL,NULL,NULL,'下注成功','2764.5','defaultChipinRule',NULL,1,'{start=2018-12-16 18:51:30, end=2018-12-16 18:59:00}','2018-12-16 18:57:32',1),(30,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216078','2018-12-16 10:57:33',NULL,NULL,NULL,'下注成功','2764.4','defaultChipinRule',NULL,1,'{start=2018-12-16 19:01:30, end=2018-12-16 19:09:00}','2018-12-16 19:04:19',1),(31,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216079','2018-12-16 11:04:20',NULL,NULL,NULL,'下注成功','2764.3','defaultChipinRule',NULL,1,'{start=2018-12-16 19:11:30, end=2018-12-16 19:19:00}','2018-12-16 19:13:37',1),(32,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216080','2018-12-16 11:13:38',NULL,NULL,NULL,'下注成功','2764.2','defaultChipinRule',NULL,1,'{start=2018-12-16 19:21:30, end=2018-12-16 19:29:00}','2018-12-16 19:25:19',1),(33,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216081','2018-12-16 11:25:20',NULL,NULL,NULL,'下注成功','2764.1','defaultChipinRule',NULL,1,'{start=2018-12-16 19:31:30, end=2018-12-16 19:39:00}','2018-12-16 19:35:51',1),(34,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216099','2018-12-16 14:10:56',NULL,NULL,NULL,'下注成功','2764','defaultChipinRule',NULL,1,'{start=2018-12-16 22:16:30, end=2018-12-16 22:19:00}','2018-12-16 22:18:29',1),(35,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216100','2018-12-16 14:18:29',NULL,NULL,NULL,'下注成功','2763.9','defaultChipinRule',NULL,1,'{start=2018-12-16 22:21:30, end=2018-12-16 22:24:00}','2018-12-16 22:23:13',1),(36,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216101','2018-12-16 14:23:14',NULL,NULL,NULL,'下注成功','2763.8','defaultChipinRule',NULL,1,'{start=2018-12-16 22:26:30, end=2018-12-16 22:29:00}','2018-12-16 22:28:58',1),(37,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216102','2018-12-16 14:28:58',NULL,NULL,NULL,'下注成功','2763.7','defaultChipinRule',NULL,1,'{start=2018-12-16 22:31:30, end=2018-12-16 22:34:00}','2018-12-16 22:32:36',1),(38,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216103','2018-12-16 14:32:37',NULL,NULL,NULL,'下注成功','2761.2','defaultChipinRule',NULL,1,'{start=2018-12-16 22:36:30, end=2018-12-16 22:39:00}','2018-12-16 22:38:54',1),(39,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216104','2018-12-16 14:38:56',NULL,NULL,NULL,'下注成功','2758.7','defaultChipinRule',NULL,1,'{start=2018-12-16 22:41:30, end=2018-12-16 22:44:00}','2018-12-16 22:42:47',1),(40,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216105','2018-12-16 14:42:48',NULL,NULL,NULL,'下注成功','2758.6','defaultChipinRule',NULL,1,'{start=2018-12-16 22:46:30, end=2018-12-16 22:49:00}','2018-12-16 22:48:25',1),(41,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216106','2018-12-16 14:48:26',NULL,NULL,NULL,'下注成功','2753.7','defaultChipinRule',NULL,1,'{start=2018-12-16 22:51:30, end=2018-12-16 22:54:00}','2018-12-16 22:52:15',1),(42,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216107','2018-12-16 14:52:15',NULL,NULL,NULL,'下注成功','2753.6','defaultChipinRule',NULL,1,'{start=2018-12-16 22:56:30, end=2018-12-16 22:59:00}','2018-12-16 22:56:51',1),(43,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216108','2018-12-16 14:56:52',NULL,NULL,NULL,'下注成功','2751.1','defaultChipinRule',NULL,1,'{start=2018-12-16 23:01:30, end=2018-12-16 23:04:00}','2018-12-16 23:03:46',1),(44,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216109','2018-12-16 15:03:46',NULL,NULL,NULL,'下注成功','2746.2','defaultChipinRule',NULL,1,'{start=2018-12-16 23:6:30, end=2018-12-16 23:9:00}','2018-12-16 23:06:54',1),(45,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216110','2018-12-16 15:06:56',NULL,NULL,NULL,'下注成功','2743.7','defaultChipinRule',NULL,1,'{start=2018-12-16 23:11:30, end=2018-12-16 23:14:00}','2018-12-16 23:12:08',1),(46,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216111','2018-12-16 15:12:08',NULL,NULL,NULL,'下注成功','2743.6','defaultChipinRule',NULL,1,'{start=2018-12-16 23:16:30, end=2018-12-16 23:19:00}','2018-12-16 23:17:49',1),(47,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181216112','2018-12-16 15:17:50',NULL,NULL,NULL,'下注成功','2741.1','defaultChipinRule',NULL,1,'{start=2018-12-16 23:21:30, end=2018-12-16 23:24:00}','2018-12-16 23:22:31',1),(48,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181218097','2018-12-18 14:00:45',NULL,NULL,NULL,'A JSONObject text must begin with \'{\' at character 1 of <script>document.domain = document.domain;</script>{\"Status\":1,\"Data\":{\"upload_type\":0,\"Details\":[{\"bet_no\":\"0005\",\"bet_money\":\"\",\"dict_no_type_id\":11}],\"TotalCount\":1,\"TotalBetMoney\":\"0\"}}','2749.2','defaultChipinRule',NULL,0,'{start=2018-12-18 22:6:30, end=2018-12-18 22:9:00}','2018-12-18 22:07:49',1),(49,2,'kai',NULL,NULL,8,NULL,'0.10',NULL,NULL,'20181218097','2018-12-18 14:04:30',NULL,NULL,NULL,'A JSONObject text must begin with \'{\' at character 1 of <script>document.domain = document.domain;</script>{\"Status\":2,\"Data\":\"txt导入暂未开放\"}','2749.2','defaultChipinRule',NULL,0,'{start=2018-12-18 22:6:30, end=2018-12-18 22:9:00}','2018-12-18 22:08:48',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lottery`
--

LOCK TABLES `lottery` WRITE;
/*!40000 ALTER TABLE `lottery` DISABLE KEYS */;
INSERT INTO `lottery` VALUES (1,'/Member/DoLogin?_=','/Member/DoLogin?_=','/drawno/GetCurrentPeriodStatus?_=','/Member/GetMemberPrint?_=','金玖网站','金玖投注网站','2018-08-31 22:51:53','admin',NULL,'2018-12-06 08:39:11',1,'/Member/AcceptAgreement?_=','/drawno/GetCurrentPeriodStatus?_=','/DrawNo/GetDrawNoTable?pageindex=1&_=','http://k1.cq6055.xyz,http://k2.cq6055.xyz,http://k3.cq6055.xyz,http://k4.cq6055.xyz,http://k5.cq6055.xyz,http://k6.cq6055.xyz,http://k7.cq6055.xyz,http://k8.cq6055.xyz','jinJiuAuthent','/Member/BatchBet','/Member/UpLoadBetNos'),(2,'/Member/DoLogin?_=','/Member/DoLogin?_=','/drawno/GetCurrentPeriodStatus?_=','/Member/GetMemberPrint?_=','六六六网站','六六六投注网站','2018-10-08 22:34:47','admin',NULL,'2018-12-19 01:34:19',1,'/Member/AcceptAgreement?_=','/drawno/GetCurrentPeriodStatus?_=','/DrawNo/GetDrawNoTable?pageindex=1&_=','http://ff1.qq18718722.com,http://ff2.qq18718722.com,http://ff3.qq18718722.com,http://ff4.qq18718722.com,http://ff5.qq18718722.com,http://ff6.qq18718722.com,http://ff7.qq18718722.com,http://ff8.qq18718722.com','liuLiuLiuAuthent','/Member/BatchBet','/Member/UpLoadBetNos'),(3,'/Member/DoLogin?_=','/Member/DoLogin?_=','/drawno/GetCurrentPeriodStatus?_=','/Member/GetMemberPrint?_=','新永利','新永利投注网站','2018-10-08 22:34:47','admin',NULL,'2018-12-16 09:10:32',1,'/Member/AcceptAgreement?_=','/drawno/GetCurrentPeriodStatus?_=','/DrawNo/GetDrawNoTable?pageindex=1&_=','http://1y.cq1555.xyz,http://2y.cq1555.xyz,http://3y.cq1555.xyz,http://4y.cq1555.xyz,http://5y.cq1555.xyz,http://6y.cq1555.xyz,http://7y.cq1555.xyz,http://8y.cq1555.xyz','liuLiuLiuAuthent','/Member/BatchBet','/Member/UpLoadBetNos');
/*!40000 ALTER TABLE `lottery` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `migration`
--

LOCK TABLES `migration` WRITE;
/*!40000 ALTER TABLE `migration` DISABLE KEYS */;
INSERT INTO `migration` VALUES (1,'chipin/init.sql','8858f8411ab709e0d547875a8f071391','2018-12-04 09:39:04'),(2,'chipin/20181212.sql','fdbb905576d8d4e5b724ec6a5c53d122','2018-12-12 10:28:24');
/*!40000 ALTER TABLE `migration` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='投注任务';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (5,1,1,800,400,10,1.00,'2018-10-19 15:19:30',NULL,NULL,'2018-10-16 23:38:40',1,0,'D:\\temp/1/5/1/151.txt','3','','往期号码规则','periodNumberChipinRule','齐天大圣','金玖网站',0,0),(6,2,2,3600,1000,10,0.10,'2018-12-19 01:50:03',NULL,'kai','2018-12-19 01:50:03',1,0,'D:\\temp/2/6/4/264.txt','1','','默认规则','defaultChipinRule','kai','六六六网站',0,0),(7,3,1,400,100,NULL,1.00,'2018-12-05 15:12:13',NULL,NULL,'2018-12-05 15:12:13',0,0,'D:\\temp/1/7/3/173.txt','1','','默认规则','defaultChipinRule','zhangshan','金玖网站',0,0),(8,2,3,4500,0,NULL,0.10,'2018-12-19 01:28:26',NULL,NULL,'2018-12-19 01:28:26',1,0,'D:\\temp/3/8/2/382.txt','1','','默认规则','defaultChipinRule','kai','新永利',0,0);
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
INSERT INTO `user` VALUES (1,'齐天大圣','王某','e10adc3949ba59abbe56e057f20f883e','13503098948','1','COMMON','2018-10-13 23:29:26',NULL,NULL,'2018-08-31 23:16:25',1,2),(2,'天蓬元帅','猪八戒','e10adc3949ba59abbe56e057f20f883e','','1','COMMON','2018-10-13 23:29:26',NULL,NULL,'2018-10-13 22:55:50',1,2),(3,'admin','管理员','e10adc3949ba59abbe56e057f20f883e','','1','ADMIN','2018-10-13 23:42:03',NULL,NULL,'2018-10-13 23:21:16',1,1),(4,'kai','恺','e10adc3949ba59abbe56e057f20f883e','','1','COMMON','2018-10-20 16:25:37',NULL,NULL,'2018-10-20 16:25:02',1,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_lottery`
--

LOCK TABLES `user_lottery` WRITE;
/*!40000 ALTER TABLE `user_lottery` DISABLE KEYS */;
INSERT INTO `user_lottery` VALUES (4,2,2,'yy0055','aaaa11111','2018-12-19 01:31:48','admin','admin',NULL,'kai','六六六网站',1),(5,2,3,'hh3555','aaaa11111','2018-12-16 09:11:02','admin',NULL,NULL,'kai','新永利',1);
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

-- Dump completed on 2018-12-20 15:56:13
