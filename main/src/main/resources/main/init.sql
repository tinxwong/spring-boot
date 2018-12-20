-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: main
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
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `cfg_key_prefix` varchar(50) DEFAULT NULL COMMENT 'key前缀',
  `cfg_key_suffix` varchar(50) DEFAULT NULL COMMENT 'key后缀',
  `cfg_value` varchar(255) DEFAULT NULL COMMENT '配置值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `visibility` tinyint(4) DEFAULT '0' COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'INTERCEPTOR','APP','','2018-11-24 03:39:21',NULL,1),(2,'AAA','BBB','CCCccc','2018-11-24 04:01:19',NULL,0),(3,'DDD','EEE','FFF','2018-11-24 04:01:19',NULL,0),(4,'HHH','III','JJJ','2018-11-24 03:57:42',NULL,0);
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `url_manage`
--

DROP TABLE IF EXISTS `url_manage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `url_manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `text` varchar(50) DEFAULT NULL COMMENT '名称',
  `href` varchar(128) DEFAULT NULL COMMENT '链接',
  `url_type` varchar(1) DEFAULT NULL COMMENT '链接类型',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `md_url` varchar(128) DEFAULT NULL COMMENT 'md5加密链接',
  `app_name` varchar(64) DEFAULT NULL COMMENT '应用名称',
  `module_name` varchar(64) DEFAULT NULL COMMENT '模块名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `root_id` bigint(20) DEFAULT NULL COMMENT '根ID',
  `class_name` varchar(50) DEFAULT '' COMMENT '样式名称',
  `mark_name` varchar(50) DEFAULT '' COMMENT '标识名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `url_manage`
--

LOCK TABLES `url_manage` WRITE;
/*!40000 ALTER TABLE `url_manage` DISABLE KEYS */;
INSERT INTO `url_manage` VALUES (1,'系统管理','/common/admin','1',0,'f1ed438770c0e684810448a29fac1b4e','common','admin','2018-12-04 02:14:38','admin',0,'',''),(2,'下注系统','/common/chipin','1',0,'346154e7b140c622fb4bfc30b7c47387','common','chipin','2018-12-04 02:14:38','admin',0,'',''),(3,'系统管理','/common/admin/manage','2',0,'be104f970f3a964ceace27f388295486','common',NULL,'2018-12-04 03:27:11','admin',1,'',''),(4,'用户管理','/common/user/index','2',3,'36d365d725cddb6ddb5270762e9983c5','common','user','2018-12-04 01:43:12','admin',1,'',''),(5,'系统配置','/common/sysconfig/index','2',3,'e68a72e8038e22d3fd413c4868b4ee4c','common','sysconfig','2018-12-04 01:45:58','admin',1,'',''),(6,'下注管理','/common/chipin/manage','2',0,'e6353c9ea0c94c04002aab661457f495','chipin',NULL,'2018-12-04 03:27:11','admin',2,'',''),(7,'下注网站','/chipin/lottery/index','2',6,'46b06d15ffe06d17d06b6136f97a5a3a','chipin','lottery','2018-12-04 01:43:12','admin',2,'',''),(8,'网站用户','/chipin/userLottery/index','2',6,'988f344ab13e408f985ebfd719d63ca8','chipin','userLottery','2018-12-04 01:43:12','admin',2,'',''),(9,'下注任务','/chipin/task/index','2',6,'e4291ba16e037dc9a53b47bdc9e1e11a','chipin','task','2018-12-04 01:43:12','admin',2,'',''),(10,'平台配置','/chipin/config/index','2',6,'6d480a9d5931b5cb1929fa0f8aedf081','chipin','config','2018-12-04 06:07:40','admin',2,'','');
/*!40000 ALTER TABLE `url_manage` ENABLE KEYS */;
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
  `sex` char(1) DEFAULT '1' COMMENT '性别',
  `role_id` tinyint(4) DEFAULT NULL COMMENT '角色ID',
  `visibility` char(1) DEFAULT NULL COMMENT '是否可用',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','管理员','e10adc3949ba59abbe56e057f20f883e',NULL,'1',1,'1','ADMIN','2018-11-05 00:25:01',NULL,NULL,'2018-10-29 07:09:39'),(2,'kai','恺','e10adc3949ba59abbe56e057f20f883e','13234566545','1',2,'1','COMMON','2018-11-23 22:02:13','kai',NULL,'2018-11-23 22:02:13'),(3,'zhangshan','张三','e10adc3949ba59abbe56e057f20f883e','13432345676','1',2,'1','COMMON','2018-11-24 02:33:49',NULL,NULL,'2018-11-24 02:33:27');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-04 14:17:40
