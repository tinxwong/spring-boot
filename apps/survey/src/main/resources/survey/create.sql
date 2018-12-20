-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 10.16.74.107    Database: survey
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Table structure for table `answer_checkbox`
--

DROP TABLE IF EXISTS `answer_checkbox`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_checkbox` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `answer_id` bigint(20) DEFAULT NULL COMMENT '回答ID',
  `directory_id` bigint(20) DEFAULT NULL COMMENT '问卷ID',
  `question_id` bigint(20) DEFAULT NULL COMMENT '问题ID',
  `question_item_id` bigint(20) DEFAULT NULL COMMENT '问题项ID',
  `visibility` char(1) DEFAULT NULL COMMENT '是否可用',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_user_id` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp COMMENT '修改时间',
  `updated_user_id` varchar(64) DEFAULT NULL COMMENT '修改人',
  `question_orderby` int(11) DEFAULT NULL COMMENT '回答内容',
  `content` varchar(1000) DEFAULT NULL COMMENT '回答内容',
  PRIMARY KEY (`id`),
  KEY `answer_checkbox_question_item_id` (`question_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=167919 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `answer_fillblank`
--

/**
这只是个测试
*/

DROP TABLE IF EXISTS `answer_fillblank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_fillblank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `content` varchar(1024) DEFAULT NULL COMMENT '内容',
  `answer_id` bigint(20) DEFAULT NULL COMMENT '回答ID',
  `question_id` bigint(20) DEFAULT NULL COMMENT '问题ID',
  `directory_id` bigint(20) DEFAULT NULL COMMENT '问卷ID',
  `visibility` char(1) DEFAULT NULL COMMENT '是否可用',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_user_id` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp COMMENT '修改时间',
  `updated_user_id` varchar(64) DEFAULT NULL COMMENT '修改人',
  `question_orderby` int(11) DEFAULT NULL COMMENT '回答内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=300 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `answer_radio`
--

DROP TABLE IF EXISTS `answer_radio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_radio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `answer_id` bigint(20) DEFAULT NULL COMMENT '回答ID',
  `directory_id` bigint(20) DEFAULT NULL COMMENT '问卷ID',
  `question_id` bigint(20) DEFAULT NULL COMMENT '问题ID',
  `question_item_id` bigint(20) DEFAULT NULL COMMENT '问题项ID',
  `visibility` char(1) DEFAULT NULL COMMENT '是否可用',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_user_id` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp COMMENT '修改时间',
  `updated_user_id` varchar(64) DEFAULT NULL COMMENT '修改人',
  `question_orderby` int(11) DEFAULT NULL COMMENT '回答内容',
  `content` varchar(1000) DEFAULT NULL COMMENT '回答内容',
  PRIMARY KEY (`id`),
  KEY `answer_radio_question_item_id` (`question_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=84701 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `answer_score`
--

DROP TABLE IF EXISTS `answer_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `answer_score` int(11) DEFAULT NULL COMMENT '评分数',
  `answer_id` bigint(20) DEFAULT NULL COMMENT '回答ID',
  `directory_id` bigint(20) DEFAULT NULL COMMENT '问卷ID',
  `question_id` bigint(20) DEFAULT NULL COMMENT '问题ID',
  `question_item_id` bigint(20) DEFAULT NULL COMMENT '问题项ID',
  `visibility` char(1) DEFAULT NULL COMMENT '是否可用',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_user_id` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp COMMENT '修改时间',
  `updated_user_id` varchar(64) DEFAULT NULL COMMENT '修改人',
  `question_orderby` int(11) DEFAULT NULL COMMENT '回答内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `question_checkbox`
--

DROP TABLE IF EXISTS `question_checkbox`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_checkbox` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `option_name` varchar(255) DEFAULT NULL COMMENT '选项名称',
  `order_by` int(11) DEFAULT NULL COMMENT '排序',
  `question_id` bigint(20) DEFAULT NULL COMMENT '所属问题',
  `visibility` char(1) DEFAULT NULL COMMENT '是否显示',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_user_id` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp COMMENT '修改时间',
  `updated_user_id` varchar(64) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=306 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `question_radio`
--

DROP TABLE IF EXISTS `question_radio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_radio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `option_name` varchar(255) DEFAULT NULL COMMENT '选项名称',
  `order_by` int(11) DEFAULT NULL COMMENT '排序',
  `question_id` bigint(20) DEFAULT NULL COMMENT '所属问题',
  `visibility` char(1) DEFAULT NULL COMMENT '是否显示',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_user_id` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp COMMENT '修改时间',
  `updated_user_id` varchar(64) DEFAULT NULL COMMENT '修改人',
  `Column_13` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=630 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `question_score`
--

DROP TABLE IF EXISTS `question_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `option_name` varchar(255) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  `visibility` char(1) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_user_id` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp COMMENT '修改时间',
  `updated_user_id` varchar(64) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `survey_answer`
--

DROP TABLE IF EXISTS `survey_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survey_answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `addr` varchar(128) DEFAULT NULL COMMENT '地址',
  `city` varchar(128) DEFAULT NULL COMMENT '所在城市',
  `finish_num` int(11) DEFAULT NULL COMMENT '完成的题目数',
  `finish_item_num` int(11) DEFAULT NULL COMMENT '完成的题目项',
  `ip_addr` varchar(56) DEFAULT NULL COMMENT 'IP地址',
  `directory_id` bigint(20) DEFAULT NULL COMMENT '问卷ID',
  `is_finish` char(10) DEFAULT NULL COMMENT '是否完成',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_user_id` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp COMMENT '修改时间',
  `updated_user_id` varchar(64) DEFAULT NULL COMMENT '修改人',
  `is_anonymous` char(1) DEFAULT '0' COMMENT '是否匿名',
  `anonymous_name` varchar(64) DEFAULT NULL COMMENT '匿名',
  `seq` int(11) DEFAULT '0' COMMENT '回答序号',
  `content` varchar(5000) DEFAULT NULL COMMENT '回答内容',
  `true_name` varchar(128) DEFAULT '' COMMENT '回答者姓名',
  `visibility` char(1) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84199 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `survey_config`
--

DROP TABLE IF EXISTS `survey_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survey_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `cfg_key_suffix` char(50) DEFAULT NULL COMMENT '配置key后缀',
  `cfg_key_prefix` char(50) DEFAULT NULL COMMENT '配置key前缀',
  `cfg_value` varchar(256) DEFAULT NULL COMMENT '配置值',
  `created_user_id` varchar(128) DEFAULT NULL COMMENT '创建人',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_user_id` varchar(128) DEFAULT NULL COMMENT '修改人',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `survey_directory`
--

DROP TABLE IF EXISTS `survey_directory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survey_directory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `answer_num` int(11) DEFAULT NULL COMMENT '答题次数',
  `excerpt_num` int(11) DEFAULT NULL COMMENT '引用数量',
  `dir_type` int(11) DEFAULT NULL,
  `html_path` varchar(256) DEFAULT NULL COMMENT '静态页面存储路径',
  `copy_from_id` bigint(20) DEFAULT NULL,
  `sid` varchar(256) DEFAULT NULL,
  `if_share` char(1) DEFAULT '0' COMMENT '是否分享',
  `survey_model` int(11) DEFAULT NULL COMMENT '问卷所属模块',
  `survey_name` varchar(256) DEFAULT NULL COMMENT '问卷名称',
  `question_num` int(11) DEFAULT NULL COMMENT '题目数',
  `survey_status` tinyint(4) DEFAULT NULL,
  `check_status` char(1) DEFAULT '0' COMMENT '标识',
  `if_view` char(1) DEFAULT '0' COMMENT '是否公开结果',
  `if_publish` char(1) DEFAULT '0' COMMENT '是否发布',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_user_id` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp COMMENT '修改时间',
  `updated_user_id` varchar(64) DEFAULT NULL COMMENT '修改人',
  `summary` varchar(256) DEFAULT '' COMMENT '摘要',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87593 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `survey_question`
--

DROP TABLE IF EXISTS `survey_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survey_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `directory_id` bigint(20) DEFAULT NULL,
  `belong_module` varchar(64) DEFAULT NULL COMMENT '所属模块',
  `cell_count` int(11) DEFAULT NULL COMMENT '列数',
  `copy_from_id` bigint(20) DEFAULT NULL COMMENT '复制的问题ID',
  `hv` char(1) DEFAULT NULL COMMENT '显示方向',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必须',
  `order_by` int(11) DEFAULT NULL COMMENT '排序',
  `score_min` int(11) DEFAULT NULL COMMENT '评分最小值',
  `score_max` int(11) DEFAULT NULL COMMENT '评分最大值',
  `tag` char(1) DEFAULT NULL COMMENT '标记',
  `title` varchar(512) DEFAULT NULL COMMENT '标题',
  `question_type` char(1) DEFAULT NULL COMMENT '问题类型',
  `visibility` char(1) DEFAULT NULL COMMENT '是否显示',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_user_id` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp COMMENT '修改时间',
  `updated_user_id` varchar(64) DEFAULT NULL COMMENT '修改人',
  `summary` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=463 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `survey_rule`
--

DROP TABLE IF EXISTS `survey_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survey_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `directory_id` bigint(20) DEFAULT NULL COMMENT '问卷ID',
  `effective` char(1) DEFAULT '1' COMMENT '问卷限制',
  `effective_ip` char(1) DEFAULT '0' COMMENT 'IP是否限制',
  `effective_time` int(11) DEFAULT '5' COMMENT '有效间隔时间',
  `answer_max_limit` int(11) DEFAULT NULL COMMENT '最多收集数量',
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '收集结束时间',
  `end_type` char(1) DEFAULT NULL COMMENT '结束方式',
  `mail_only` char(1) DEFAULT NULL COMMENT '仅邮件邀请',
  `rule` char(1) DEFAULT NULL COMMENT '访问规则',
  `answer_least_num` int(11) DEFAULT NULL COMMENT '至少答题数',
  `rule_code` varchar(64) DEFAULT NULL COMMENT '令牌',
  `show_share_survey` char(1) DEFAULT NULL COMMENT '显示分享',
  `show_answer_num` int(11) DEFAULT NULL COMMENT '显示分享次数',
  `survey_note` text COMMENT '问卷说明',
  `if_answer_max_limit` char(1) DEFAULT NULL COMMENT '是否开启最大收集数',
  `if_end_time` char(1) DEFAULT NULL COMMENT '是否开启结束时间',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `created_user_id` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp COMMENT '修改时间',
  `updated_user_id` varchar(64) DEFAULT NULL COMMENT '修改人',
  `is_anonymous` char(1) DEFAULT '0' COMMENT '是否匿名',
  `effective_account` char(1) DEFAULT '0' COMMENT '账号限制',
  `obj_set` varchar(5000) DEFAULT '' COMMENT '对象集合',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87593 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `survey_scope`
--

DROP TABLE IF EXISTS `survey_scope`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survey_scope` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `directory_id` bigint(20) DEFAULT NULL COMMENT '问卷ID',
  `directory_type` int(11) DEFAULT NULL COMMENT '问卷类型',
  `type_name` varchar(64) DEFAULT NULL COMMENT '类型名称',
  `type_id` varchar(64) DEFAULT NULL COMMENT '类型ID',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_user_id` varchar(64) DEFAULT NULL COMMENT '创建人',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp COMMENT '修改时间',
  `updated_user_id` varchar(64) DEFAULT NULL COMMENT '修改人',
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

-- Dump completed on 2018-11-29 11:06:54
